package com.qcz.qmplatform.common.utils;

import cn.hutool.json.JSONUtil;
import com.qcz.qmplatform.common.bean.TokenPayload;
import com.qcz.qmplatform.common.constant.Constant;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.interfaces.RSAPublicKey;

public class JwtDecoder {
    /**
     * 解密Token
     *
     * @param token
     * @return
     */
    public static TokenPayload decodeToken(String token) {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(Constant.KEYSTORE_FILE), Constant.SIGN_PASSWORD.toCharArray());
        RSAPublicKey publicKey = (RSAPublicKey) keyStoreKeyFactory.getKeyPair(Constant.SIGN_ACCOUNT).getPublic();
        String payload = JwtHelper.decodeAndVerify(token, new RsaVerifier(publicKey)).getClaims();
        return JSONUtil.toBean(payload, TokenPayload.class);
    }

}
