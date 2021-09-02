package jdbc.starter.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

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
