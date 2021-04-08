package com.qcz.qmplatform.gateway.config;

import cn.hutool.json.JSONUtil;
import com.google.common.base.Charsets;
import com.qcz.qmplatform.common.bean.TokenPayload;
import com.qcz.qmplatform.common.constant.ResponseCode;
import com.qcz.qmplatform.common.utils.ConfigLoader;
import com.qcz.qmplatform.common.utils.JwtDecoder;
import com.qcz.qmplatform.common.utils.StringUtils;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.NettyWriteResponseFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON;

@Configuration
public class ResponseGlobalFilter implements GlobalFilter, Ordered {

    @Value("${custom.gateway.allowed-urls}")
    private List<String> allowedUrls;

    @SuppressWarnings("unchecked")
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        // 过滤白名单URL
        String requestPath = request.getPath().value();
        if (allowedUrls.contains(requestPath)) {
            return chain.filter(exchange);
        }

        ServerHttpResponse originalResponse = exchange.getResponse();
        DataBufferFactory bufferFactory = originalResponse.bufferFactory();
        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
            @SuppressWarnings({"NullableProblems"})
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                // 判断服务返回的数据类型进行拦截，根据自己的业务进行修改
                if (APPLICATION_JSON.isCompatibleWith(getDelegate().getHeaders().getContentType())) {
                    Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                    return super.writeWith(fluxBody.buffer().map(dataBuffers -> {
                        DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
                        DataBuffer join = dataBufferFactory.join(dataBuffers);
                        byte[] content = new byte[join.readableByteCount()];
                        join.read(content);
                        DataBufferUtils.release(join);
                        String responseData = new String(content, Charsets.UTF_8);
                        // 判断token是否快过期
                        Map<String, Object> reqBodyMap = JSONUtil.toBean(responseData, Map.class);
                        String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
                        if (StringUtils.isNotBlank(token)) {
                            TokenPayload tokenPayload = JwtDecoder.decodeToken((token.startsWith("bearer") || token.startsWith("Bearer")) ? token.substring(7) : token);
                            if (tokenPayload.getExp() - (new Date().getTime() / 1000) < ConfigLoader.getTokenWillExpTime()) {
                                if (reqBodyMap.get("code") != null && ResponseCode.SUCCESS.code() == (int) reqBodyMap.get("code")) {
                                    reqBodyMap.put("code", ResponseCode.TOKEN_WILL_EXPIRE.code());
                                }
                            }
                        }
                        byte[] uppedContent = JSONUtil.toJsonStr(reqBodyMap).getBytes(Charsets.UTF_8);
                        return bufferFactory.wrap(uppedContent);
                    }));
                } else {
                    return chain.filter(exchange);
                }
            }
        };
        return chain.filter(exchange.mutate().response(decoratedResponse).build());
    }

    @Override
    public int getOrder() {
        //WRITE_RESPONSE_FILTER 之前执行
        return NettyWriteResponseFilter.WRITE_RESPONSE_FILTER_ORDER - 1;
    }

}
