package com.qcz.qmplatform.oauth2.config;

import com.qcz.qmplatform.oauth2.domain.AuthUser;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Map;

public class MyJwtAccessTokenConverter extends JwtAccessTokenConverter {

    @Override
    public Map<String, ?> convertAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        Map<String, Object> resp = (Map<String, Object>) super.convertAccessToken(token, authentication);
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        resp.put("sysUserId", authUser.getSysUserId());
        resp.put("sysUserName", authUser.getSysUserName());
        return resp;
    }
}
