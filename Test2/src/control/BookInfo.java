package control;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.*;
import view.*;

/** Servlet implementation class Login */
@WebServlet("/BookInfo")

public class BookInfo extends HttpServlet 
{
	
	private static final long serialVersionUID = 1L;
	/** @see HttpServlet#HttpServlet() */
	public BookInfo() 
	{
		super(); // TODO Auto-generated constructor stub
	}
	/** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setLocale(new Locale(new String("zh"), new String("TW")));
		response.setContentType("text/html");
		DBCon dbc = new DBCon();
		dbc.connect();
		PrintWriter pw = response.getWriter();
		MyUtil.printHead(pw);
		HttpSession session = request.getSession(true);
		String title = "";
		pw.println("<a href=Query><input type=button value=Query name=B1></a><br><br>");
		try {
			title = URLDecoder.decode(request.getParameter("bookname"), "UTF-8");
		}catch(UnsupportedEncodingException e) { }
		try {
			ResultSet rs = dbc.exec(String.format("select * from Book where title like '%%%s%%", title));
			pw.println(String.format("ISBN : %s<br><br>", rs.getString("ISBN13")));
			pw.println(String.format("Title : %s<br><br>", rs.getString("title")));
			pw.println(String.format("Authors : %s<br><br>", rs.getString("authors")));
			pw.println(String.format("Number Of Pages : %s<br><br>", rs.getInt("num_page")));
			pw.println(String.format("Publication Date : %s<br><br>", rs.getString("publication")));
			pw.println(String.format("Average Rating : %s<br><br>", rs.getString("average_rating")));
			pw.println(String.format("Rating Count : %s<br><br>", rs.getString("ratings_count")));
			pw.println(String.format("Stock : %s<br><br>", rs.getString("stock")));
		} catch(SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
		dbc.close();
		//pw.println(title);
		pw.println("</body></html>");
		pw.close();
	}
	
	/** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}
}