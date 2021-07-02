package com.qcz.qmplatform.gateway.config;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.qcz.qmplatform.common.utils.SecureUtils;
import io.netty.buffer.ByteBufAllocator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 权限过滤器
 */
@Component
public class AuthorizeFilterConfig implements GlobalFilter, Ordered {

    private static final String ENC_REG = "ENC\\([a-zA-Z0-9\\+/=]+\\)";

    private static final Pattern ENC_PATTERN = Pattern.compile(ENC_REG);

    @Value("${custom.gateway.allowed-urls}")
    private List<String> allowedUrls;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        // 过滤白名单URL
        String requestPath = request.getPath().value();
        if (allowedUrls.contains(requestPath)) {
            return chain.filter(exchange);
        }

        ServerHttpResponse response = exchange.getResponse();
        String authorization = HttpHeaders.AUTHORIZATION;
        // 从头文件中获取token
        String token = request.getHeaders().getFirst(authorization);
        boolean tokenInHeader = true;
        // 参数中获取token
        if (StrUtil.isBlank(token)) {
            token = request.getQueryParams().getFirst(authorization);
            tokenInHeader = false;
        }
        // cookie中获取token
        if (StrUtil.isBlank(token)) {
            HttpCookie httpCookie = request.getCookies().getFirst(authorization);
            if (httpCookie != null) {
                token = httpCookie.getValue();
            }
        }

        if (StrUtil.isBlank(token)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        } else if (tokenInHeader) {
            // 将token传递到下一个微服务的头文件中
            String newToken = token;
            if (!token.startsWith("bearer") && !token.startsWith("Bearer")) {
                newToken = "bearer " + newToken;
            }
            request.mutate().header(authorization, newToken);
        }
        String method = request.getMethodValue();
        return DataBufferUtils.join(request.getBody()).flatMap(d -> Mono.just(Optional.of(d))).defaultIfEmpty(Optional.empty())
                .flatMap(optional -> {
                    // 取出body中的参数
                    String bodyString = "";
                    if (optional.isPresent()) {
                        byte[] oldBytes = new byte[optional.get().readableByteCount()];
                        optional.get().read(oldBytes);
                        bodyString = new String(oldBytes, StandardCharsets.UTF_8);
                    }
                    HttpHeaders httpHeaders = request.getHeaders();
                    // 执行加密参数的解密、XSS过滤清理
                    bodyString = stripXSS(stripENC(bodyString));
                    Map<String, List<String>> decodeParams = HttpUtil.decodeParams(request.getURI().getQuery(), "UTF-8");
                    Map<String, List<String>> newParams = new HashMap<>();
                    for (String paramKey : decodeParams.keySet()) {
                        List<String> newVal = new ArrayList<>();
                        for (String paramVal : decodeParams.get(paramKey)) {
                            String newParamVal = stripXSS(stripENC(paramVal));
                            newVal.add(newParamVal);
                        }
                        newParams.put(paramKey, newVal);
                    }
                    String newPath = request.getURI().getPath() + "?" + HttpUtil.toParams(newParams);
                    ServerHttpRequest newRequest = null;
                    try {
                        newRequest = request.mutate().uri(new URI(newPath)).build();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }

                    // 重新构造body
                    byte[] newBytes = bodyString.getBytes(StandardCharsets.UTF_8);
                    DataBuffer bodyDataBuffer = toDataBuffer(newBytes);
                    Flux<DataBuffer> bodyFlux = Flux.just(bodyDataBuffer);
                    // 重新构造header
                    HttpHeaders headers = new HttpHeaders();
                    headers.putAll(httpHeaders);
                    // 由于修改了传递参数，需要重新设置CONTENT_LENGTH，长度是字节长度，不是字符串长度
                    int length = newBytes.length;
                    headers.remove(HttpHeaders.CONTENT_LENGTH);
                    headers.setContentLength(length);
                    headers.set(HttpHeaders.CONTENT_TYPE, "application/json;charset=utf8");
                    // 重写ServerHttpRequestDecorator，修改了body和header，重写getBody和getHeaders方法
                    newRequest = new ServerHttpRequestDecorator(Objects.requireNonNull(newRequest)) {
                        @SuppressWarnings("NullableProblems")
                        @Override
                        public Flux<DataBuffer> getBody() {
                            return bodyFlux;
                        }

                        @SuppressWarnings("NullableProblems")
                        @Override
                        public HttpHeaders getHeaders() {
                            return headers;
                        }
                    };
                    return chain.filter(exchange.mutate().request(newRequest).build());
                });
    }

    @Override
    public int getOrder() {
        return -3;
    }

    /**
     * 字节数组转DataBuffer
     *
     * @param bytes 字节数组
     * @return DataBuffer
     */
    private DataBuffer toDataBuffer(byte[] bytes) {
        NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
        DataBuffer buffer = nettyDataBufferFactory.allocateBuffer(bytes.length);
        buffer.write(bytes);
        return buffer;
    }

    /**
     * 解密ENC(xxxxx)加密参数
     */
    private String stripENC(String value) {
        if (value != null) {
            Matcher matcher = ENC_PATTERN.matcher(value);
            while (matcher.find()) {
                String group = matcher.group();
                value = value.replaceFirst(ENC_REG, SecureUtils.rsaDecrypt(group.substring(4, group.length() - 1)));
            }
        }
        return value;
    }

    /**
     * XSS攻击字符过滤
     */
    private String stripXSS(String value) {
        if (value != null) {
            // NOTE: It's highly recommended to use the ESAPI library and
            // uncomment the following line to
            // avoid encoded attacks.
            // value = ESAPI.encoder().canonicalize(value);
            // Avoid null characters
            value = value.replaceAll("", "");
            // Avoid anything between script tags
            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid anything in a
            // src="http://www.yihaomen.com/article/java/..." type of
            // e­xpression
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            // Remove any lonesome </script> tag
            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid eval(...) e­xpressions
            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid e­xpression(...) e­xpressions
            scriptPattern = Pattern.compile("e­xpression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid javascript:... e­xpressions
            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid vbscript:... e­xpressions
            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid onload= e­xpressions
            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            scriptPattern = Pattern.compile("onerror(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
        }
        return value;
    }

}
