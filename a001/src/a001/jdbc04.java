package a001;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;



public class jdbc04 {
	public static void main(String[] args) {

		
		
		//-------
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/brad",prop)){
			String sql = "INSERT INTO cust (came,tel,birthday)VALUES(?,?,?)";
			
			PreparedStatement psymet = conn.prepareStatement(sql);
			psymet.setString(1, "Peter");
			psymet.setString(2, "321");
			psymet.setString(2, "1998-05-09");
			psymet.execute();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
