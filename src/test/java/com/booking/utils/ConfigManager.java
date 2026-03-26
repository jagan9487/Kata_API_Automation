package com.booking.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigManager {
    private static final Properties prop;

    static {
        prop = new Properties();
        try {
            prop.load(new FileInputStream("src/test/resources/config.properties"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }
}