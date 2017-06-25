package a001;


import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;







public class JDBC13 {

	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");

		try(Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/oneone",prop);
				FileInputStream in = new FileInputStream("./dir1/java.png")) {
			
//			FileInputStream in = new FileInputStream("./dir1/java.png");
			PreparedStatement pstmt = conn.prepareStatement("update member set img = ? where id = 1");
			pstmt.setBinaryStream(1, in);
			pstmt.executeUpdate();
			
			System.out.println("ok");
		} catch (Exception e) {
			System.out.println(e);
	
		}
	}

}


