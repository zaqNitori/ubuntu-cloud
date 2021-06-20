package model;

import java.sql.Date;

public interface IBook {
	public int ISBN();
	public String Title();
	public String Author();
	public double Rating();
	public int NumberOfPages();
	public int RatingCount();
	public Date PublicationDate();
	public int Stock();
	//public boolean InStock();
}
