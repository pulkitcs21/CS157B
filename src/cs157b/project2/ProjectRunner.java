package cs157b.project2;

import java.sql.Connection;
import java.sql.SQLException;

public class ProjectRunner {

	public static void main(String[] args) {
		// create connection
		 System.out.println("Creating connection");
		 Connection con= makeConnection.getconnection();
		 System.out.println("Connection Created");
		
		 //create table
		 Table table;
			
			try {
				table = new Table(con.createStatement(), con);
				table.createtable();
				System.out.println("-------Tables creater---------");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Create statements failed");
			}
			
			
		//insert raw data
			Insert insert = new Insert(con);
			insert.insertIntoTables();
			
		//create views + triggers on views
			Views views = new Views(con);
			views.card_type_view_routine();
			//user id = 1
			views.user_specific_views_routines(1);	
			
		//uid
			System.out.println("------------------card_type_view-------------");
			Queries.selectAll("card_type_view", con);
			System.out.println("INSERT");
			UID.insert_card_type_view("new card 1", con);
			Queries.selectAll("card_type_view", con);
			System.out.println("UPDATE");
			UID.update_card_type_view("Visa", "Visa card", con);
			Queries.selectAll("card_type_view", con);
			System.out.println("DELETE");
			UID.delete_card_type_view("new card 1", con);
			Queries.selectAll("card_type_view", con);
			
			System.out.println("------------------payment_method_user_1-------------");
			Queries.selectAll("payment_method_user_1", con);
			System.out.println("INSERT WITH NEW CARD TYPE");
			UID.insert_payment_method_user(1,"test user 1", "123456789123", "222", "03/21", "new card 1", con);
			Queries.selectAll("credit_debit", con);
			Queries.selectAll("card_type", con);
			System.out.println("INSERT WITH AN EXISTING CARD TYPE - Visa card id 1");
			UID.insert_payment_method_user(1,"test user 2", "987654567821", "123", "06/21", "Visa card", con);
			Queries.selectAll("credit_debit", con);
			System.out.println("DELETE A PAYMENT -- payment_id=12");
			UID.delete_payment_method_user(1, 12, con);
			Queries.selectAll("credit_debit", con);
			Queries.selectAll("card_type", con);
	}

}
