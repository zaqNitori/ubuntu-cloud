package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Product 
{
	private int pid;
	private String type;
	private String title;
	private int price;
	
	public Product(int pid,String type,String title,int price)
	{
		this.pid = pid;
		this.type = type;
		this.title = title;
		this.price = price;
	}
	
	public int getPID() { return pid; }
	
	public int getPrice() { return price; }
	
	public String getType() { return type; }
	
	public void setTitle(String newTitle) { title = newTitle; }
	
	public String getTitle() { return title; }
	
	public int computeDiscount(double discount)
	{
		return (int)(price*discount);
	}
	
	public static int searchPriceByTitle(String title) 
	{
		int price=0;
		ResultSet hrs=null;
		DBCon dbc = new DBCon();
		dbc.connect();
		hrs = dbc.exec("SELECT price FROM store where title='" + title +"'");
		try 
		{
			hrs.next();
			price = hrs.getInt("price");
		}
		catch (SQLException ex) 
		{
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		System.out.println("price: " + price);
		dbc.close();
		return price;
	} 
	
	public static ArrayList<Product> getAllProduct() 
	{
		ArrayList<Product> products = new ArrayList<Product>();
		Product product=null;
		int pid=0, price=0;
		String type=null, title = null;
		ResultSet hrs;
		DBCon dbc = new DBCon();
		dbc.connect();
		hrs = dbc.exec("SELECT * FROM store");
		try 
		{
			while (hrs.next()) 
			{
				pid = hrs.getInt("id");
				type = hrs.getString("type");
				title = hrs.getString("title");
				price = hrs.getInt("price");
				product = new Product(pid, type, title, price);
				products.add(product);
			}
		}catch (SQLException ex) 
		{
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		dbc.close();
		return products;
	}
	
	public static void testSearchByTitle()
	{
		int price = searchPriceByTitle("C++");
		System.out.println(price);
	}
	
	public static void main(String [] args)
	{
		testSearchByTitle();
		ArrayList<Product> products = getAllProduct();
		for(Product p:products)
		{
			System.out.println(p.getPID() + " " + p.getType() + 
					" " + p.getTitle() + " " + p.getPrice());
		}
	}
	
}
