package week3;

public class Test {
	public static void main(String [] argv) {
		Product [] p = new Product[3];
		Product p1 = new Product(1,"Book","Java",50);
		Product p2 = new Product(2, "Book", "C++", 90);
		Product p3 = new Product(3, "Book", "Python",80);
		p2.setTitle("Powershell");
		int total = p1.getPrice() + p2.getPrice()+p3.computeDiscount(0.8);
		System.out.println("Total="+total);
		System.out.println(p1.getTitle()+":"+p1.getPrice());
		System.out.println(p2.getTitle()+":"+p2.getPrice());
		System.out.println(p3.getTitle()+":"+p3.getPrice());
		DBCon dbc = new DBCon();
		dbc.connect();
		System.out.println("Hello");
	}
}