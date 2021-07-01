package com.qcz.qmplatform.baseweb.starter;

import com.qcz.qmplatform.common.constant.Constant;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties(BaseWebProperties.class)
@ConditionalOnWebApplication
public class BaseWebAutoConfiguration extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenStore(tokenStore());
        resources.authenticationEntryPoint(authExceptionEntryPoint());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").authenticated();

    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        ClassPathResource resource = new ClassPathResource(Constant.PUBLIC_CERT_FILE);
        String verifierKey = null;
        try {
            byte[] resourceBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
            verifierKey = new String(resourceBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        converter.setVerifierKey(verifierKey);
        return converter;
    }

    @Bean
    public AuthExceptionEntryPoint authExceptionEntryPoint() {
        return new AuthExceptionEntryPoint();
    }

    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Bean
    public RequestAspect requestAspect() {
        return new RequestAspect();
    }

}
