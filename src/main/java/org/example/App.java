package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");
        Connection connection = getConnection();
        System.out.println(connection);
    }

    private static Properties loadProperties() throws IOException {
        String dbConfigFilePath = "db-config.properties";
        InputStream is = App.class.getClassLoader().getResourceAsStream(dbConfigFilePath);
        Properties properties = new Properties();
        properties.load(is);
        return properties;
    }

    private static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        Properties properties = loadProperties();
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");
        String dbUrl = properties.getProperty("db.url");
        String driverClass = properties.getProperty("db.driver-class");

        Class.forName(driverClass);
        Connection connection = DriverManager.getConnection(dbUrl, username, password);
        return connection;
    }
}
