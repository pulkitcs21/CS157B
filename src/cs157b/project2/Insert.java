package cs157b.project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import java.sql.*;

public class Insert 
{
	private Connection conn;
	public Insert(Connection conn){
		this.conn = conn;
		
	}
	
	public  void insertIntoTables()
	{
		 Statement statement = null;
		try
		{
			//connection to do inserts

			//conn = DriverManager.getConnection("jdbc:sqlite:C:/sqlite/db/157b.db");

			statement = conn.createStatement();
			
			//BRAND TABLE
			Scanner brandScanner = 	new Scanner(new File("src/cs157b/project2/brandFile.txt"));
			while(brandScanner.hasNextLine())
			{
				String brandName = brandScanner.nextLine();
				statement.execute("Insert INTO brand (brand_name) VALUES ('" + brandName + "')");
				
			}
			System.out.println();
			
			ResultSet brandRS = statement.executeQuery("Select * from brand;");
			
			System.out.println("BRAND TABLE");
			System.out.printf("%-10s %-10s \n", "brand_id", "brand_name");
			while (brandRS.next()) 
			{
               System.out.printf("%-10s %-10s \n", brandRS.getString("brand_id"), brandRS.getString("brand_name"));
            }
			
			System.out.println();
			
			//CARD TYPE
			Scanner cardTypeScanner = 	new Scanner(new File("src/cs157b/project2/cardTypeFile.txt"));
			while(cardTypeScanner.hasNextLine())
			{
				String cardName = cardTypeScanner.nextLine();
				statement.execute("Insert INTO card_type (card_type_name) VALUES ('" + cardName + "')");
				
			}
			
			ResultSet cardRS = statement.executeQuery("Select * from card_type;");
			
			System.out.println("CARD TABLE");
			System.out.printf("%-12s %-12s \n", "card_type_id", "card_type_name");
			while (cardRS.next()) 
			{
               System.out.printf("%-12s %-12s \n", cardRS.getString("card_type_id"), cardRS.getString("card_type_name"));
            }
			
			System.out.println();
			
			//credit debit folder
			
			Scanner CDscanner = new Scanner(new File("src/cs157b/project2/CDfile.txt"));
			
			while(CDscanner.hasNextLine())
			{
				String next = CDscanner.nextLine();
				String[] hold = next.split(" ");
				String name = hold[0] + " " + hold[1];
				String cardNumber = hold[2];
				String expDate = hold[3];
				String cvv = hold[4];
				String typeNumber = hold[5];
				
				//System.out.println(name + " " + cardNumber +" " + expDate + " " + cvv + " " +typeNumber);
				
				statement.execute("Insert INTO credit_debit(name_on_card, card_number, exp_date, cvv, card_type_id) VALUES "
						+ "('" + name + "','" + cardNumber + "','" + expDate +"','" + cvv +"','" + typeNumber+ "')");
			}
			
				ResultSet CDRS = statement.executeQuery("Select * from credit_debit;");
				System.out.println("CEDIT DEBIT TABLE");
			System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s \n", "payment_id",
					"name_on_card", "card_number" ,"exp_date", "cvv", "card_type_id");
			while (CDRS.next()) 
			{
               System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s \n", CDRS.getString("payment_id"), 
            		   CDRS.getString("name_on_card"), CDRS.getString("card_number"), CDRS.getString("exp_date")
            		   , CDRS.getString("cvv"), CDRS.getString("card_type_id"));
            }
			
			System.out.println();
			
			//USER
			Scanner userScanner = new Scanner(new File("src/cs157b/project2/userFile.txt"));
			
			while(userScanner.hasNextLine())
			{
				String next = userScanner.nextLine();
				String[] hold = next.split(" ");
				
				String user = hold[0];
				String pass = hold[1];
				String email = hold[2];
				String DOB = hold[3];
				String addr = hold[4] + " " + hold[5] + " " + hold[6] + " " + hold[7] +" " + hold[8] + " " + hold[9];
				String Deladdr = hold[10] +" "
				+ hold[11] + " " + hold[12] + " " + hold[13] +" " + hold[14] + " " + hold[15];
				
				statement.execute("Insert INTO user(username, password, email, DOB, address, deliveryAddress) VALUES "
						+ "('" + user + "','" + pass + "','" + email +"','" + DOB +"','" + addr+  "','" + Deladdr+ "')");
				
			}
			
			ResultSet userRS = statement.executeQuery("Select * from user;");
			System.out.println("USER TABLE");
			System.out.printf("%-50s %-50s %-50s %-50s %-50s %-50s %-50s \n", "user_id",
					"username", "password" ,"email", "DOB", "address", "deliveryAddress");
			while (userRS.next()) 
			{
               System.out.printf("%-50s %-50s %-50s %-50s %-50s %-50s %-50s  \n", userRS.getString("user_id"), 
            		   userRS.getString("username"), userRS.getString("password"), userRS.getString("email")
            		   , userRS.getString("DOB"), userRS.getString("address"), userRS.getString("deliveryAddress"));
            }
			
			System.out.println();
			
			//ORDER
			/*Scanner orderScanner = new Scanner(new File("src/cs157b/project2/orderFile.txt"));
			
			while(orderScanner.hasNextLine())
			{
				String next = orderScanner.nextLine();
				String[] hold = next.split(" ");
				
				String cost = hold[0];
				String buyerID = hold[1];
				
				statement.execute("Insert INTO [order](order_price, user_id) VALUES "
						+ "('" + cost + "','" + buyerID +  "')");	
			}
			
			ResultSet orderRS = statement.executeQuery("Select * from `order`;");
			System.out.println("ORDER TABLE");
			System.out.printf("%-10s %-10s %-10s %-10s \n", "order_id",
					"order_price", "user_id" ,"order_status");
			while (orderRS.next()) 
			{
               System.out.printf("%-10s %-10s %-10s %-10s \n", orderRS.getString("order_id"), 
            		   orderRS.getString("order_price"), orderRS.getString("user_id"), orderRS.getString("order_status"));
            }
			
			System.out.println(); */
			
