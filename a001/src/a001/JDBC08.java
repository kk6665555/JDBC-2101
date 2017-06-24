package a001;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;





public class JDBC08 {

	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");

		try(Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/oneone",prop)) {
			Statement stmt = conn.createStatement();
			String sql = "insert into member(account,password,realname)"+"values('mike','123','mike1 567')";
			
			stmt.executeUpdate(sql);
			
			System.out.println("ok");
			
//			ResultSet rs = stmt.executeQuery("select count(*) from git");
//			rs.next();
//			System.out.println(rs.getString(1));
			//System.out.println(rs.getRow());//指標位置
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	
	static boolean isRepeat(String account,Statement stmt) throws Exception{
		String sql = "select count(*) from member where account='" + account + " ' ";
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()){
			int num = rs.getInt(1);
			if(num>0) {
				return true;
			}else{
				return false;
			}
		}else {
			throw new Exception("SQL ennor");
		}
		
	}
	
}


