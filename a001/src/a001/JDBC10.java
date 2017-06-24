package a001;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;





public class JDBC10 {

	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");

		try(Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/oneone",prop)) {
			Statement stmt = conn.createStatement();
			String account = "mike5" , password = "abcd";
			member login ;
			if((login = check(stmt,account,password))!=null){
				System.out.println("Welcome,"+login.realname);
			}else{
				System.out.println("Error Login");
			}
			
//			ResultSet rs = stmt.executeQuery("select count(*) from git");
//			rs.next();
//			System.out.println(rs.getString(1));
			//System.out.println(rs.getRow());//指標位置
			System.out.println("ok");
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	
	static member check(Statement stmt,String account,String password) throws Exception{
		String [] ret=new String[4];
		String sql = "select * from member where account='" +
				account + "'and password ='" + password + "'";
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()){
			
			member member = new member(rs.getString("id"),rs.getString("account"),rs.getString("realname"));
			return member;
		}else{
			ret =null;
		}
		return null;
	
		
	}
}
class member{
	String id,account,password,realname;
	member(String id,String account,String realname){
		this.id=id;
		this.account=account;
		this.realname=realname;
	}


}


