package com.github.nikhrom.javatraining.jdbc.starter.util;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionManager {

    private static final String URL_KEY = "db.url";
    private static final String USER_KEY = "db.user";
    private static final String PASSWORD_KEY = "db.password";
    private static final String POOL_SIZE_KEY = "db.pool.size";
    private static final Integer DEFAULT_POOL_SIZE = 10;
    private static BlockingQueue<Connection> pool;


    static {
        loadDriver();
        initConnectionPool();
    }

    private ConnectionManager(){}

    private static void initConnectionPool() {
        String poolSize = PropertiesUtil.get(POOL_SIZE_KEY);
        Integer size = poolSize == null ? DEFAULT_POOL_SIZE: Integer.valueOf(poolSize);
        pool = new ArrayBlockingQueue<Connection>(size);
        for (int i = 0; i < pool.size(); i++){
            Connection connection = open();
            Connection proxyConnection = (Connection)
                    Proxy.newProxyInstance(ConnectionManager.class.getClassLoader(), new Class[]{Connection.class},
                        (proxy, method, args) -> method.getName().equals("close")
                            ? pool.add((Connection) proxy)
                            : method.invoke(connection, args));

            pool.add(proxyConnection);
        }
    }

    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection get(){
        try {
            return pool.take();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
    }

    private static Connection open(){
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USER_KEY),
                    PropertiesUtil.get(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



/*
Соединение без Connection Pool. Соединение открывалось каждый раз, когда осуществлялся запрос
 */
//    public static Connection open(){
//        try {
//            return DriverManager.getConnection(
//                    PropertiesUtil.get(URL_KEY),
//                    PropertiesUtil.get(USER_KEY),
//                    PropertiesUtil.get(PASSWORD_KEY)
//            );
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
