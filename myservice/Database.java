package myservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    static final String DB_URL = "jdbc:mysql://localhost:3306";
    static final String USER = "root";
    static final String PASS = "root";
    private static Database mydatabase = null;
    
    private Database() {
        // Open a connection
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {		      
            String sql = "CREATE DATABASE STUDENTS";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");   	  
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    public static Database getInsance() {
        if (mydatabase == null) {
            mydatabase = new Database();
        }
        return mydatabase;
    }
     
}
