package tw.brad.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBC03 {

	private static final String url = "jdbc:mysql://localhost:3306/brad";
	private static final Properties prop = new Properties();
	private static final String SQL_INSERT ="""
							INSERT INTO cust (cname,tel,birthday) 
							VALUES ('Eric1','123','1999-01-02'),
							('Eric2','123','1999-01-02'),
							('Eric3','123','1999-01-02'),
							('Eric4','123','1999-01-02'),
							('Eric5','123','1999-01-02')
							""";
	
	public static void main(String[] args) {
		
		prop.put("user", "root");
		prop.put("password", "root");
		prop.put("useSSL", "false");
		prop.put("characterEncoding", "UTF-8");
		
		try (Connection conn = DriverManager.getConnection(url, prop);
			Statement stmt = conn.createStatement();){
			
			stmt.execute(SQL_INSERT);
	
			System.out.println("OK6");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}