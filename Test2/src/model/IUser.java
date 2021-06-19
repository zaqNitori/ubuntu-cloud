package model;

import java.util.List;

public interface IUser {
	public int ID();
	public String Account();
	public String Email();
	public List<String> Books();
}
