package github.nikhrom.javatraining.junit.dao;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

public class UserDao {

    @SneakyThrows
    public boolean delete(Integer userId){
        try (Connection connection = DriverManager.getConnection("url", "userName", "example")) {
            return true;
        }
    }

}
