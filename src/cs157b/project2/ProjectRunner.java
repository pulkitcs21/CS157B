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
			// insert raw data
			Insert insert = new Insert(con);
			insert.insertIntoTables();
			System.out.println("Insertion Complete");
			System.out.println("=====================================================================================");

			// Run Queries
			Queries queries = new Queries(con, stmt);
			queries.selectStatements();
			
			
			// create views
			Views views = new Views(con);
			views.card_type_view();
			views.payment_method_user(1);
			views.shopping_cart_user(1);
			views.history_order_user(1);

			// create trigger
			Triggers tg = new Triggers(con);
			tg.insert1_payment_method_user(1);
			tg.insert2_payment_method_user(1);
			tg.delete_payment_method_user(1);

			// uid

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
