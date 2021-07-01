package com.qcz.qmplatform.gateway.utils;

import cn.hutool.json.JSONUtil;
import com.qcz.qmplatform.common.bean.TokenPayload;
import com.qcz.qmplatform.common.constant.Constant;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.interfaces.RSAPublicKey;

public class JwtDecoder {

    private static final RsaVerifier VERIFIER;

    static {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(Constant.KEYSTORE_FILE), Constant.SIGN_PASSWORD.toCharArray());
        RSAPublicKey publicKey = (RSAPublicKey) keyStoreKeyFactory.getKeyPair(Constant.SIGN_ACCOUNT).getPublic();
        VERIFIER = new RsaVerifier(publicKey);
    }

    /**
     * 解密Token
     *
     * @param token 加密的token
     * @return token载荷信息
     */
    public static TokenPayload decodeToken(String token) {
        String payload = JwtHelper.decodeAndVerify(token, VERIFIER).getClaims();
        return JSONUtil.toBean(payload, TokenPayload.class);
    }

}
