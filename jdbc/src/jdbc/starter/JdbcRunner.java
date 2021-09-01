package jdbc.starter;

import jdbc.starter.util.ConnectionManager;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcRunner {

    public static void main(String[] args) throws SQLException {
        Class<Driver> driver = Driver.class;
        try(var connection = ConnectionManager.open()){
            System.out.println(connection.getTransactionIsolation());
        }
    }

}
