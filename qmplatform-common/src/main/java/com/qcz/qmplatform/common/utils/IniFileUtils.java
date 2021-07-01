package com.qcz.qmplatform.common.utils;

import cn.hutool.setting.Setting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IniFileUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(IniFileUtils.class);

    /**
     * config.ini配置文件路径
     */
    private static final String CONFIG_FILE_PATH = FileUtils.WEB_PATH + "/config/config.ini";

    private static Setting configIniFile;

    static {
        FileUtils.createIfNotExists(CONFIG_FILE_PATH);
        configIniFile = new Setting(CONFIG_FILE_PATH);
        configIniFile.autoLoad(true);
    }

    /**
     * 获取config.ini配置文件实例
     *
     * @return
     */
    public static Setting getConfigFile() {
        return configIniFile;
    }
}
