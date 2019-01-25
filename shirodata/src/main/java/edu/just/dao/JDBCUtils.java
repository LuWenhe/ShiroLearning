package edu.just.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JDBCUtils {

    public static Connection getConnection() {
        try {
            InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties properties = new Properties();

            properties.load(in);

            String driverClass = properties.getProperty("driver");
            String jdbcUrl = properties.getProperty("jdbcUrl");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");

            Class.forName(driverClass);

            Connection connection = DriverManager.getConnection(jdbcUrl, user, password);

            return connection;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
