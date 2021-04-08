package com.qcz.qmplatform.common.utils;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;

public class ValidateCodeCacheUtils {

    private static final TimedCache<String, String> validateCodeCache = CacheUtil.newTimedCache(300 * 1000L);

    public static String get(String loginName) {
        return validateCodeCache.get(loginName);
    }

    public static void set(String loginName, String validateCode) {
        validateCodeCache.put(loginName, validateCode);
    }
}
