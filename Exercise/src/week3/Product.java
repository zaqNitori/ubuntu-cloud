package week3;
import java.io.*;

public class Product {
	private int id;
	private String type;
	private String title;
	private int price;
	
	public Product(int id,String type,String title,int price) {
		this.id =id;
		this.type = type;
		this.title = title;
		this.price = price;
	}
	
	public	int getPrice() {
		return this.price;
	}
	public	void setTitle(String newTitle) {
		this.title = newTitle;
	}
	public	String getTitle() {
		return this.title;
	}
	public int computeDiscount(double discount) {
		return (int)(this.price * discount);
	}

}
