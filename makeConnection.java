package cs157b.project2;


import java.sql.*;

import javax.swing.SortingFocusTraversalPolicy;

import java.io.*;

public class makeConnection {
	
	// Change the userName and Password According to your database
	 private static final String USERNAME = "pulkit1";
	 private static final String PASSWORD= "pulkit";
	 private static final String Conn_String= "jdbc:mysql://localhost:3307/157b";
	 
	 
	 public static Connection getconnection() {
	        Connection conn = null;
	        try {
	            System.out.println("Creating Database..");
	            conn = DriverManager.getConnection(Conn_String, USERNAME, PASSWORD);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }catch (Exception e) {
	            e.printStackTrace();
	        }
	        return conn;

	    }
//	 public static void main(String[] args) {
//		 System.out.println("Creating connection");
//		Connection con= makeConnection.getconnection();
//		System.out.println("Connection Created");
//		
//	}
}