package com.qcz.qmplatform.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigLoader {

    private static final Logger logger = LoggerFactory.getLogger(ConfigLoader.class);

    private static final IniFile configFile = IniFileUtils.getConfigFile();

    private static final String SECTION_COMMON = "Common";

    private static String getStringConfig(String propertyName, String defaultValue) {
        return configFile.getStringProperty(SECTION_COMMON, propertyName, defaultValue);
    }

    private static String getStringConfig(String propertyName) {
        return getStringConfig(propertyName, null);
    }

    private static Long getLongConfig(String propertyName, Long defaultValue) {
        return configFile.getLongProperty(SECTION_COMMON, propertyName, defaultValue);
    }

    private static Long getLongConfig(String propertyName) {
        return getLongConfig(propertyName, null);
    }

    private static Integer getIntConfig(String propertyName, Integer defaultValue) {
        return configFile.getIntegerProperty(SECTION_COMMON, propertyName, defaultValue);
    }

    private static Integer getIntConfig(String propertyName) {
        return getIntConfig(propertyName, null);
    }

    /**
     * token离过期还剩多长时间续期，单位：秒
     * <p>默认1200秒（20分钟）</p>
     *
     * @return the value of expire time
     */
    public static Long getTokenWillExpTime() {
        Long tokenWillExpTime = getLongConfig("TokenWillExpTime", 1200L);
        if (tokenWillExpTime < 600) {
            logger.warn("the expiration time is less than {} and is set to 600", tokenWillExpTime);
            return 600L;
        } else if (tokenWillExpTime > 7200) {
            logger.warn("the expiration time is higher than {} and is set to 7200", tokenWillExpTime);
            return 7200L;
        }
        return tokenWillExpTime;
    }
}
