package com.github.nikhrom.javatraining.jdbc.starter;

import com.github.nikhrom.javatraining.jdbc.starter.util.ConnectionManager;
import org.postgresql.Driver;

import java.sql.SQLException;

public class JdbcRunner {

    public static void main(String[] args) throws SQLException {
        Class<Driver> driver = Driver.class;
        try(var connection = ConnectionManager.open()){
            System.out.println(connection.getTransactionIsolation());
        }
    }

}
