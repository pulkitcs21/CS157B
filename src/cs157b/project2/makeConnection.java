package cs157b.project2;


import java.sql.*;

import javax.swing.SortingFocusTraversalPolicy;

import java.io.*;
/**
 * Logs in and makes connection
 */
public class makeConnection {

	// Change the userName and Password According to your database
	private static final String USERNAME = "root";
	private static final String PASSWORD= "123";
	private static final String Conn_String= "jdbc:sqlite:/Users/jennifernghinguyen/CS157B/database/157b.db";

	/**
	 * Connects to the database, and returns said connection
	 * @return conn, the connection to the database
	 */
	public static Connection getconnection() {
		Connection conn = null;
		try {
			System.out.println("Creating Database..");
			conn = DriverManager.getConnection(Conn_String);
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return conn;

	}

}