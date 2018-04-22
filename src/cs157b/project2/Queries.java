package cs157b.project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Queries {
	
	public static void selectStatements()
	{
		 Connection conn = null;
		 Statement statement = null;
		try
		{
			//connection to do inserts

			conn = DriverManager.getConnection("jdbc:sqlite:C:/sqlite/db/157b.db");

			statement = conn.createStatement();
			
			ResultSet one = statement.executeQuery("select product.product_name, "
					+ "type.type_name, brand.brand_name, product.product_price,"
					+ "product.stock_amount, product.product_description FROM product, type, brand WHERE "
					+ "product.type_id = type.type_id AND product.brand_id = brand.brand_id AND product.product_id = 1;");
			
			System.out.println("select product.product_name, type.type_name, brand.brand_name, product.product_price,"
					+ "product.stock_amount, product.product_description FROM product, type, brand WHERE "
					+ "product.type_id = type.type_id AND product.brand_id = brand.brand_id AND product.product_id = 1;");
			 
			System.out.printf("%-25s %-25s %-25s %-25s %-25s %-25s \n", "product.product_name", "type.type_name", "brand.brand_name", "product.product_price",
					"product.stock_amount", "product.product_description");
			
			
			while (one.next()) 
			{
               System.out.printf("%-25s %-25s %-25s %-25s %-25s %-25s \n", one.getString("product_name"), one.getString("type_name"), one.getString("brand_name"), 
            		   one.getString("product_price"), one.getString("stock_amount"), one.getString("product_description") );
            }
			
			System.out.println();
			
			ResultSet two = statement.executeQuery("select product.product_name, shopping_cart.amount, (shopping_cart.amount * product.product_price) AS item_price FROM product, [order], shopping_cart WHERE shopping_cart.order_id = [order].order_id AND shopping_cart.product_id = product.product_id AND [order].order_id = 2;");
			System.out.println("select product.product_name, shopping_cart.amount, (shopping_cart_amount * product.product_price) AS item_price"
					+ "from product, [order], shopping_cart WHERE shopping_cart.order_id = [order].order_id AND shopping_cart.product_id = product_product.id"
					+ "AND [order].order_id =2;");
			
			System.out.printf("%-15s %-15s %-15s\n", "product.product_name", "shopping_cart.amount", "item_price");
			
			while(two.next())
			{
				System.out.printf("%-15s %-15s %-15s\n", two.getString("product_name"), two.getString("amount"), two.getString("item_price"));
			}
			System.out.println();
			
			ResultSet three = statement.executeQuery("select count(order_id) as item_count from shopping_cart where order_id = 2 group by order_id;");
			System.out.println("select count(order_id) as item_count from shopping_cart where order_id = 2 group by order_id;");
			System.out.printf(" %-15s\n", "item_count");
			
			while(three.next())
			{
				System.out.printf(" %-15s\n", three.getString("item_count"));
			}
			
			System.out.println();
			
			ResultSet four = statement.executeQuery("Select * from product order by product_description;");
			System.out.println("Select * from product order by product_description;");
			System.out.printf(" %-15s %-15s %-15s %-15s %-15s %-15s %-15s\n", "product_id", "type_id", "brand_id", "product_name", "product_price", "product_description", "stock_amount");
			
			while(four.next())
			{
				System.out.printf(" %-15s %-15s %-15s %-15s %-15s %-15s %-15s\n", four.getString("product_id"), four.getString("type_id"), four.getString("brand_id"),
						four.getString("product_name"), four.getString("product_price"), four.getString("product_description"), four.getString("stock_amount"));
			}
			
			System.out.println();
		
			ResultSet five = statement.executeQuery("select shoppingCart.product_id, "
					+ "theProduct.product_id, theProduct.product_name, theProduct.product_price FROM shopping_cart as shoppingCart INNER JOIN product AS theProduct ON shoppingCart.product_id = theProduct.product_id WHERE shoppingCart.product_id = '1'; ");
			System.out.println("select shoppingCart.product_id, theProduct.product_id, theProduct.product_name, theProduct.product_price FROM shopping_cart as shoppingCart INNER JOIN product AS theProduct ON shoppingCart.product_id = theProduct.product_id WHERE shoppingCart.product_id = '1'; ");
			System.out.printf(" %-15s %-15s %-15s \n", "product_id", "product_name", "product_price");
			
			while(five.next())
			{
				System.out.printf(" %-15s %-15s %-15s \n", five.getString("product_id"), five.getString("product_name"), five.getString("product_price"));
			}
			
			System.out.println();
			
		ResultSet six = statement.executeQuery("Select count(amount) as totalQuantity from shopping_cart;");
		System.out.println("Select count(amount) as totalQuantity from shopping_cart;");
		
		System.out.printf(" %-15s \n", "totalQuantity");
		
		while(five.next())
		{
			System.out.printf(" %-15s \n", six.getString("totalQuantity"));
		}
		
		System.out.println();
		
		
		ResultSet seven = statement.executeQuery("select * from product where product_price between 0.99 and 5.99;");
		System.out.println("select * from product where product_price between 0.99 and 5.99;");
		
		System.out.printf(" %-15s %-15s %-15s %-15s %-15s %-15s %-15s \n", "product_id", "type_id", "brand_id", "product_name", "product_price", "product_description", "stock_amount");
		
		while(six.next())
		{
			System.out.printf(" %-15s %-15s %-15s %-15s %-15s %-15s %-15s \n", six.getString("product_id"), six.getString("type_id"), six.getString("brand_id"), six.getString("product_name"), six.getString("product_price"), six.getString("product_description"), six.getString("stock_amount"));
		}
			
			
		}
		catch (SQLException i) 
		{
	        //System.out.println("Query for initializing tables found. Please check your file formatting.");
			i.printStackTrace();
	    }
	}
	
	public static void selectAll(String table_name, Connection conn){
		Statement stmt = null;
		String query = "select * from "+ table_name+"; ";
		int col = 0;
		
		try {
			stmt = conn.createStatement();
			ResultSet re = stmt.executeQuery(query);
			ResultSetMetaData rsmd = re.getMetaData();
			for (int i = 1; i <= rsmd.getColumnCount(); i++)
			{
				System.out.print(rsmd.getColumnName(i) + " |  ");
				col += 1;
			}
			System.out.println("\n--------------------------------------------------------------------");
			 while(re.next()){

		         for (int i = 1; i <= col; i++){
		        	 
		        	 if(i == col){
		        		 System.out.print(re.getString(i) + "\n");
		        	 }else{
		        		 System.out.print(re.getString(i) +"  |  " );
		        	 }
		         }
		         
		         
		      }
			 System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
