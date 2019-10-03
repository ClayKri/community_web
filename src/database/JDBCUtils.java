package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {

    private static final String DRIVER = "com.mysql.jbdc.Driver";
    private static final String URL = "jbdc:mysql://localhost:3306/forum?characterEncoding=utf-8";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    private Connection connection;

    public JDBCUtils() {
        try {
            Class.forName(DRIVER);
            // connect database success
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
