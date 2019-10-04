package by.gyudenok.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {
    private final static ConnectionPool INSTANCE = new ConnectionPool();

    private Connection mConnection;

    private ConnectionPool() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
             mConnection = DriverManager.getConnection(
                     "jdbc:mysql://localhost:3306/laserhairremovalstudiodb?" +
                          "useLegacyDatetimeCode=false&amp&serverTimezone=Europe/Moscow",
                    "student","pass123"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        return mConnection;
    }
}
