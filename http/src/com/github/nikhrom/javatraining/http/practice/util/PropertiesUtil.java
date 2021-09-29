package com.github.nikhrom.javatraining.http.practice.util;

import java.io.*;
import java.util.Properties;
import java.util.stream.Stream;

public class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();

    static{
        loadProperties();
    }

    private PropertiesUtil() {

    }

    private static void loadProperties(){

        try(var resource = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

}
