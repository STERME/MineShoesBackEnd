package db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.shoses;
/*
 * �������帺����������ݿ����ز�������ز������ݿ�ķ�����Ҫ��ǰ������DBConnection����
 */
public class MySQLConnection implements DBConnection {
	private Connection conn;
	   
	   public MySQLConnection() {
	  	 try {
	  		 Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance(); // JDBC �������ݿ�
	  		 conn = DriverManager.getConnection(MySQLDBUtil.URL);
	  		
	  	 } catch (Exception e) {
	  		 e.printStackTrace();
	  	 }
	   }
	   
	   @Override
	   public void close() {
	  	 if (conn != null) {
	  		 try {
	  			 conn.close();
	  		 } catch (Exception e) {
	  			 e.printStackTrace();
	  		 }
	  	 }
	   }

	@Override
	public String getFullname(String userId) {
		// TODO Auto-generated method stub
		if (conn == null) {
			return "";
		}	
		
		String name = "";
		try {
			String sql = "SELECT first_name, last_name from users WHERE user_id = ? ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, userId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) { // initial position is -1 need next() to remove to 0
				name = rs.getString("first_name") + " " + rs.getString("last_name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return name;
	}

	@Override
	public boolean verifyLogin(String userId, String password) {
		// TODO Auto-generated method stub
		// ��֤��½�߼�
		if (conn == null) {
			return false;
		}
		
		String pwd = "";
		try {
			String sql = "SELECT password from users WHERE user_id = ? "; // �����ã� ���Է�ֹsql�������ִ�д���
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, userId);// �����ʺŵ�ֵ
			ResultSet rs = statement.executeQuery();
			while (rs.next()) { // initial position is -1 need next() to remove to 0
				pwd = rs.getString("password");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (pwd.equals(password)) {
			return true;
		}
		
		return false;
	}

	@Override
	public List<shoses> searchAllShoses() {
		// TODO Auto-generated method stub
		// �����������ݿ���shose��ֵ
		if (conn == null) {
			return null;
		}
		List<shoses> allShoses = new ArrayList<>();
		try {
			String sql = "SELECT * from shoses";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			shoses.ShosesBuilder builder = new shoses.ShosesBuilder();
			
			while (rs.next()) {
				builder.setShoseId(rs.getString("shoses_id")); // rs�Ƿ��ر��һ��tuple��getString��ȡ���е�һ������attribute����builder��������shose�������shose��
				builder.setName(rs.getString("name"));
				builder.setCategory(rs.getString("category"));
				builder.setColor(rs.getString("color"));
				builder.setVendor(rs.getString("vendor"));
				builder.setDescription(rs.getString("description"));
				builder.setImageUrl(rs.getString("image_url"));
				builder.setPrice(rs.getString("price"));
				builder.setSize(rs.getString("size"));
				builder.setWidth(rs.getString("width"));
				builder.setType(rs.getString("type"));
				builder.setMsrp(rs.getString("msrp"));
				allShoses.add(builder.build()); // ����һ��shose object
				
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return allShoses;
	}
	public boolean insertShoses(String shoses_id, String name, String category, String color, String vendor, String description, String image_url, String price, String size, String width, String type, String msrp) {	
		if (conn == null) {
			System.err.println("DB connection failed");
			return false;
		}
		try {
			String sql = "INSERT IGNORE INTO shoses VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, shoses_id);
			ps.setString(2, name);
			ps.setString(3, color);
			ps.setString(4, vendor);
			ps.setString(5, category);
			ps.setString(6, type);
			ps.setString(7, description);
			ps.setString(8, image_url);
			ps.setString(9, price);
			ps.setString(10, size);
			ps.setString(11, width);
			ps.setString(12, msrp);
			
			return ps.executeUpdate() == 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;	
	}
	@Override
	public boolean registerUser(String userId, String password, String firstname, String lastname) {
		if (conn == null) {
			System.err.println("DB connection failed");
			return false;
		}

		try {
			String sql = "INSERT IGNORE INTO users VALUES (?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, password);
			ps.setString(3, firstname);
			ps.setString(4, lastname);
			
			return ps.executeUpdate() == 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;	
	}
	@Override
	public boolean deleteShoses (String id) {
		if (conn == null) {
			System.err.println("DB connection failed");
			return false;
		}
		try {
			String sql = "DELETE IGNORE FROM shoses where shoses_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			return ps.executeUpdate() == 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}