package cs157b.project2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Triggers {

	private Connection conn;
	public Triggers (Connection conn){
		this.conn = conn;
	}
	
	/*
	 * view: payment_method_user_USER_ID
	 * on insert
	 * case: card type already exists --> insert into credit_debit
	 */
	
	public void insert1_payment_method_user(int user_id){
		Statement stmt = null;
		String drop = "DROP TRIGGER IF EXISTS insert1_payment_method_user_"+user_id+"; ";
		String stmtString = " CREATE TRIGGER insert1_payment_method_user_"+user_id+"  INSTEAD OF INSERT ON payment_method_user_"+user_id + 
							" WHEN (SELECT count(*) FROM card_type WHERE card_type_name = NEW.card_type_name) = 1 "+
								" BEGIN  "+
								" 		INSERT INTO credit_debit (name_on_card, card_number, exp_date, cvv, card_type_id) "+
								"		SELECT NEW.name_on_card, NEW.card_number, NEW.exp_date, NEW.cvv, card_type.card_type_id  FROM card_type WHERE card_type_name = NEW.card_type_name; " +
								"END; ";
		
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
	
	public void insert2_payment_method_user(int user_id){
		Statement stmt = null;
		String drop = "DROP TRIGGER IF EXISTS insert2_payment_method_user_"+user_id+"; ";
		String stmtString = " CREATE TRIGGER insert2_payment_method_user_"+user_id+"  INSTEAD OF INSERT ON payment_method_user_"+user_id + 
							" WHEN (SELECT count(*) from card_type where card_type_name =NEW.card_type_name) <> 1 or (select count(*) from card_type where card_type_name =NEW.card_type_name) is null "+
								" BEGIN  "+
								" 		insert into card_type_view(card_type_name)  values (NEW.card_type_name) ; "+
								"END; ";
		
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
	
	public void delete_payment_method_user(int user_id){
		Statement stmt = null;
		String drop = "DROP TRIGGER IF EXISTS delete_payment_method_user_"+user_id+"; ";
		String stmtString = " CREATE TRIGGER delete_payment_method_user_"+user_id+"  INSTEAD OF delete ON payment_method_user_"+user_id + 
								" BEGIN  "+
								" 		delete "+
								" 		from credit_debit "+
								" 		where credit_debit.payment_id=OLD.payment_id; "+
								"END; ";
		
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

}
