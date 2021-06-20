package model;

import java.sql.Date;

public class Book implements IBook {
	private int isbn;
	private String title;
	private String author;
	private double rating;
	private int numberOfPage;
	private int ratingCount;
	private Date publicationDate;
	private int stock;

	@Override
	public int ISBN() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String Title() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String Author() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double Rating() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int NumberOfPages() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int RatingCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Date PublicationDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int Stock() {
		// TODO Auto-generated method stub
		return 0;
	}

}