			//TYPE
			Scanner typeScanner = 	new Scanner(new File("src/cs157b/project2/typeFile.txt"));
			while(typeScanner.hasNextLine())
			{
				String typeName = typeScanner.nextLine();
				statement.execute("Insert INTO type (type_name) VALUES ('" + typeName + "')");
				
			}
			
			ResultSet typeRS = statement.executeQuery("Select * from type;");
			System.out.println("TYPE TABLE");
			System.out.printf("%-12s %-12s \n", "type_id", "type_name");
			while (typeRS.next()) 
			{
               System.out.printf("%-12s %-12s \n", typeRS.getString("type_id"), typeRS.getString("type_name"));
            }
			
			System.out.println();
			
			//PRODUCT
			Scanner productScanner = new Scanner(new File("src/cs157b/project2/productFile.txt"));
			
			while(productScanner.hasNextLine())
			{
				String next = productScanner.nextLine();
				String[] hold = next.split(" ");
				
				String typeID = hold[0];
				String brandID = hold[1];
				String productName = hold[2];
				String price = hold[3];
				String description = hold[4];
				String stock = hold[5];
				
				statement.execute("Insert INTO product(type_id, brand_id, product_name, product_price, product_description, stock_amount) VALUES "
						+ "('" + typeID + "','" + brandID + "','" + productName + "','"+ price + "','" + description + "','" + stock +"')");	
			}
			
			ResultSet productRS = statement.executeQuery("Select * from product;");
			System.out.println("PRODUCT TABLE");
			System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s %-10s\n", "product_id", "type_id",
					"brand_id", "product_name" ,"product_price", "product_description", "stock_amount");
			while (productRS.next()) 
			{
               System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s %-10s\n", productRS.getString("product_id"), 
            		   productRS.getString("type_id"), productRS.getString("brand_id"), productRS.getString("product_name"),
            		   productRS.getString("product_price"), productRS.getString("product_description"), productRS.getString("stock_amount"));
            }
			
			System.out.println();
			
			//SHOPPING CART
			/*Scanner scScanner = 	new Scanner(new File("src/cs157b/project2/shoppingCartFile.txt"));
			while(scScanner.hasNextLine())
			{
				String next = scScanner.nextLine();
				String[] hold = next.split(" ");
				String order_id = hold[0];
				String product_id = hold[1];
				String amount = hold[2];
						
				statement.execute("Insert INTO shopping_cart (order_id, product_id, amount) VALUES ('" + order_id+ "','" + product_id+ "','" + amount + "')");
				
			}
			
			ResultSet scRS = statement.executeQuery("Select * from shopping_cart;");
			System.out.println("SHOPPING CART TABLE");
			System.out.printf("%-12s %-12s %-12s \n", "order_id", "product_id", "amount");
			while (scRS.next()) 
			{
               System.out.printf("%-12s %-12s %-12s \n", scRS.getString("order_id"), scRS.getString("product_id"), scRS.getString("amount"));
            }
			
			System.out.println();*/
			
			
			//USER PAYMENT
			Scanner upScanner = 	new Scanner(new File("src/cs157b/project2/userPaymentFile.txt"));
			while(upScanner.hasNextLine())
			{
				String next = upScanner.nextLine();
				String[] hold = next.split(" ");
				String payment_id = hold[0];
				String user_id = hold[1];
				
						
				statement.execute("Insert INTO user_payment (payment_id, user_id) VALUES ('" + payment_id+ "','" + user_id+  "')");
				
			}
			
			ResultSet upRS = statement.executeQuery("Select * from user_payment;");
			System.out.println("USER TABLE");
			System.out.printf("%-12s %-12s \n", "payment_id", "user_id");
			while (upRS.next()) 
			{
               System.out.printf("%-12s %-12s\n", upRS.getString("payment_id"), upRS.getString("user_id"));
            }
			
			System.out.println();
			
			
			
			
			//PURCHASED ORDER
			/*Scanner PO = 	new Scanner(new File("src/cs157b/project2/purchasedOrderFile.txt"));
			while(PO.hasNextLine())
			{
				String next = PO.nextLine();
				String[] hold = next.split(" ");
				String order_id = hold[0];
				String product_id = hold[1];
				String amount = hold[2];
				
						
				statement.execute("Insert INTO purchased_order (order_id, product_id,amount) VALUES ('" + order_id+ "','" + product_id+"','" +amount+  "')");
				
			}
			
			ResultSet OPRS = statement.executeQuery("Select * from purchased_order;");
			System.out.println("PURCHASED ORDER TABLE");
			System.out.printf("%-12s %-12s %-12s \n", "order_id", "product_id", "amount");
			while (upRS.next()) 
			{
               System.out.printf("%-12s %-12s %-12s\n", OPRS.getString("order_id"), OPRS.getString("product_id"), OPRS.getString("amount"));
            }
			
			System.out.println();*/
		}
		catch(FileNotFoundException e) 
		{
			System.out.println("File not found! Please check your file paths.");
		}
		catch (SQLException i) 
		{
	        //System.out.println("Query for initializing tables found. Please check your file formatting.");
			i.printStackTrace();
	    }
	}

}
