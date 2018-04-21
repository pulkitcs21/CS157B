package cs157b.project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
			
			System.out.println("");
			String query1 = "select product.product_name, \"\n" + 
					"					+ \"type.type_name, brand.brand_name, product.product_price,\"\n" + 
					"					+ \"product.stock_amount, product.product_description FROM product, type, brand WHERE \"\n" + 
					"					+ \"product.type_id = type.type_id AND product.brand_id = brand.brand_id AND product.product_id = 1;";
			ResultSet one = statement.executeQuery(query1);
			
			while (one.next()) 
			{
				String productname = one.getString(1);
				String typename = one.getString(2);
				String brandname = one.getString(3);
				String productprice = one.getString(4);
				String stockAmount = one.getString(5);
				String description = one.getString(6);
				System.out.println("ProductName: " + productname +"typeName: " + typename +"brandName: " + brandname + "ProductPrice: "
				+ productprice +"StockAmount: " + stockAmount +"Description: " + description);
            }
			
			System.out.println("=====================================================================================================");
			
			
			String query2= "select product.product_name, shopping_cart.amount, (shopping_cart.amount * product.product_price) AS item_price FROM product,"
					+ "[order], shopping_cart WHERE shopping_cart.order_id = [order].order_id AND shopping_cart.product_id = product.product_id AND [order].order_id = 2";
			ResultSet two = statement.executeQuery("query2");
			while(two.next())
			{
				String productName = two.getString(1); 
				String amount = two.getString(2);
				String itemprice = two.getString(3);
				System.out.println("ProductName " + productName + "Amount " + amount + "ItemPrice "+ itemprice);
			}
			

			System.out.println("=====================================================================================================");
			
			String query3 = "select count(order_id) as item_count from shopping_cart where order_id = 2 group by order_id";
			ResultSet three = statement.executeQuery(query3);
		
			while(three.next())
			{
				String itemCount = three.getString(1);
				System.out.println("itemCount " + itemCount);
			}
			

			System.out.println("=====================================================================================================");
			
			String query4 = "Select * from product order by product_description";
			ResultSet four = statement.executeQuery(query4);
			
			while(four.next())
			{
				String productId = four.getString(1);
				String typeId= four.getString(2);
				String brandID= four.getString(3);
				String productName= four.getString(4);
				String productPrice= four.getString(5);
				String ProductDescription= four.getString(6);
				String StockAmount= four.getString(7);
				System.out.println("ProductId "+ productId + "TypeId "+ typeId + "brandId "+ brandID +"productName "+ productName + "productPrice "+ productPrice + "ProductDescription  "+ ProductDescription + "StockAmount "+ StockAmount );
			}
			

			System.out.println("=====================================================================================================");
			
			String query5 = "select shoppingCart.product_id, \"\n" + 
					"					+ \"theProduct.product_id, theProduct.product_name, theProduct.product_price "
					+ "FROM shopping_cart as shoppingCart INNER JOIN product AS theProduct ON shoppingCart.product_id = theProduct.product_id WHERE shoppingCart.product_id = '1';";
			ResultSet five = statement.executeQuery(query5);
			
			while(five.next())
			{
				String productId = five.getString(1);
				String productName = five.getString(2);
				String productPrice = five.getString(3);
				System.out.println("product_id" + productId + "product_name" +productName + "product_price" + productPrice);
			}

			System.out.println("=====================================================================================================");
			
			
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

}
