package com.qcz.qmplatform.common.utils;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.DES;

/**
 * 加解密工具
 */
public class SecureUtils {

    public static final String PASSWORD_UNCHANGED = "******";

    /**
     * MD5 加密盐
     */
    private static final String MD5_SALT = "qmplatform";

    private static final MD5 MD5_INSTANCE = new MD5(MD5_SALT.getBytes());

    private static final AES AES_INSTANCE = SecureUtil.aes(ConfigLoader.getAesKey().getBytes());

    private static final DES DES_INSTANCE = SecureUtil.des(ConfigLoader.getDesKey().getBytes());

    private static final RSA RSA_INSTANCE = SecureUtil.rsa(ConfigLoader.getRsaPrivateKey(), ConfigLoader.getRsaPublicKey());

    /**
     * 账号加密
     *
     * @param pwd 待加密的字符串
     * @return 加密后的字符串
     */
    public static String accountEncrypt(String pwd) {
        return md5Encrypt(pwd);
    }

    /**
     * MD5加密
     *
     * @param pwd  待加密的字符串
     * @return 加密后的字符串
     */
    public static String md5Encrypt(String pwd) {
        return MD5_INSTANCE.digestHex(pwd);
    }

    /**
     * AES加密
     *
     * @param str 待加密的字符串
     * @return 加密后的字符串
     */
    public static String aesEncrypt(String str) {
        return AES_INSTANCE.encryptBase64(str);
    }

    /**
     * AES解密
     *
     * @param str 待解密的字符串
     * @return 解密后的字符串
     */
    public static String aesDecrypt(String str) {
        return AES_INSTANCE.decryptStr(str);
    }

    /**
     * DES加密
     *
     * @param str 待加密的字符串
     * @return 加密后的字符串
     */
    public static String desEncrypt(String str) {
        return DES_INSTANCE.encryptBase64(str);
    }

    /**
     * DES解密
     *
     * @param str 待解密的字符串
     * @return 解密后的字符串
     */
    public static String desDecrypt(String str) {
        return DES_INSTANCE.decryptStr(str);
    }

    /**
     * RSA加密
     *
     * @param str 待加密的字符串
     * @return 加密后的字符串
     */
    public static String rsaEncrypt(String str) {
        return RSA_INSTANCE.encryptBase64(str, KeyType.PublicKey);
    }

    /**
     * RSA解密
     *
     * @param str 待解密的字符串
     * @return 解密后的字符串
     */
    public static String rsaDecrypt(String str) {
        return RSA_INSTANCE.decryptStr(str, KeyType.PrivateKey);
    }

    /**
     * 扩展使用其它私钥的RSA解密
     *
     * @param str 待解密的字符串
     * @return 解密后的字符串
     */
    public static String rsaDecrypt(String privateKey, String str) {
        return SecureUtil.rsa(privateKey, null).decryptStr(str, KeyType.PrivateKey);
    }

    /**
     * 密码是否发生变化
     *
     * @param pwd the password
     */
    public static boolean passwordChanged(String pwd) {
        return !StringUtils.equals(PASSWORD_UNCHANGED, pwd);
    }

}
