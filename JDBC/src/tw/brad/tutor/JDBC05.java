package tw.brad.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBC05 {

	private static final String url = "jdbc:mysql://localhost:3306/brad";
	private static final Properties prop = new Properties();
	private static final String SQL="""
							UPDATE cust 
							SET cname = 'Andy2',tel = '345'
							WHERE id = 8
							""";
	
	public static void main(String[] args) {
		
		prop.put("user", "root");
		prop.put("password", "root");
		prop.put("useSSL", "false");
		prop.put("characterEncoding", "UTF-8");
		
		try (Connection conn = DriverManager.getConnection(url, prop);
			Statement stmt = conn.createStatement();){
			
			System.out.println(stmt.execute(SQL));
	
			System.out.println("OK7");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}