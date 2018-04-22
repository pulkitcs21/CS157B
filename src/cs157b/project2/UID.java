package cs157b.project2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public final class UID {

	// uid for views and trigger
	 
		//card_type_view
		public static void insert_card_type_view(String card_type_name, Connection conn){
			Statement stmt = null;
			String stmtString = "insert into card_type_view(card_type_name) values ('"+card_type_name+"'); ";
			
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(stmtString);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public static void update_card_type_view(String old_card_type_name, String new_card_type_name, Connection conn){
			Statement stmt = null;
			String stmtString = " update card_type_view set card_type_name = '"+new_card_type_name + "' "+
								" where card_type_name = '"+ old_card_type_name+"'; ";
			
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(stmtString);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public static void update_card_type_view(int old_card_type_id, String new_card_type_name, Connection conn){
			Statement stmt = null;
			String stmtString = " update card_type_view set card_type_name = '"+new_card_type_name + "' "+
								" where card_type_id = "+ old_card_type_id+"; ";	
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(stmtString);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		public static void delete_card_type_view(String card_type_name, Connection conn){
			Statement stmt = null;
			String stmtString = " delete from card_type_view  "+
								" where card_type_name = '"+ card_type_name+"'; ";
			
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(stmtString);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public static void delete_card_type_view(int card_type_id, Connection conn){
			Statement stmt = null;
			String stmtString = " delete from card_type_view  "+
								" where card_type_id = "+ card_type_id+"; ";
			
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(stmtString);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		////////////////////////////
		
		//payment_method_user_USER_ID
		public static void insert_payment_method_user(int user_id, String name_on_card, String card_number, String cvv, String exp_date, String card_type_name, Connection conn){
			Statement stmt = null;
			String stmtString = "insert into payment_method_user_"+user_id+"(name_on_card, card_number, cvv, exp_date, card_type_name) "+
								" values ('"+name_on_card+"', '"+card_number+"', '"+cvv+"', '"+exp_date+"', '"+card_type_name+"' ); ";
			
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(stmtString);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		public static void delete_payment_method_user(int user_id, int payment_id, Connection conn){
			Statement stmt = null;
			String stmtString = " delete from payment_method_user_"+user_id+
								" where payment_id = "+ payment_id+"; ";
			
			try {
				System.out.println(stmtString);
				stmt = conn.createStatement();
				stmt.executeUpdate(stmtString);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
}
