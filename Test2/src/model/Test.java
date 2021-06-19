package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
	
	public static void main(String [] args)
	{
		DBCon dbc = new DBCon();
		ResultSet rs;
		//dbc.setQuery("Select * from store");
		dbc.connect();
		//dbc.update("insert into test (msg) values(\"hahaha\")");
		rs = dbc.exec("select * from test");
		try 
		{
			while (rs.next()) 
			{
				System.out.println(rs.getString("msg"));
			}
		}
		catch (SQLException ex)
		{
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		dbc.close();
	}
	
}
