package a001;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;







public class JDBC15 {

	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");

		try(Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/oneone",prop)) {
			
			
			student s1 = new student("001",60,85,90);
			
			System.out.println(s1.getid() + ":" + s1.total() + " " + s1.avg());
			
			
//			ObjectOutputStream oot = new ObjectOutputStream(new FileOutputStream("./dir1/s1.obj"));
//			FileInputStream in = new FileInputStream("./dir1/java.png");
			
//			oot.writeObject(s1);
//			oot.flush();
//			oot.close();
			
//			ObjectInputStream oin = new ObjectInputStream(new FileInputStream("./dir1/s1.obj"));
			PreparedStatement pstmt = conn.prepareStatement("update member set obj = ? where id = 2");
			pstmt.setObject(1, s1);//會自動序列化
			pstmt.executeUpdate();
//			oin.close();
			
			
//			PreparedStatement pstmt = conn.prepareStatement("update member set obj = ? where id = 1");
//			pstmt.setBinaryStream(1, xxx);
			
			
			System.out.println("ok");
		} catch (Exception e) {
			System.out.println(e);
	
		}
	}

}


