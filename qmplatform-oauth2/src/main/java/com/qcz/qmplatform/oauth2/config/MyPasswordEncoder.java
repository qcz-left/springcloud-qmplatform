package com.qcz.qmplatform.oauth2.config;

import com.qcz.qmplatform.common.utils.SecureUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 账号密码加密器
 */
public class MyPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return SecureUtils.accountEncrypt(String.valueOf(charSequence));
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
}
