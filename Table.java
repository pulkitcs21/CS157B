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
        String card_type = "DROP TABLE IF EXISTS card_type;" + 
        		"CREATE TABLE card_type (" + 
        		"  card_type_id int(3) unsigned NOT NULL AUTO_INCREMENT," + 
        		"  card_type_name varchar(255) NOT NULL," + 
        		"  PRIMARY KEY (card_type_id))";
        // Creating table Membership type
        String credit_debit = "DROP TABLE IF EXISTS credit_debit;" +
        		"CREATE TABLE credit_debit (" + 
        		"   payment_id  int(4) unsigned NOT NULL, " + 
        		"   name_on_card  varchar(255) NOT NULL , " + 
        		"   card_number  varchar(17) NOT NULL , " + 
        		"   exp_date  char(5) NOT NULL, " + 
        		"   cvv  char(3) NOT NULL, " + 
        		"   card_type_id  int(3) unsigned NOT NULL, " + 
        		"  KEY  card_type_id  ( card_type_id ), " + 
        		"  CONSTRAINT  card_type_id  FOREIGN KEY ( card_type_id ) REFERENCES  card_type  ( card_type_id ) ON DELETE CASCADE ON UPDATE CASCADE )";
        
        String type= "DROP TABLE IF EXISTS   type  ; " +
        		"CREATE TABLE   type   ( " + 
        		"    type_id   int(4) unsigned NOT NULL AUTO_INCREMENT, " + 
        		"    type_name   varchar(255) NOT NULL, " + 
        		"  PRIMARY KEY (  type_id  ))";
        
        // Creating table brand
        String brand = "DROP TABLE IF EXISTS brand;" + 
        		"CREATE TABLE brand (" + 
        		"  brandId int(4) unsigned NOT NULL AUTO_INCREMENT," + 
        		"  brand_name varchar(255) NOT NULL," + 
        		"  PRIMARY KEY (brandId))";
        
        String product= "DROP TABLE IF EXISTS  product ; " + 
        		"CREATE TABLE  product  ( " + 
        		"   product_id  int(4) unsigned NOT NULL AUTO_INCREMENT, " + 
        		"   type_id  int(4) unsigned NOT NULL, " + 
        		"   brand_id  int(4) unsigned NOT NULL, " + 
        		"   product_name  varchar(255) NOT NULL, " + 
        		"   product_price  float NOT NULL, " + 
        		"   product_description  text, " + 
        		"   stock_amount  int(4) NOT NULL, " + 
        		"  PRIMARY KEY ( product_id ), " + 
        		"  KEY  type_id  ( type_id ), " + 
        		"  KEY  brand_id  ( brand_id ), " + 
        		"  CONSTRAINT  brand_id  FOREIGN KEY ( brand_id ) REFERENCES  brand  ( brand_id ) ON DELETE CASCADE ON UPDATE CASCADE, " + 
        		"  CONSTRAINT  type_id  FOREIGN KEY ( type_id ) REFERENCES  type  ( type_id ) ON DELETE CASCADE ON UPDATE CASCADE)";
       
        
        String user= "DROP TABLE IF EXISTS  user ; " + 
        		"CREATE TABLE  user  ( " + 
        		"   user_id  int(4) unsigned NOT NULL AUTO_INCREMENT, " + 
        		"   username  char(16) NOT NULL , " + 
        		"   password  char(16) NOT NULL , " + 
        		"   email  varchar(254), " + 
        		"   DOB  date, " + 
        		"   address  varchar(200), " + 
        		"   deliveryAddress  varchar(200), " + 
        		"  PRIMARY KEY ( user_id )) ";
        
        String order= "DROP TABLE IF EXISTS  order ; " + 
        		" " + 
        		"CREATE TABLE  order  ( " + 
        		"   order_id  int(4) unsigned NOT NULL AUTO_INCREMENT, " + 
        		"   order_price  float NOT NULL, " + 
        		"   user_id  int(11) unsigned NOT NULL, " + 
        		"  PRIMARY KEY ( order_id ), " + 
        		"  KEY  user_id_order  ( user_id ), " + 
        		"  CONSTRAINT  user_id_order  FOREIGN KEY ( user_id ) REFERENCES  user  ( user_id ) ON DELETE CASCADE ON UPDATE CASCADE )"; 
        
        String shoppingCart = "DROP TABLE IF EXISTS  shopping_cart ; " +
        		"CREATE TABLE  shopping_cart  ( " + 
        		"   order_id  int(11) unsigned NOT NULL, " + 
        		"   product_id  int(11) unsigned NOT NULL, " + 
        		"   amount  int(11) NOT NULL, " + 
        		"  KEY  order_id_shopping_cart  ( order_id ), " + 
        		"  KEY  product_id_shopping_cart  ( product_id ), " + 
        		"  CONSTRAINT  order_id_shopping_cart  FOREIGN KEY ( order_id ) REFERENCES  order  ( order_id ) ON DELETE CASCADE ON UPDATE CASCADE, " + 
        		"  CONSTRAINT  product_id_shopping_cart  FOREIGN KEY ( product_id ) REFERENCES  product  ( product_id ) ON DELETE CASCADE ON UPDATE CASCADE) ";
        try {
            // Executes the given tables to add tables to Database
            stmt.executeUpdate(card_type);
            stmt.executeUpdate(credit_debit);
            stmt.executeUpdate(brand);
            stmt.executeUpdate(type);
            stmt.executeUpdate(product);
            stmt.executeUpdate(user);
            stmt.executeUpdate(order);
            stmt.executeUpdate(shoppingCart);
            
        } catch (SQLException se) {
            // Catches if there are exceptions raised by SQL
            se.printStackTrace();
        } catch (Exception e) {
            // Catches any Java Exceptions
            e.printStackTrace();
        }
    }
}
