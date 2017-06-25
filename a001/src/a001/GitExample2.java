package a001;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

public class GitExample2 {

	public static void main(String[] args) {
		
		long start = System.currentTimeMillis();

		
		
		String strurl ="http://data.coa.gov.tw/Service/OpenData/ODwsv/ODwsvAgriculturalProduce.aspx"; 
		String json =getjson(strurl);
		
		
			
			Properties prop = new Properties();
			prop.setProperty("user", "root");
			prop.setProperty("password", "root");
			
			try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/oneone",prop)){
				String sql = "INSERT INTO git ( gid, Name, Feature, SalePlace, ProduceOrg, SpecAndPrice, OrderUrl, ContactTel, Column1)" + "VALUES (?,?,?,?,?,?,?,?,?)";
				
				PreparedStatement psymet = conn.prepareStatement(sql);
			
				
				JSONArray root = new JSONArray(json);
//				System.out.println(root.length());
				for(int i = 0;i<root.length();i++){
					JSONObject row = root.getJSONObject(i);
					String gid =  row.getString("ID");
					String Name = row.getString("Name");
					String Feature = row.getString("Feature");
					String SalePlace = row.getString("SalePlace");
					String ProduceOrg = row.getString("ProduceOrg");
					String SpecAndPrice = row.getString("SpecAndPrice");
					String OrderUrl = row.getString("OrderUrl");
					String ContactTel = row.getString("ContactTel");
					String Column1 = row.getString("Column1");
//					System.out.println(gid + ":" + Name);
					// 最好做 資料檢查與過濾
					psymet.setString(1, gid );
					psymet.setString(2, Name);
					psymet.setString(3, Feature);
					psymet.setString(4, SalePlace);
					psymet.setString(5, ProduceOrg);
					psymet.setString(6, SpecAndPrice);
					psymet.setString(7, OrderUrl);
					psymet.setString(8, ContactTel);
					psymet.setString(9, Column1);
					
					
					psymet.addBatch();//先儲存放旁邊  增加一點點效率
				}
				
				psymet.executeBatch();
				System.out.println("ok");
				System.out.println(System.currentTimeMillis() - start);
				}catch(Exception e){
			System.out.println(e);
			}
		
	}
		
		private static String getjson(String strurl){
			StringBuilder sb = new StringBuilder();
			try {
				URL url = new URL(strurl);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.connect();
				InputStream in = conn.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
				
				String line =null;
				while((line = reader.readLine()) != null){
					sb.append(line.trim());//trim 兩邊去空格
				}
				reader.close();
				System.out.println(sb);
				
			} catch (Exception e) {
				System.out.println(e);
			}
			return sb.toString();
		}
	

}
