package cs157b.project2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public final class Triggers {

	
	/*
	 * view: payment_method_user_USER_ID
	 * on insert
	 * case: card type already exists --> insert into credit_debit
	 */
	
	public static void insert1_payment_method_user(int user_id, Connection conn){
		Statement stmt = null;
		String drop = "DROP TRIGGER IF EXISTS insert1_payment_method_user_"+user_id+"; ";
		String stmtString = " CREATE TRIGGER insert1_payment_method_user_"+user_id+"  INSTEAD OF INSERT ON payment_method_user_"+user_id + 
							" WHEN (SELECT count(*) FROM card_type WHERE card_type_name = NEW.card_type_name) = 1 "+
								" BEGIN  "+
								" 		INSERT INTO credit_debit (name_on_card, card_number, exp_date, cvv, card_type_id) "+
								"		SELECT NEW.name_on_card, NEW.card_number, NEW.exp_date, NEW.cvv, card_type.card_type_id  FROM card_type WHERE card_type_name = NEW.card_type_name; " +
								"		insert into user_payment (payment_id, user_id) "+
								"		select payment_id, "+user_id+" from credit_debit "+
								"		where payment_id = ( select MAX(payment_id) from credit_debit); " + 
								" END; ";
		
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
	 * view: payment_method_user_USER_ID
	 * on insert
	 * case: card type not exists 
	 * --> new card type by insert into card_type_view
	 */
	
	public static void insert2_payment_method_user(int user_id , Connection conn){
		Statement stmt = null;
		String drop = "DROP TRIGGER IF EXISTS insert2_payment_method_user_"+user_id+"; ";
		String stmtString = " CREATE TRIGGER insert2_payment_method_user_"+user_id+"  INSTEAD OF INSERT ON payment_method_user_"+user_id + 
							" WHEN (SELECT count(*) from card_type where card_type_name =NEW.card_type_name) <> 1 or (select count(*) from card_type where card_type_name =NEW.card_type_name) is null "+
								" BEGIN  "+
								" 		insert into card_type_view(card_type_name)  values (NEW.card_type_name) ; "+
								" END; ";
		
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
	 * view: payment_method_user_USER_ID
	 * on delete
	 * --> delete only from table credit_debit
	 */
	
	public static void delete_payment_method_user(int user_id, Connection conn){
		Statement stmt = null;
		String drop = "DROP TRIGGER IF EXISTS delete_payment_method_user_"+user_id+"; ";
		String stmtString = " CREATE TRIGGER delete_payment_method_user_"+user_id+"  INSTEAD OF delete ON payment_method_user_"+user_id + 
								" BEGIN  "+
								" 		delete "+
								" 		from credit_debit "+
								" 		where credit_debit.payment_id=OLD.payment_id; "+
								" 		delete "+
								" 		from user_payment "+
								" 		where payment_id=OLD.payment_id; "+
								" END; ";
		
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(drop);
			 stmt.executeUpdate(stmtString);
			 stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	////////////////////
	
	/*
	 * view: card_type_view
	 * on insert
	 * condition: card_type_name is unique
	 * --> insert into card_type table 
	 */
	
	public static void insert_card_type_view(Connection conn){
		Statement stmt = null;
		String drop = "DROP TRIGGER IF EXISTS insert_card_type_view; ";
		String stmtString = " CREATE TRIGGER insert_card_type_view  INSTEAD OF INSERT ON card_type_view "+
								" when ( select count(*) from card_type where card_type_name = NEW.card_type_name ) = 0 "+
								" BEGIN  "+
								" 		insert into card_type (card_type_name) values (NEW.card_type_name); "+
								" END; ";
		
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
	 * view: card_type_view
	 * on update
	 * condition: new card_type_name is not as same as the old one
	 * --> update card_type table 
	 */
	
	public static void update_card_type_view(Connection conn){
		Statement stmt = null;
		String drop = "DROP TRIGGER IF EXISTS update_card_type_view; ";
		String stmtString = " CREATE TRIGGER update_card_type_view  INSTEAD OF update ON card_type_view "+
							" when NEW.card_type_name <> OLD.card_type_name   "+	
							" BEGIN  "+
								" 		update card_type "+
								" 		set card_type_name=NEW.card_type_name "+
								" 		where card_type_id = OLD.card_type_id or card_type_name = OLD.card_type_name; "+
								" END; ";
		
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
	 * view: card_type_view
	 * on delete
	 * --> delete from card_type table 
	 */
	
	public static void delete_card_type_view(Connection conn){
		Statement stmt = null;
		String drop = "DROP TRIGGER IF EXISTS delete_card_type_view; ";
		String stmtString = " CREATE TRIGGER delete_card_type_view  INSTEAD OF delete ON card_type_view "+
							" BEGIN  "+
								" 		delete  "+
								" 		from card_type "+
								" 		where  card_type_name = OLD.card_type_name; "+
								" END; ";
		
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(drop);
			 stmt.executeUpdate(stmtString);
			 stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	///////////////////
	
	/*
	 * view: shopping_cart_user_USER_ID
	 * on insert
	 * case: current user doesn't have any shopping cart
	 * --> new order record start with price = 0
	 * --> new record in shopping card
	 * --> update total price accordingly in order table 
	 */
	
	public static void insert1_shopping_cart_user(int user_id, Connection conn){
		Statement stmt = null;
		String drop = "DROP TRIGGER IF EXISTS insert1_shopping_cart_user_"+user_id+"; ";
		String stmtString = " CREATE TRIGGER insert1_shopping_cart_user_"+user_id+"  instead of insert on shopping_cart_user_"+user_id + 
							" when (select count(*)  from `order` where user_id = "+user_id+" and order_status='Pending') = 0 "+	
								" BEGIN  "+
								" 		insert into `order` (order_price, user_id, order_status) values (0,"+user_id+",'Pending'); "+
								" 		Insert into shopping_cart (order_id, product_id, amount) "+
								"		select order_id, product_id,  NEW.amount " +
								" 		from `order`, product "+
								" 		where `order`.user_id = "+user_id+" and `order`.order_status='Pending' and product_name = NEW.product_name; " +
								" 		update `order` " +
								" 		set order_price = (select sum((amount * product_price)) as totalprice " +
								"						   from `order`, shopping_cart, product " + 
								"						   where `order`.order_id = shopping_cart.order_id and shopping_cart.product_id = product.product_id and `order`.user_id="+user_id+")	" +
								"		where `order`.user_id = "+user_id+" and `order`.order_status='Pending'; " +
								" END; ";
		
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
	 * view: shopping_cart_user_USER_ID
	 * on insert
	 * case: current user has a pending shopping cart
	 * --> new record in shopping card
	 * --> update total price accordingly in order table 
	 */
	
	public static void insert2_shopping_cart_user(int user_id, Connection conn){
		Statement stmt = null;
		String drop = "DROP TRIGGER IF EXISTS insert2_shopping_cart_user_"+user_id+"; ";
		String stmtString = " CREATE TRIGGER insert2_shopping_cart_user_"+user_id+"  instead of insert on shopping_cart_user_"+user_id + 
							" when (select count(*)  from `order` where user_id = "+user_id+" and order_status='Pending') != 0 "+	
								" BEGIN  "+
								" 		Insert into shopping_cart (order_id, product_id, amount) "+
								"		select order_id, product_id,  NEW.amount " +
								" 		from `order`, product "+
								" 		where `order`.user_id = "+user_id+" and `order`.order_status='Pending' and product_name = NEW.product_name; " +
								" 		update `order` " +
								" 		set order_price = (select sum((amount * product_price)) as totalprice " +
								"						   from `order`, shopping_cart, product " + 
								"						   where `order`.order_id = shopping_cart.order_id and shopping_cart.product_id = product.product_id and `order`.user_id="+user_id+")	" +
								"		where `order`.user_id = "+user_id+" and `order`.order_status='Pending'; " +
								" END; ";
		
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
	 * view: shopping_cart_user_USER_ID
	 * on update: amount
	 * case: new amount is not as same as the old one, product name not change
	 * --> update amount in shopping_cart
	 * --> update total price accordingly in order table 
	 */
	
	public static void update_shopping_cart_user(int user_id, Connection conn){
		Statement stmt = null;
		String drop = "DROP TRIGGER IF EXISTS update_shopping_cart_user_"+user_id+"; ";
		String stmtString = " CREATE TRIGGER update_shopping_cart_user_"+user_id+"  instead of update on shopping_cart_user_"+user_id + 
							" when NEW.amount <> OLD.amount and NEW.amount > 0 and NEW.product_name=OLD.product_name "+	
								" BEGIN  "+
								" 		update shopping_cart "+
								" 		set amount = NEW.amount "+
								" 		where  order_id = (	select order_id "+
								" 							from `order` "+
								" 							where user_id = "+user_id+" and order_status='Pending') "+
								" 			   and product_id= (select product_id "+
								" 								from product "+			
								"								where product_name=OLD.product_name); "+
								" 		update `order` "+
								" 		set order_price = (select sum((amount * product_price)) as totalprice "+
								" 						   from `order`, shopping_cart, product "+
								" 						   where `order`.order_id = shopping_cart.order_id and shopping_cart.product_id = product.product_id and `order`.user_id="+user_id+") "+
								" 		where `order`.user_id = "+user_id+" and `order`.order_status='Pending'; " +
								" END; ";
		
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
	 * view: shopping_cart_user_USER_ID
	 * on delete an item
	 * case: new amount is not as same as the old one, product name not change
	 * --> update amount in shopping_cart
	 * --> update total price accordingly in order table 
	 */
	
	public static void delete_shopping_cart_user(int user_id, Connection conn){
		Statement stmt = null;
		String drop = "DROP TRIGGER IF EXISTS delete_shopping_cart_user_"+user_id+"; ";
		String stmtString = " CREATE TRIGGER delete_shopping_cart_user_"+user_id+"  instead of delete on shopping_cart_user_"+user_id + 
								" BEGIN  "+
								" 		delete "+
								" 		from shopping_cart "+
								" 		where  order_id = (	select order_id "+
								" 							from `order` "+
								" 							where user_id = "+user_id+" and order_status='Pending')  "+
								"				and product_id= (select product_id "+
								" 								 from product	"+		
								" 								 where product_name=OLD.product_name); "+														
								" 		update `order` "+
								" 		set order_price = (select sum((amount * product_price)) as totalprice "+
								" 						   from `order`, shopping_cart, product "+
								" 						   where `order`.order_id = shopping_cart.order_id and shopping_cart.product_id = product.product_id and `order`.user_id="+user_id+") "+
								" 		where `order`.user_id = "+user_id+" and `order`.order_status='Pending'; " +
								" END; ";
		
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(drop);
			 stmt.executeUpdate(stmtString);
			 stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	//////////////////////////////
	
	/*
	 * view: history_order_user_USER_ID
	 * on insert
	 * condition: current user has a pending shopping cart
	 * --> new record in purchased_order (as same as ones in shopping_cart)
	 * --> delete current records of the order in shopping_cart
	 * --> update corresponding order_status to Paid
	 */
	
	public static void insert1_history_order_user(int user_id, Connection conn){
		Statement stmt = null;
		String drop = "DROP TRIGGER IF EXISTS insert1_history_order_user_"+user_id+"; ";
		String stmtString = " CREATE TRIGGER insert1_history_order_user_"+user_id+"  instead of insert on history_order_user_"+user_id + 
							" when (select count(*) from `order` where user_id="+user_id+" and order_status='Pending') = 1 "+	
							" BEGIN  "+
							" 		insert into purchased_order (order_id, product_id, amount) " +
							" 		select order_id, product_id, amount from shopping_cart "+
							" 		where order_id = NEW.order_id; "+
								
							" 		delete from shopping_cart "+
							" 		where order_id = NEW.order_id; "+
	
							" 		update `order` "+
							" 		set order_status = 'Paid' "+
							" 		where order_id = NEW.order_id; " +
							" END; ";
		
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
