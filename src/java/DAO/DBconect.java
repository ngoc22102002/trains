/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author phamn
 */
public class DBconect {
      protected Connection connection;

    public DBconect() {
        try {
            String url="jdbc:sqlserver://localhost:1433;databaseName=phamngoc";
            String username="sa";
            String password="123";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");              
            connection =DriverManager.getConnection(url,username,password);
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }
}
