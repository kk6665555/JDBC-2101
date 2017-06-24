package a001;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;







public class JDBC12 {

	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");

		try(Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/oneone",prop)) {
			
			DatabaseMetaData metadata =conn.getMetaData();
			boolean ok =  metadata.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);//遠端更改
			System.out.println(ok);
			
			Statement stmt = conn.createStatement(
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			String sql = "select * from member where id = 2";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();//指標須先指到
			System.out.println(rs.getString("account"));
			
			rs.updateString("account","Peter");
			rs.updateString("password","Peter");
			rs.updateRow();
			
			
			PreparedStatement pstmt = conn.prepareStatement("select * from member ",ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs2 = pstmt.executeQuery();
			while(rs2.next()){
				rs2.updateString("password", "2345");
				rs2.updateRow();
			}
			
			
			rs2.moveToInsertRow();
			rs2.updateString("account", "ss");
			rs2.updateString("password", "ss");
			rs2.updateString("realname", "ss");
			rs2.insertRow();
			System.out.println("ok");
		} catch (Exception e) {
			System.out.println(e);
	
		}
	}

}


