package com.revature.ers.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Connection;

public class ConnectionFactory {
    private static ConnectionFactory connectionFactory;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Properties props = new Properties();

    private ConnectionFactory() {
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();

            props.load(loader.getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }
        return connectionFactory;
    }

    public Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(
                props.getProperty("db-url"),
                props.getProperty("db-username"),
                props.getProperty("db-password")
        );

        if (conn == null) {
            throw new RuntimeException("Could not establish a connection to the database.");
        }

        return conn;
    }
}