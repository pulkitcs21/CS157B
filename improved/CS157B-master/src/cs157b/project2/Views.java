package cs157b.project2;


import java.sql.*;

public class Views {
	
	private Connection conn;
	public Views (Connection conn){
		this.conn = conn;
	}

	public void shopping_cart_summary(int order_id, int user_id ){
		PreparedStatement stmt = null;
		String drop ="drop view if exists shopping_cart_summary_user"+"_"+user_id;
		String stmtString = " create view shopping_cart_summary_user"+"_"+user_id+ 
							" as "+
								"select product_name, amount, product_price, FORMAT(product_price * amount, 2) as total_product_price "+
								"from shopping_cart, product "+
								"where order_id= ? and shopping_cart.product_id=product.product_id; ";
		
		 try {
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(drop);
			 stmt.executeUpdate();
			 stmt = conn.prepareStatement(stmtString);
			 stmt.setInt(1, order_id);
			
			 stmt.executeUpdate();
			 stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Views v = new Views(DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/157b?useSSL=false", "root", "123"));
			v.shopping_cart_summary(3,2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
