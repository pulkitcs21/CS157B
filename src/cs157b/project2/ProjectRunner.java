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
			
		//create views
			Views views = new Views(con);
			views.card_type_view();
			views.payment_method_user(1);
			views.shopping_cart_user(1);
			views.history_order_user(1);
			
		
		//create trigger
			Triggers tg = new Triggers(con);
			tg.insert1_payment_method_user(1);
			tg.insert2_payment_method_user(1);
			tg.delete_payment_method_user(1);
		
		//uid

	}

}
