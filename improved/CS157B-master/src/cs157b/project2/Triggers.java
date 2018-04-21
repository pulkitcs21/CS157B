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
	
	public void createTrigger_tg_purchased_shopping_cart(){
		Statement stmt =null;
		
		 try {
			stmt = conn.createStatement();
			String drop = "drop trigger if exists tg_purchased_shopping_cart;";
			String query = " create trigger tg_purchased_shopping_cart after update on `order` "+
						   " for each row "+
						   " BEGIN "+
						   " 	if NEW.`order_status`='Paid' or NEW.`order_status`='paid' then "+
						   "		delete from shopping_cart where shopping_cart.order_id = OLD.order_id; "+
						   "	end if; "+
						   " END	";
			stmt.executeUpdate(drop);
			stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void createTrigger_tg_delete_shopping_cart(){
		Statement stmt =null;
		
		 try {
			stmt = conn.createStatement();
			String drop = "drop trigger if exists tg_delete_shopping_cart;";
			String query = " create trigger tg_delete_shopping_cart after delete on `shopping_cart` "+
						   " for each row "+
						   " BEGIN "+
						   "	declare val int; "+
						   " 	set val = (select is_this_order_paid(OLD.order_id)); "+
						   "	if val = 1 then "+
						   "		insert into purchased_order(order_id, product_id, amount) values (OLD.order_id, OLD.product_id, OLD.amount); "+
						   " 	ELSE "+
						   " 		delete from `order` where order_id = OLD.order_id; "+
						   " 	end if;	"+ 
						   " END ";
			stmt.executeUpdate(drop);
			stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		try {
			Triggers tr = new Triggers(DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/157b?useSSL=false", "root", "123"));
			tr.createTrigger_tg_purchased_shopping_cart();
			tr.createTrigger_tg_delete_shopping_cart();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
