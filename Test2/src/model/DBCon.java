package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.HashMap;

public class DBCon 
{
	private Connection con;
	private String jdbcName;
	private String query;
	private ResultSet rs;
	private PreparedStatement pstmt;
	private Statement stmt;
	
	public DBCon() 
	{
		con = null;
		jdbcName = "com.mysql.cj.jdbc.Driver";
		//query = "SELECT * FROM customer";
		stmt = null;
		pstmt = null;
		rs = null;
	}
	
	/**
	 * 
	 */
	public void connect() 
	{
		try 
		{
			Class.forName(jdbcName); //載入jdbc驅動程式
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?" + "user=107590026&password=zaq78963"); //驅動程式管理器，取得mysql連線
			//con = DriverManager.getConnection("jdbc:mysql://140.124.184.211/library?" + "user=107590026&password=zaq78963");
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void close()
	{
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (con!= null) {
				con.close();
				con = null;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public Boolean check(String cname,String cpasswd)
	{
		Map<String,String> map = new HashMap<>();
		
		String name = null,passwd = null;
		ResultSet hrs;
		DBCon dbc = new DBCon();
		dbc.connect();
		hrs = dbc.exec("Select * from User");
		
		try
		{
			if(hrs == null) return false;
			while(hrs.next())
			{
				name = hrs.getString("account");
				passwd = hrs.getString("password");
				map.put(name, passwd);
			}
		}
		catch(SQLException ex)
		{
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		System.out.println(passwd);
		dbc.close();
		passwd = map.get(cname);
		if(passwd != null && cpasswd.compareTo(passwd) == 0) return true;
		else return false;
	}
	
	public void update(String sql)
	{
		
		try
		{
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.executeUpdate(sql);
		}
		catch(SQLException ex)
		{
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorEror: " + ex.getErrorCode());
		}
	}
	
	public ResultSet exec(String sql)
	{
		try
		{
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			rs = pstmt.executeQuery(sql);
		}
		catch(SQLException ex)
		{
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorEror: " + ex.getErrorCode());
		}
		return rs;
	}
	
	public static void test(String name, String passwd) 
	{
		DBCon dbc = new DBCon();
		Boolean b = dbc.check(name, passwd);
		System.out.println(name + "/" + passwd + "=>" + b);
		
	}
	
	public static void main(String [] argv) 
	{
		test("Tom","1234");
	}
}
