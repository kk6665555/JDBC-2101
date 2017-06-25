package a001;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;







public class JDBC16 {

	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");

		try(Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/oneone",prop);) {
			
//			FileInputStream in = new FileInputStream("./dir1/java.png");
			
			PreparedStatement pstmt = conn.prepareStatement("select * from member where id = 1");
			ResultSet rs =  pstmt.executeQuery();
			rs.next();
			
			ObjectInputStream in =new ObjectInputStream(rs.getBinaryStream("obj"));
			student s3 = (student) in.readObject();
			System.out.println(s3.getid() + ":" + s3.total() + ":" + s3.avg());
			in.close();
			
			
		
			
			System.out.println("ok");
		} catch (Exception e) {
			System.out.println(e);
	
		}
	}

}


