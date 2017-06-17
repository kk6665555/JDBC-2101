package a001;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class test3 {
	public static void main(String[] args) {
		// 1. Driver
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException ee){
			System.exit(-1);
		}
		
		try{
			Properties prop = new Properties();
			prop.setProperty("user", "root");
			prop.setProperty("password", "root");
			// 2. Connection
			Connection conn = 
				DriverManager.getConnection(
					"jdbc:mysql://localhost/brad",
					prop);
			// 3. SQL statement
			Statement stmt = conn.createStatement();
			// 4. query
			String sql = "SELECT * FROM cust";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				String id = rs.getString("id");
				String cname = rs.getString("cname");
				String tel = rs.getString("tel");
				String birthday = rs.getString("birthday");
				System.out.println(id + ":" + cname + ":" + tel +":"+ birthday);

//需要改成自己的資料庫設定

			}
			
			stmt.execute(sql);
			System.out.println(sql);
		}catch(SQLException se){
			System.out.println(se);
		}
		
		
		
	}
}
