package com.qcz.qmplatform.oauth2.service;

import com.qcz.qmplatform.oauth2.domain.AuthGrantType;
import com.qcz.qmplatform.oauth2.domain.AuthToken;

public interface IAuthService {

    /**
     * 用户登录实现（从oauth2中获取token信息）
     *
     * @param username     用户名
     * @param password     密码
     * @param grantType    认证类型
     * @param clientId     客户端id
     * @param clientSecret 客户端密码
     * @return
     */
    AuthToken login(String username, String password, AuthGrantType grantType, String clientId, String clientSecret);

    AuthToken refreshToken(String refreshToken, AuthGrantType grantType, String clientId, String clientSecret);

    String getMailByLoginName(String loginName);
}
