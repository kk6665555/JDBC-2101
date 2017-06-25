package a001;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;







public class JDBC14 {

	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");

		try(Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/oneone",prop);) {
			
//			FileInputStream in = new FileInputStream("./dir1/java.png");
			
			PreparedStatement pstmt = conn.prepareStatement("select * from member where id = 1");
			ResultSet rs =  pstmt.executeQuery();
			rs.next();
			
			InputStream in =  rs.getBinaryStream("img");
			
			FileOutputStream out = new FileOutputStream("./dir2/img.png");
			byte[] buf = new byte[4096]; int len;
			while((len = in.read(buf)) != -1 ){
				out.write(buf,0,len);
			}
			
			out.flush();
			out.close();
			
			System.out.println("ok");
		} catch (Exception e) {
			System.out.println(e);
	
		}
	}

}


