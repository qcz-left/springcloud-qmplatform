package com.qcz.qmplatform.common.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 加密处理器
 *
 * @author quchangzhong
 */
public class PasswordUtils {

    private static final String PASSWORD_UNCHANGED = "******";

    private static PasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * 加密
     *
     * @param pwd
     * @return
     */
    public static String encode(String pwd) {
        return encoder.encode(pwd);
    }

    /**
     * 密码比对
     *
     * @param inputPassword   用户输入的密码
     * @param encodedPassword 数据库存储的加密密码
     * @return
     */
    public static boolean matcher(String inputPassword, String encodedPassword) {
        return encoder.matches(inputPassword, encodedPassword);
    }

    /**
     * 密码是否发生变化
     *
     * @param pwd
     * @return
     */
    public static boolean passwordChange(String pwd) {
        return !StringUtils.equals(PASSWORD_UNCHANGED, pwd);
    }
}
