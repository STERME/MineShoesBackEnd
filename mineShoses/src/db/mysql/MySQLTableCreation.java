package db.mysql;

import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Connection;

public class MySQLTableCreation {
	// Run this as Java application to reset db schema.
	public static void main(String[] args) {
		try {
			// Step 1 Connect to MySQL.
			System.out.println("Connecting to " + MySQLDBUtil.URL);
			Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
			Connection conn = DriverManager.getConnection(MySQLDBUtil.URL);
			
			if (conn == null) {
				return;
			}
			
			//  Drop tables in case they exist.
			Statement statement = conn.createStatement();
			String sql = "DROP TABLE IF EXISTS categories";
			statement.executeUpdate(sql);
			
			sql = "DROP TABLE IF EXISTS shoses";
			statement.executeUpdate(sql);
			
			sql = "DROP TABLE IF EXISTS type";
			statement.executeUpdate(sql);
			
			sql = "DROP TABLE IF EXISTS users";
			statement.executeUpdate(sql);
            
			// Step 3 Create new tables
			sql = "CREATE TABLE shoses ("
								+ "shoses_id VARCHAR(255) NOT NULL,"
								+ "name VARCHAR(255),"
								+ "color VARCHAR(255),"
								+ "vendor VARCHAR(255),"
								+ "category VARCHAR(255),"
								+ "type VARCHAR(255),"
								+ "description VARCHAR(255),"
								+ "image_url VARCHAR(255),"
								+ "price VARCHAR(255),"
								+ "size VARCHAR(255),"
								+ "width VARCHAR(255),"	
								+ "msrp VARCHAR(255),"		
								+ "PRIMARY KEY (shoses_id, category, type)"
								+ ")";
		  statement.executeUpdate(sql);

		  sql = "CREATE TABLE users ("
								+ "user_id VARCHAR(255) NOT NULL,"
								+ "password VARCHAR(255) NOT NULL,"
								+ "first_name VARCHAR(255),"
								+ "last_name VARCHAR(255),"
								+ "PRIMARY KEY (user_id)"
								+ ")";
		statement.executeUpdate(sql);

		sql = "CREATE TABLE categories ("
								+ "shoses_id VARCHAR(255) NOT NULL,"
								+ "category VARCHAR(255) NOT NULL,"
								+ "type VARCHAR(255) NOT NULL,"
								+ "PRIMARY KEY (shoses_id, category, type),"
								+ "FOREIGN KEY (shoses_id) REFERENCES shoses(shoses_id)"
								+ ")";
		statement.executeUpdate(sql);
        
		sql = "INSERT INTO users VALUES('1111', '3229c1097c00d497a0fd282d586be050', 'John', 'Smith')";
		statement.executeUpdate(sql);
		
		sql = "INSERT INTO shoses VALUES('1', 'Nike 320', 'brown', 'Nike', 'man', 'joke', 'nice shoses',"
				+ " 'www.baidu.com', '40', '44', '22', '123')";
		statement.executeUpdate(sql);
			conn.close();
			System.out.println("Import done successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}