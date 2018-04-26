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
	private Connection conn;
	private Statement statement;
	
	public Queries(Connection conn, Statement statement) {
		this.conn = conn;
		this.statement = statement;
	}

	public void selectStatements() {
	
		try {
			statement = conn.createStatement();

			System.out.println("Query 1");
			ResultSet one = statement.executeQuery("select product.product_name, "
					+ "type.type_name, brand.brand_name, product.product_price,"
					+ "product.stock_amount, product.product_description FROM product, type, brand WHERE "
					+ "product.type_id = type.type_id AND product.brand_id = brand.brand_id AND product.product_id = 1;");
			System.out.println();
			System.out.println("select product.product_name, type.type_name, brand.brand_name, product.product_price,"
					+ "product.stock_amount, product.product_description FROM product, type, brand WHERE "
					+ "product.type_id = type.type_id AND product.brand_id = brand.brand_id AND product.product_id = 1;");
			
			System.out.printf("%-25s %-25s %-25s %-25s %-25s %-25s \n", "Product_name", "Type_name",
					"Brand_name", "Product_price", "Stock_amount", "Product_description");

			while (one.next()) {
				System.out.printf("%-25s %-25s %-25s %-25s %-25s %-25s \n", one.getString("product_name"),
						one.getString("type_name"), one.getString("brand_name"), one.getString("product_price"),
						one.getString("stock_amount"), one.getString("product_description"));
			}
			System.out.println("=====================================================================================");
			System.out.println("Query 2");
			
			ResultSet two = statement.executeQuery(
					"select product.product_name, shopping_cart.amount, (shopping_cart.amount * product.product_price) AS item_price FROM product, [order], shopping_cart WHERE shopping_cart.order_id = [order].order_id AND shopping_cart.product_id = product.product_id AND [order].order_id = 2;");
			System.out.println(
					"select product.product_name, shopping_cart.amount, (shopping_cart_amount * product.product_price) AS item_price"
							+ "from product, [order], shopping_cart WHERE shopping_cart.order_id = [order].order_id AND shopping_cart.product_id = product_product.id"
							+ "AND [order].order_id =2;");

			System.out.println();
			System.out.printf("%-15s %-15s %-15s\n", "Product_name", "shopping_cart.amount", "item_price");

			while (two.next()) {
				System.out.println("Name: "+ two.getString("product_name"));
				System.out.printf("%-15s %-15s %-15s\n", two.getString("product_name"), two.getString("amount"),
						two.getString("item_price"));
			}

			System.out.println("======================================================================================================================");
			System.out.println("Query 3");
			ResultSet three = statement.executeQuery(
					"select count(order_id) as item_count from shopping_cart where order_id = 2 group by order_id;");
			System.out.println();
			System.out.println(
					"select count(order_id) as item_count from shopping_cart where order_id = 2 group by order_id;");
			
			System.out.printf(" %-15s\n", "item_count");

			while (three.next()) {
				System.out.printf(" %-15s\n", three.getString("item_count"));
			}

			System.out.println("======================================================================================================================");
			System.out.println("Query 4");
			ResultSet four = statement.executeQuery("Select * from product order by product_description;");
			System.out.println("Select * from product order by product_description;");
			System.out.printf(" %-15s %-15s %-15s %-15s %-15s %-15s %-15s\n", "product_id", "type_id", "brand_id",
					"product_name", "product_price", "product_description", "stock_amount");

			while (four.next()) {
				System.out.printf(" %-15s %-15s %-15s %-15s %-15s %-15s %-15s\n", four.getString("product_id"),
						four.getString("type_id"), four.getString("brand_id"), four.getString("product_name"),
						four.getString("product_price"), four.getString("product_description"),
						four.getString("stock_amount"));
			}

			System.out.println("===========================================================================================================");
			ResultSet five = statement.executeQuery("select shoppingCart.product_id, "
					+ "theProduct.product_id, theProduct.product_name, theProduct.product_price FROM shopping_cart as shoppingCart INNER JOIN product AS theProduct ON shoppingCart.product_id = theProduct.product_id WHERE shoppingCart.product_id = '1'; ");
			System.out.println(
					"select shoppingCart.product_id, theProduct.product_id, theProduct.product_name, theProduct.product_price FROM shopping_cart as shoppingCart INNER JOIN product AS theProduct ON shoppingCart.product_id = theProduct.product_id WHERE shoppingCart.product_id = '1'; ");
			System.out.printf(" %-15s %-15s %-15s \n", "product_id", "product_name", "product_price");

			while (five.next()) {
				System.out.printf(" %-15s %-15s %-15s \n", five.getString("product_id"), five.getString("product_name"),
						five.getString("product_price"));
			}

			System.out.println();

			ResultSet six = statement.executeQuery("Select count(amount) as totalQuantity from shopping_cart;");
			System.out.println("Select count(amount) as totalQuantity from shopping_cart;");

			System.out.printf(" %-15s \n", "totalQuantity");

			while (six.next()) {
				System.out.printf(" %-15s \n", six.getString("totalQuantity"));
			}

			System.out.println();

			ResultSet seven = statement
					.executeQuery("select * from product where product_price between 0.99 and 5.99;");
			System.out.println("select * from product where product_price between 0.99 and 5.99;");

			System.out.printf(" %-15s %-15s %-15s %-15s %-15s %-15s %-15s \n", "product_id", "type_id", "brand_id",
					"product_name", "product_price", "product_description", "stock_amount");

			while (seven.next()) {
				System.out.printf(" %-15s %-15s %-15s %-15s %-15s %-15s %-15s \n", seven.getString("product_id"),
						seven.getString("type_id"), seven.getString("brand_id"), seven.getString("product_name"),
						seven.getString("product_price"), seven.getString("product_description"),
						seven.getString("stock_amount"));
			}
			// JOINS
			
			System.out.println("===========================================================================================================");
			System.out.println("Query 8");
			String query8 = "select distinct product.product_name from product cross join brand where product.type_id > 8";
			System.out.println(query8);
			ResultSet eight = statement.executeQuery(query8); 
			while (eight.next()) {
				String productName = eight.getString(1);
				System.out.println("Product Name: "+ productName);
			}
			
			//LEFT JOIN
			System.out.println("===========================================================================================================");
			System.out.println("Query 9");
			String query9 = "select pd.product_name as Name, pd.product_price as Price, pd.product_description as Description from product pd \n" + 
					"Left join brand on brand.brand_id = pd.brand_id\n" + 
					"left join type on type.type_id = pd.type_id\n" + 
					"where pd.product_price > 5 order by pd.product_description;";
			System.out.println(query9);
			System.out.println();
			ResultSet nine = statement.executeQuery(query9); 
			
			System.out.printf(" %-15s %-15s %-15s \n", "Name", "Price", "Description");
			while(nine.next()) {
				String name = nine.getString(1);
				String price = nine.getString(2);
				String description = nine.getString(3);
				System.out.printf(" %-15s %-15s %-15s \n", name, price, description);
			}
			

			//Self JOIN
			System.out.println("===========================================================================================================");
			System.out.println("Query 10");
			String query10 = "Select s.order_id, b.amount, b.product_id\n" + 
					"from shopping_cart s, shopping_cart b\n" + 
					"where s.order_id = b.order_id and s.amount >20; ";
			System.out.println(query10);
			System.out.println();
			ResultSet ten = statement.executeQuery(query10); 
			
			System.out.printf(" %-15s %-15s %-15s \n", "OrderId", "Amount", "Product ID");
			while(ten.next()) {
				String name = ten.getString(1);
				String price = ten.getString(2);
				String description = ten.getString(3);
				System.out.printf(" %-15s %-15s %-15s \n", name, price, description);
			}
                        
                        
                        //More Select queries
                        System.out.println("===========================================================================================================");
			System.out.println("Query 11");
                        String query11 = "Select user_id, username \n"
                                + "from user \n"
                                + "where DOB > '2005-01-01' ;";
                        System.out.println(query11);
			System.out.println();
			ResultSet eleven = statement.executeQuery(query11);
                        
                        System.out.printf(" %-15s %-15s \n", "UserID", "Username");
			while(eleven.next()) {
				String userid = eleven.getString(1);
				String username = eleven.getString(2);
				System.out.printf(" %-15s %-15s \n", userid, username);
			}
			
                        System.out.println("===========================================================================================================");
			System.out.println("Query 12");
                        String query12 = "Select name_on_card, username \n"
                                + "from user_payment join user join credit_debit \n"
                                + "on user.user_id = user_payment.user_id AND credit_debit.payment_id = user_payment.payment_id;";
                        System.out.println(query12);
			System.out.println();
			ResultSet twelve = statement.executeQuery(query12);
                        
                        System.out.printf(" %-15s %-15s \n", "Name on Card", "Username");
			while(twelve.next()) {
				String name_on_card = twelve.getString(1);
				String username = twelve.getString(2);
				System.out.printf(" %-15s %-15s \n", name_on_card, username);
			}
                        
                        System.out.println("===========================================================================================================");
			System.out.println("Query 13");
                        String query13 = "Select name_on_card, card_type_name \n"
                                + "from credit_debit join card_type \n"
                                + "on credit_debit.card_type_id = card_type.card_type_id ;";
                        System.out.println(query13);
			System.out.println();
			ResultSet thirteen = statement.executeQuery(query13);
                        
                        System.out.printf(" %-15s %-15s \n", "Name on Card", "Card Type");
			while(thirteen.next()) {
				String name_on_card = thirteen.getString(1);
				String card_type = thirteen.getString(2);
				System.out.printf(" %-15s %-15s \n", name_on_card, card_type);
				
				System.out.println("===========================================================================================================");
				System.out.println("Query 14");
	                        String query14 = "Delete from brand where brand_name = 'PAM';";
	                        System.out.println(query14);
	                        String queryDelete = "Select * from brand;";
				System.out.println();
	                        statement.execute(query14);
				ResultSet fourteen = statement.executeQuery(queryDelete);
	                        
	                        System.out.printf(" %-15s %-15s \n", "brand_id", "brand_name");
				while(fourteen.next()) {
					String brandID = fourteen.getString(1);
					String brandName = fourteen.getString(2);
					System.out.printf(" %-15s %-15s \n", brandID, brandName);
			}
				
				System.out.println("===========================================================================================================");
				System.out.println("Query 15");
	                        String query15 = "Update brand Set brand_name = 'SquareEnix' where brand_id = 3";;
	                        System.out.println(query15);
	                        String queryUpdate = "Select * from brand;";
				System.out.println();
	                        statement.execute(query15);
				ResultSet fifteen = statement.executeQuery(queryUpdate);
	                        
	                        System.out.printf(" %-15s %-15s \n", "brand_id", "brand_name");
				while(fifteen.next()) {
					String brandID = fifteen.getString(1);
					String brandName = fifteen.getString(2);
					System.out.printf(" %-15s %-15s \n", brandID, brandName);
			}
			}
                        
		} catch (SQLException i) {
			// System.out.println("Query for initializing tables found. Please check your
			// file formatting.");
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
