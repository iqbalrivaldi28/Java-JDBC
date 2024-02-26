package com.enigma.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static final String URL = "jdbc:postgresql://localhost:5432/db_enigma";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres123";


    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connect to Postgres SQL is Succesfull");
        } catch (SQLException e){
            System.out.println(e.getMessage());

        }

        return conn;
    }
}
