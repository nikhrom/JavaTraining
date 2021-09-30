package com.github.nikhrom.javatraining.http.practice.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.*;
import java.util.Properties;
import java.util.stream.Stream;

@UtilityClass
public class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();

    static{
        loadProperties();
    }

    @SneakyThrows
    private static void loadProperties(){
        try(var resource = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(resource);
        }
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

}
