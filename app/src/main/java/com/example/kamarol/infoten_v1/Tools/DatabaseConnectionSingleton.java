package com.example.kamarol.infoten_v1.Tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by kamar on 2018-03-12.
 */

public class DatabaseConnectionSingleton {
    private static DatabaseConnectionSingleton instance;
    private Connection connection;
    private String url = "jdbc:mysql://ec2-18-217-42-15.us-east-2.compute.amazonaws.com:3306/infoten";
    private String username = "infoten";
    private String password = "infoten123";

    private DatabaseConnectionSingleton() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseConnectionSingleton getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnectionSingleton();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnectionSingleton();
        }

        return instance;
    }
}
