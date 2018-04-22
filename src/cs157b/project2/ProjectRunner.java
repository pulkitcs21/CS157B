package cs157b.project2;

import java.sql.*;

public class ProjectRunner {

	public static void main(String[] args) {
		// create connection
		try {
			Connection con = makeConnection.getconnection();
			Statement stmt;
			stmt = con.createStatement();

			// create table
			Table table;
				table = new Table(stmt, con);
				table.createtable();

				System.out.println("-------Tables created---------");
				System.out.println("=====================================================================================");
			System.out.println("Inserting to Database");
			
			
			//insert raw data
			Insert insert = new Insert(con);
			insert.insertIntoTables();
			System.out.println("Insertion Complete");
			System.out.println("=====================================================================================");

			// Run Queries
			Queries queries = new Queries(con, stmt);
			queries.selectStatements();
			
			

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
			Queries.selectAll("user_payment", con);
			System.out.println("INSERT WITH AN EXISTING CARD TYPE - Visa card id 1");
			UID.insert_payment_method_user(1,"test user 2", "987654567821", "123", "06/21", "Visa card", con);
			Queries.selectAll("credit_debit", con);
			Queries.selectAll("user_payment", con);
			System.out.println("DELETE A PAYMENT -- payment_id=12");
			UID.delete_payment_method_user(1, 12, con);
			Queries.selectAll("credit_debit", con);
			Queries.selectAll("user_payment", con);
			
			System.out.println("------------------shopping_cart_user_1-------------");
			System.out.println("INSERT ITEMS IN SHOPPING CART");
			UID.insert_shopping_cart_user(1, "watermelon", 10, con);
			UID.insert_shopping_cart_user(1, "forks", 10, con);
			UID.insert_shopping_cart_user(1, "cookie", 10, con);
			Queries.selectAll("shopping_cart_user_1", con);
			Queries.selectAll("`order`", con);
			System.out.println("UPDATE AMOUNT IN SHOPPING CART");
			UID.update_shopping_cart_user(1, "cookie", 5, con);
			Queries.selectAll("shopping_cart_user_1", con);
			Queries.selectAll("`order`", con);
			System.out.println("DELETE ITEMS IN SHOPPING CART");
			UID.delete_shopping_cart_user(1, "cookie", con);
			Queries.selectAll("shopping_cart_user_1", con);
			Queries.selectAll("`order`", con);
			
			System.out.println("------------------history_order_user_1-------------");
			System.out.println("CHECKOUT FOR USER ID 1");
			UID.insert_history_order_user(1, 1, con);
			System.out.println("-----shopping_cart-----");
			Queries.selectAll("shopping_cart_user_1", con);
			System.out.println("-----order-----");
			Queries.selectAll("`order`", con);
			System.out.println("-----purchased_order-----");
			Queries.selectAll("purchased_order", con);
			
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Create statements failed");
			
		}
			
			
		

	}

}
