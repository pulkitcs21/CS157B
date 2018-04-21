package cs157b.project2;
import java.sql.*;
import java.util.Random;
public class Table {

	  // Statement and connection from Javal to execute the tables 
    private Statement stmt;
    private Connection connection;

    // Initalizing the statement and connection with our localhost 
    public Table(Statement stmt, Connection conn) {
        this.stmt = stmt;
        this.connection = conn;
    }

    // Function to create the three table provided
    public void createtable() {
        // Creating table card_Type 
    	String drop = "DROP DATABASE 157b;";
    	String database = "CREATE DATABASE 157b;";
    	String use = "use 157b;";
        String card_type = "DROP TABLE IF EXISTS card_type;" + 
        		" CREATE TABLE card_type (\n" + 
        		"    card_type_id   INTEGER       NOT NULL\n" + 
        		"                                 PRIMARY KEY AUTOINCREMENT,\n" + 
        		"    card_type_name VARCHAR (255) NOT NULL UNIQUE \n" + 
        		");";
        // Creating table Membership type
        String credit_debit = "DROP TABLE IF EXISTS credit_debit;" +
        		"CREATE TABLE credit_debit (\n" + 
        		"    payment_id   INTEGER       NOT NULL\n" + 
        		"                               PRIMARY KEY AUTOINCREMENT,\n" + 
        		"    name_on_card VARCHAR (255) NOT NULL,\n" + 
        		"    card_number  VARCHAR (17)  NOT NULL,\n" + 
        		"    exp_date     CHAR (5)      NOT NULL,\n" + 
        		"    cvv          CHAR (3)      NOT NULL,\n" + 
        		"    card_type_id INTEGER       NOT NULL,\n" + 
        		"    CONSTRAINT card_type_id FOREIGN KEY (\n" + 
        		"        card_type_id\n" + 
        		"    )\n" + 
        		"    REFERENCES card_type (card_type_id) ON DELETE CASCADE\n" + 
        		"                                        ON UPDATE CASCADE\n" + 
        		");";
        
        String type= "DROP TABLE IF EXISTS   type  ; " +
        		"CREATE TABLE type (\n" + 
        		"    type_id   INTEGER       NOT NULL\n" + 
        		"                            PRIMARY KEY AUTOINCREMENT,\n" + 
        		"    type_name VARCHAR (255) NOT NULL\n" + 
        		");\n" + 
        		"";
        
        // Creating table brand
        String brand = "DROP TABLE IF EXISTS brand;" + 
        		"CREATE TABLE brand (\n" + 
        		"    brand_id   INTEGER       NOT NULL\n" + 
        		"                             PRIMARY KEY AUTOINCREMENT,\n" + 
        		"    brand_name VARCHAR (255) NOT NULL\n" + 
        		");";
        
        String product= "DROP TABLE IF EXISTS  product ; " + 
        		"CREATE TABLE product (\n" + 
        		"    product_id          INTEGER       NOT NULL\n" + 
        		"                                      PRIMARY KEY AUTOINCREMENT,\n" + 
        		"    type_id             INTEGER       NOT NULL,\n" + 
        		"    brand_id            INTEGER       NOT NULL,\n" + 
        		"    product_name        VARCHAR (255) NOT NULL,\n" + 
        		"    product_price       FLOAT         NOT NULL,\n" + 
        		"    product_description TEXT,\n" + 
        		"    stock_amount        INTEGER       NOT NULL,\n" + 
        		"    CONSTRAINT brand_id FOREIGN KEY (\n" + 
        		"        brand_id\n" + 
        		"    )\n" + 
        		"    REFERENCES brand (brand_id) ON DELETE CASCADE\n" + 
        		"                                ON UPDATE CASCADE,\n" + 
        		"    CONSTRAINT type_id FOREIGN KEY (\n" + 
        		"        type_id\n" + 
        		"    )\n" + 
        		"    REFERENCES type (type_id) ON DELETE CASCADE\n" + 
        		"                              ON UPDATE CASCADE\n" + 
        		");\n" + 
        		"";
       
        
        String user= "DROP TABLE IF EXISTS  user ; " + 
        		"CREATE TABLE user (\n" + 
        		"    user_id         INTEGER       NOT NULL\n" + 
        		"                                  PRIMARY KEY AUTOINCREMENT,\n" + 
        		"    username        CHAR (16)     NOT NULL,\n" + 
        		"    password        CHAR (16)     NOT NULL,\n" + 
        		"    email           VARCHAR (254) DEFAULT NULL,\n" + 
        		"    DOB             DATE          DEFAULT NULL,\n" + 
        		"    address         VARCHAR (200) DEFAULT NULL,\n" + 
        		"    deliveryAddress VARCHAR (200) DEFAULT NULL\n" + 
        		");\n" + 
        		"";
        
        String user_payment="DROP TABLE IF EXISTS  user_payment ; "+"CREATE TABLE user_payment (\n" + 
        		"    payment_id INTEGER NOT NULL,\n" + 
        		"    user_id    INTEGER NOT NULL,\n" + 
        		"    CONSTRAINT payment_id_user_payment FOREIGN KEY (\n" + 
        		"        payment_id\n" + 
        		"    )\n" + 
        		"    REFERENCES credit_debit (payment_id) ON DELETE CASCADE\n" + 
        		"                                         ON UPDATE CASCADE,\n" + 
        		"    CONSTRAINT user_id_user_payment FOREIGN KEY (\n" + 
        		"        user_id\n" + 
        		"    )\n" + 
        		"    REFERENCES user (user_id) ON DELETE CASCADE\n" + 
        		"                              ON UPDATE CASCADE\n" + 
        		");\n" + 
        		"";
        
        String order=  "DROP TABLE IF EXISTS  [order] ; " + 
        		"CREATE TABLE [order] (\n" + 
        		"    order_id     INTEGER      NOT NULL\n" + 
        		"                              PRIMARY KEY AUTOINCREMENT,\n" + 
        		"    order_price  FLOAT        NOT NULL,\n" + 
        		"    user_id      INTEGER      NOT NULL,\n" + 
        		"    order_status VARCHAR (20) NOT NULL\n" + 
        		"                              DEFAULT 'Pending',\n" + 
        		"    CONSTRAINT user_id_order FOREIGN KEY (\n" + 
        		"        user_id\n" + 
        		"    )\n" + 
        		"    REFERENCES user (user_id) ON DELETE CASCADE\n" + 
        		"                              ON UPDATE CASCADE\n" + 
        		");"; 
        
        String shoppingCart = "DROP TABLE IF EXISTS  shopping_cart ; " +
        		"CREATE TABLE shopping_cart (\n" + 
        		"    order_id   INTEGER NOT NULL,\n" + 
        		"    product_id INTEGER NOT NULL,\n" + 
        		"    amount     INTEGER NOT NULL,\n" + 
        		"    CONSTRAINT order_id_shopping_cart FOREIGN KEY (\n" + 
        		"        order_id\n" + 
        		"    )\n" + 
        		"    REFERENCES [order] (order_id) ON DELETE CASCADE\n" + 
        		"                                  ON UPDATE CASCADE,\n" + 
        		"    CONSTRAINT product_id_shopping_cart FOREIGN KEY (\n" + 
        		"        product_id\n" + 
        		"    )\n" + 
        		"    REFERENCES product (product_id) ON DELETE CASCADE\n" + 
        		"                                    ON UPDATE CASCADE\n" + 
        		");\n" + 
        		"";
        
        String purchasedOrder = "DROP TABLE IF EXISTS  purchased_order ; " +
        		"CREATE TABLE purchased_order (\n" + 
        		"    order_id   INTEGER NOT NULL,\n" + 
        		"    product_id INTEGER NOT NULL,\n" + 
        		"    amount     INTEGER NOT NULL,\n" + 
        		"    CONSTRAINT order_id_purchased_cart FOREIGN KEY (\n" + 
        		"        order_id\n" + 
        		"    )\n" + 
        		"    REFERENCES [order] (order_id) ON DELETE CASCADE\n" + 
        		"                                  ON UPDATE CASCADE,\n" + 
        		"    CONSTRAINT product_id_purchased_cart FOREIGN KEY (\n" + 
        		"        product_id\n" + 
        		"    )\n" + 
        		"    REFERENCES product (product_id) ON DELETE CASCADE\n" + 
        		"                                    ON UPDATE CASCADE\n" + 
        		");\n" + 
        		"";
        try {
            // Executes the given tables to add tables to Database
        	//stmt.executeUpdate(drop);
        	//stmt.executeUpdate(database);
        	//stmt.executeUpdate(use);
            stmt.executeUpdate(card_type);//
            stmt.executeUpdate(credit_debit);//
            stmt.executeUpdate(brand);//
            stmt.executeUpdate(type);//
            stmt.executeUpdate(product);//
            stmt.executeUpdate(user);//
            stmt.executeUpdate(user_payment);//
            stmt.executeUpdate(order);//
            stmt.executeUpdate(shoppingCart);//
            stmt.executeUpdate(purchasedOrder);//
            
        } catch (SQLException se) {
            // Catches if there are exceptions raised by SQL
            se.printStackTrace();
        } catch (Exception e) {
            // Catches any Java Exceptions
            e.printStackTrace();
        }
    }
}
