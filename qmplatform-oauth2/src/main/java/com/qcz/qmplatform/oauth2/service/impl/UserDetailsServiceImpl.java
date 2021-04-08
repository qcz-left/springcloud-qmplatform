package com.qcz.qmplatform.oauth2.service.impl;

import com.qcz.qmplatform.oauth2.domain.AuthUser;
import com.qcz.qmplatform.oauth2.mapper.AuthorizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private AuthorizationMapper authorizationMapper;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        Map<String, String> sysUser = authorizationMapper.queryUserByLoginname(loginName);
        if (sysUser == null) {
			return null;
		}

        String userId = sysUser.get("userid");
        List<String> authorities = authorizationMapper.queryAuthoritiesByUserId(userId);
        HashSet<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        for (String auth : authorities) {
        	grantedAuthorities.add(new SimpleGrantedAuthority(auth));
		}
        AuthUser user = new AuthUser(loginName, sysUser.get("password"), grantedAuthorities);
        user.setSysUserId(userId);
        user.setSysUserName(sysUser.get("username"));
        return user;
    }
}
