package tw.brad.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBC06 {

	private static final String url = "jdbc:mysql://localhost:3306/brad";
	private static final Properties prop = new Properties();
	private static final String SQL ="""
							SELECT id, cname myname, tel, birthday 
							FROM cust
							""";
	
	public static void main(String[] args) {
		
		prop.put("user", "root");
		prop.put("password", "root");
		prop.put("useSSL", "false");
		prop.put("characterEncoding", "UTF-8");
		
		try (Connection conn = DriverManager.getConnection(url, prop);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
				){
			while (rs.next()) {
				String c1 = rs.getString("id");
				String c2 = rs.getString("myname");
				String c3 = rs.getString("birthday");
				System.out.printf("%s:%s:%s\n", c1, c2, c3);
			}
			
	
			System.out.println("OK8");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}