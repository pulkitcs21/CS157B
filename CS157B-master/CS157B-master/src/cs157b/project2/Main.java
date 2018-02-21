package cs157b.project2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

	 public static void main(String[] args) {
		 System.out.println("Creating connection");
		Connection con= makeConnection.getconnection();
		System.out.println("Connection Created");
		
		Table table;
		try {
			table = new Table(con.createStatement(), con);
			table.createtable();
			System.out.println("-------Tables created---------");
			
			//Inserting
			System.out.println("Try to insert");
			Insert.insertIntoTables();
			System.out.println("-----Insert Completed-----");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Create statements failed");
		}
		
	}


}
