package com.github.nikhrom.javatraining.jdbc.starter;

import com.github.nikhrom.javatraining.jdbc.starter.util.ConnectionManager;
import org.postgresql.Driver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

public class JdbcRunner {

    public static void main(String[] args) throws SQLException {
        TreeSet<Integer> ints = new TreeSet<>();

        ints.add(null);


        String sql= """
                    SELECT * 
                    FROM ticket;
                    """;


        try(var connection = ConnectionManager.open();
            var statement = connection.createStatement()) {

            ResultSet executeResult = statement.executeQuery(sql);

            while(executeResult.next()) {
                System.out.println(executeResult.getLong("id"));
                System.out.println(executeResult.getString("passenger_no"));
                System.out.println(executeResult.getString( "cost"));
                System.out.println("------------");
            }
        }
    }

}
