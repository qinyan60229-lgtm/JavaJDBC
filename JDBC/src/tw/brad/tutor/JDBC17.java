package tw.brad.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import tw.brad.api.Food;

public class JDBC17 {
	// Application => 共用 Connection Pool
	private static HikariDataSource dataSource;
	
	static {      //static initialization block 靜態初始化區塊   class 第一次被載入時，只會執行一次
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:mysql://localhost:3306/brad");
		config.setUsername("root");
		config.setPassword("root");
		
		// 優化
		config.setMaximumPoolSize(10);
		config.setMinimumIdle(4);
		config.setConnectionTimeout(10*1000);
		
		dataSource = new HikariDataSource(config);
	}
	
	
	private static final String SQL_KEY = """
						SELECT id, name, city, town, addr, tel
						FROM food
						ORDER BY id
						LIMIT ?, ?
							""";
	private static final int rpp = 10;
	
	public static void main(String[] args) {

		
		try (Connection conn = dataSource.getConnection();  //跟 Hikari 連線池借一條 Connection
				PreparedStatement pstmt = conn.prepareStatement(SQL_KEY);  //把 SQL先準備好
				){
			System.out.println("Food Search");
			System.out.println("-----");
			Scanner scanner = new Scanner(System.in);
			System.out.print("Page: ");
			int page = scanner.nextInt();
			System.out.println("-----");
//			
//			String skey = "%" + key + "%";
			
			List<Food> foods = search(page, pstmt);  //把第 X 頁和 pstmt 交給 search() 幫我查
			System.out.printf("共%d筆美食資料\n", foods.size());
			System.out.println("-----");
			for (Food food : foods) {  //foods 裡面的 Food，一個一個拿出來。
				System.out.printf("%d:%s:%s:%s\n", food.getId(), food.getName(), food.getTel(), food.getAddr());
				System.out.println("------------------------");
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	static List<Food> search(int page, PreparedStatement pstmt)throws Exception{
		List<Food> foods = new LinkedList<>();

		int start = (page - 1) * rpp;  //起始位置 = (頁數 - 1) × 每頁筆數
		
		pstmt.setInt(1, start);
		pstmt.setInt(2, rpp);
		
		ResultSet rs = pstmt.executeQuery();  //SQL 真的送去 MySQL 執行
		while (rs.next()) {
			Food food = new Food(rs.getLong("id"), rs.getString("name"),   //把 ResultSet 轉成 Food 物件
					rs.getString("city")+rs.getString("town")+rs.getString("addr"),
					rs.getString("tel"),null);
			foods.add(food);
		}
		
		
		return foods;
	}
	

}