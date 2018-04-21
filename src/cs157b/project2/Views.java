package cs157b.project2;


import java.sql.*;

public class Views {
	
	private Connection conn;
	public Views (Connection conn){
		this.conn = conn;
	}
	
	
	/*
	 * user-specific view
	 * create view such as payment_method_user_1
	 */
	public void payment_method_user(int user_id ){
		Statement stmt = null;
		String drop = "DROP VIEW IF EXISTS payment_method_user_"+user_id+"; ";
		String stmtString = " CREATE VIEW payment_method_user_"+user_id+ 
							" AS "+
								" SELECT credit_debit.payment_id, credit_debit.name_on_card, credit_debit.card_number, credit_debit.cvv, credit_debit.exp_date, card_type.card_type_name "+
								" FROM credit_debit, card_type, user_payment "+
								" WHERE credit_debit.card_type_id = card_type.card_type_id and credit_debit.payment_id=user_payment.payment_id and user_payment.user_id="+user_id+";  ";
		
		 try {
			 stmt = conn.createStatement();
			 stmt.executeUpdate(drop);
			 stmt.executeUpdate(stmtString);
			 stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	
	
	/*
	 * user-specific view
	 * create view such as shopping_cart_user_1
	 */
	public void shopping_cart_user(int user_id ){
		Statement stmt = null;
		String drop = "DROP VIEW IF EXISTS shopping_cart_user_"+user_id+"; ";
		String stmtString = " CREATE VIEW shopping_cart_user_"+user_id+ 
							" AS "+
								" SELECT product.product_name, shopping_cart.amount, (shopping_cart.amount * product.product_price) as price "+
								" FROM `order`, shopping_cart, product "+
								" WHERE `order`.user_id = "+user_id+" and `order`.order_status='Pending' and `order`.order_id = shopping_cart.order_id and shopping_cart.product_id=product.product_id;  ";
		
		 try {
			stmt = conn.createStatement();
			stmt.executeUpdate(drop);
			 stmt.executeUpdate(stmtString);
			 stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	/*
	 * user-specific view
	 * create view such as history_order_user_1
	 */
	public void history_order_user(int user_id ){
		Statement stmt = null;
		String drop = "DROP VIEW IF EXISTS history_order_user_"+user_id+"; ";
		String stmtString = " CREATE VIEW history_order_user_"+user_id+ 
							" AS "+
								" SELECT `order`.order_id, product.product_name, purchased_order.amount, (purchased_order.amount * product.product_price) as price "+
								" FROM `order`, purchased_order, product "+
								" WHERE `order`.user_id = "+user_id+" and `order`.order_status='Paid' and `order`.order_id = purchased_order.order_id and purchased_order.product_id=product.product_id;  ";
		
		 try {
			stmt = conn.createStatement();
			stmt.executeUpdate(drop);
			 stmt.executeUpdate(stmtString);
			 stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	
	/*
	 * card type view
	 * 
	 */
	public void card_type_view(){
		Statement stmt = null;
		String drop = "DROP VIEW IF EXISTS card_type_view";
		String stmtString = " CREATE VIEW card_type_view "+ 
							" AS "+
								" SELECT * "+
								" FROM card_type; ";		
		 try {
			stmt = conn.createStatement();
			stmt.executeUpdate(drop);
			 stmt.executeUpdate(stmtString);
			 stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}
