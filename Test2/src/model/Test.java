package model;

public class Test {
	
	public static void main(String [] args)
	{
		DBCon dbc = new DBCon();
		//dbc.setQuery("Select * from store");
		dbc.connect();
		Customer cus = new Customer("Lai","123",1,1000);
		System.out.println(cus.getName());
	}
	
}
