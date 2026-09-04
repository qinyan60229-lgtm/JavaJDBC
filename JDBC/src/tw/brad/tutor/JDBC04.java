package tw.brad.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBC04 {

	private static final String url = "jdbc:mysql://localhost:3306/brad";
	private static final Properties prop = new Properties();
	private static final String SQL ="""
							DELETE FROM cust 
							WHERE cname LIKE 'E%'
							""";
	
	public static void main(String[] args) {
		
		prop.put("user", "root");
		prop.put("password", "root");
		prop.put("useSSL", "false");
		prop.put("characterEncoding", "UTF-8");
		
		try (Connection conn = DriverManager.getConnection(url, prop);
			Statement stmt = conn.createStatement();){
			
			int n = stmt.executeUpdate(SQL);
			System.out.println(n);
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}