/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author dedhi
 */
public class DAO {
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//    static final String DB_URL = "jdbc:mysql://localhost/moviedb?"+"user=root&password=root";
//    static final String USER = "student";
//    static final String PASSWORD = "p@ssw0rd";
     Connection conn = null;
    private Connection connection;

    public Connection getConnection(String DB_URL, String USER, String PASSWORD) throws Exception {
        try {
            Class.forName(JDBC_DRIVER);
            this.conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
        } catch (SQLException ex) {
            System.out.println("SQL Exception");
            ex.printStackTrace();
            throw new Exception();
        }
        return this.conn;
    }
    
}
