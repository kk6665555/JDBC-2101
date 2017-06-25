package a001;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;



public class MyDataTable  extends JFrame{
	private JTable table;
	private String [][] data = {{"01","02","03","04"},{"11","12","13","14"},{"21","22","23","24"},{"31","32","33","34"}};
	private String[] files = {"f1","f2","f3","f4"};
	private MytableModel model;
	
	private Connection conn;
	private ResultSet rs;
	private int DataCount;
	
	public MyDataTable(){
		super("客戶資料管理");
		setLayout(new BorderLayout());
		
		
		try {
			init();
		} catch (Exception e) {
		}
		
		
		model = new MytableModel();
		table = new JTable(model);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				System.out.println(e.getFirstIndex() + "x" + e.getLastIndex());
				
			}
		});
		
		
		JScrollPane jsp =new JScrollPane(table);
		add(jsp,BorderLayout.CENTER);
		
		
		setVisible(true);
		setSize(800,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private class MytableModel extends DefaultTableModel{
		@Override
		public String getColumnName(int column) {
			return files[column];
		}
		@Override
		public int getColumnCount() {
			return files.length;
		}
		@Override
		public int getRowCount() {
			return DataCount;
		}
		@Override
		public Object getValueAt(int row, int column) {
			try {
				rs.absolute(row+1);
				return rs.getString((column)+1);
			} catch (SQLException e) {
				return ("----");
			}
			
		}
		@Override
		public void setValueAt(Object aValue, int row, int column) {
			super.setValueAt(aValue, row, column);
			data[row][column] = (String) aValue;
			System.out.println(aValue + ";" + row + ";" + column);
			
		}
		
		@Override
		public boolean isCellEditable(int row, int column) {
			if(column == 2){//第二個欄位
				return false;
			}else{
				return true;
			}
		}
	}
	
	
	private void init(){
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");
		
		
		try{
		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/oneone",prop);
		String sql = "select count(*) from git  ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		rs.next(); DataCount = rs.getInt(1);
		
		
		sql = "select * from git order by ProduceOrg ";
		pstmt=conn.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
		rs = pstmt.executeQuery();
		
		ResultSetMetaData metadata = rs.getMetaData();
		files = new String[metadata.getColumnCount()];
		
		for (int i=0; i<files.length; i++){
			files[i] = metadata.getColumnLabel(i+1);
		}
		}catch(Exception e){
			
		}
	}
	public static void main(String[] args) {
		new MyDataTable();

	}

}
