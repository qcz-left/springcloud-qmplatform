package com.qcz.qmplatform.oauth2.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.qcz.qmplatform.oauth2.domain.AuthGrantType;
import com.qcz.qmplatform.oauth2.domain.AuthToken;
import com.qcz.qmplatform.oauth2.mapper.AuthorizationMapper;
import com.qcz.qmplatform.oauth2.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private AuthorizationMapper authorizationMapper;

    @Value("${spring.application.name}")
    private String serviceId;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Override
    public AuthToken login(String username, String password, AuthGrantType grantType, String clientId, String clientSecret) {
        /*
         * 传递普通参数
         */
        Map<String, Object> body = new HashMap<>();
        body.put("username", username);
        body.put("password", password);
        return getAuthToken(body, grantType, clientId, clientSecret);
    }

    @Override
    public AuthToken refreshToken(String refreshToken, AuthGrantType grantType, String clientId, String clientSecret) {
        /*
         * 传递普通参数
         */
        Map<String, Object> body = new HashMap<>();
        body.put("refresh_token", refreshToken);
        return getAuthToken(body, grantType, clientId, clientSecret);
    }

    private AuthToken getAuthToken(Map<String, Object> body, AuthGrantType grantType, String clientId, String clientSecret) {
        ServiceInstance instance = loadBalancerClient.choose(serviceId);
        HttpRequest httpRequest = HttpUtil.createPost(instance.getUri() + contextPath + "/oauth/token");
        // 客户端验证
        httpRequest.basicAuth(clientId, clientSecret);
        body.put("grant_type", grantType.value());
        httpRequest.form(body);

        HttpResponse httpResponse = httpRequest.execute();
        String responseBody = httpResponse.body();
        AuthToken authToken = null;
        if (httpResponse.isOk() && !StrUtil.isBlank(responseBody)) {
            authToken = JSONUtil.toBean(responseBody, AuthToken.class);
        }
        return authToken;
    }

    /**
     * 根据登录名获取邮件信息
     *
     * @param loginName 登录名
     * @return 邮件地址
     */
    @Override
    public String getMailByLoginName(String loginName) {
        Map<String, String> user = authorizationMapper.queryUserByLoginname(loginName);
        if (user != null) {
            return user.get("emailAddr");
        }
        return null;
    }
}
