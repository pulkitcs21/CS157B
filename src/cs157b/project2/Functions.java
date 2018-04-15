package cs157b.project2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Functions {
	private Connection conn;
	public Functions (Connection conn){
		this.conn = conn;
	}
	
	
	public void createFuction_isThisOrderPaid(){
		Statement stmt =null;
		
		 try {
			stmt = conn.createStatement();
			String drop = "drop function if exists is_this_order_paid";
			String query = " CREATE FUNCTION is_this_order_paid(orderId int(11)) "+
						   " RETURNS int "+
						   " BEGIN "+
						   " 	declare status_ret varchar(20); "+
						   "	declare val int; "+
						   " 	set status_ret = (select order_status from `order` where order_id=orderId); "+
						   "	if status_ret = 'Paid' then "+
						   "		set val = 1; "+
						   "	ELSE "+
						   " 		set val = 0; "+
						   "	end if; "+
						   "	return val;	"+
						   "END";
			stmt.executeUpdate(drop);
			stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Functions function = new Functions(DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/157b?useSSL=false", "root", "123"));
			function.createFuction_isThisOrderPaid();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
