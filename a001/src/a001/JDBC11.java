package a001;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;





public class JDBC11 {

	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");

		try(Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/oneone",prop)) {
			Statement stmt = conn.createStatement();
//			String sql = "select * from git where ProduceOrg='水里鄕農會'";
			
			String keyword="苗栗";
			String sqlCond = "where Name like '%"+ keyword+ "%'"
					+ "or Feature like '%"+ keyword+ "%'"
					+ "or SalePlace like '%"+ keyword+ "%'";
			String sqlCount = "select count(*) as num from git " + sqlCond;
			ResultSet rsCount = stmt.executeQuery(sqlCount);
			if(!rsCount.next()) return;
			int total = rsCount.getInt("num");
			int page = 1;
			int rpp =10;
			
			
			
			int lastPage=(total%rpp==0)?(total/rpp):(total/rpp+1);
			
			page = (page<=lastPage)?page:lastPage;
			
			int start = (page-1)*rpp;
			String sql ="select * from git "+sqlCond +" limit "+start+","+ rpp;//git後面 limit 後面 須要有空格
			ResultSet rs =stmt.executeQuery(sql);
			int i = 0;
			while(rs.next()){
				String gid = rs.getString("gid");
				String name = rs.getString("Name");
				System.out.println((++i) + ":"+ gid +";"+ name);
			}
			
			System.out.println("ok");
		} catch (Exception e) {
			System.out.println(e);
	
		}
	}

}


