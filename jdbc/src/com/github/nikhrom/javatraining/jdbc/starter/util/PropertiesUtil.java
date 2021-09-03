package com.github.nikhrom.javatraining.jdbc.starter.util;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class PropertiesUtil {

    public static final Properties PROPERTIES = new Properties();

    static{
        loadProperties();
    }

    private static void loadProperties(){
//        ClassLoader.getSystemResourceAsStream("application.properties");
        /*var inputStream = Files.newInputStream(Paths.get("./application.properties"));*/

        try (var inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String get(String key){
        return PROPERTIES.getProperty(key);
    }

    private PropertiesUtil(){
    }

}
