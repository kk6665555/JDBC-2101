package a001;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;

public class test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//1.driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("ok");
		} catch (ClassNotFoundException e) {
			System.out.println(e);
			System.exit(0);
		}
		//2connection
		try {
			Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/test?" +
			        "user=minty&password=greatsqldb");
			System.out.println("ok");
			
			//3 SQL Statment
			
			Statement stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
