package com.qcz.qmplatform.common.utils;

import cn.hutool.core.io.resource.ClassPathResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static cn.hutool.core.io.FileUtil.file;

public class FileUtils extends org.apache.commons.io.FileUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

    public static final String WEB_PATH = getWebPath();

    /**
     * 将对象写到文件
     *
     * @param obj
     * @param filePath
     */
    public static void writeObjectToFile(Object obj, String filePath) {
        writeObjectToFile(obj, new File(filePath));
    }

    /**
     * 将对象写到文件
     *
     * @param obj
     * @param file
     */
    public static void writeObjectToFile(Object obj, File file) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CloseUtils.close(oos);
            CloseUtils.close(fos);
        }
    }

    /**
     * 从文件里面读取对象
     *
     * @param filePath
     * @return
     */
    public static Object readObjectFromFile(String filePath) {
        return readObjectFromFile(new File(filePath));
    }

    public static Object readObjectFromFile(File file) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CloseUtils.close(ois);
            CloseUtils.close(fis);
        }
        return null;
    }
    /**
     * 如果文件不存在就创建，如果父目录不存在也一并创建
     */
    public static void createIfNotExists(File file) {
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                LOGGER.error(null, e);
            }
        }
    }

    public static void createIfNotExists(String filePath) {
        createIfNotExists(file(filePath));
    }

    /**
     * 如果文件夹不存在就创建
     */
    public static void createDirIfNotExists(File file) {
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static void createDirIfNotExists(String dirPath) {
        createDirIfNotExists(file(dirPath));
    }

    private static String getWebPath() {
        try {
            String webPath = new ClassPathResource("").getFile().getCanonicalPath();
            if (webPath.contains("target\\classes")) {
                return new File("../").getCanonicalPath();
            }
            return webPath;
        } catch (IOException e) {
            LOGGER.error(null, e);
        }
        return "/";
    }
}
