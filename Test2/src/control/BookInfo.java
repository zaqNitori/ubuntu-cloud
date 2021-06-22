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
		String isbn = request.getParameter("ISBN13");
		String url = String.format("title=%s&author=%s&isbn=%s&submit=Submit"
				, request.getParameter("title")
				, request.getParameter("author")
				, request.getParameter("isbn"));
		pw.println("<a href=Query?" + url + " ><input type=button value=Query name=B1></a><br><br>");

		ResultSet rs = dbc.exec(String.format("select * from Book where ISBN13='%s'", isbn));
		try {
			if(rs.next())
			{
				System.out.println(isbn);
				pw.println(String.format("<h1>ISBN : %s</h1><br>", isbn));
				pw.println(String.format("<h1>Title : %s</h1><br>", rs.getString("title")));
				pw.println(String.format("<h1>Authors : %s</h1><br>", rs.getString("authors")));
				pw.println(String.format("<h1>Number Of Pages : %s</h1><br>", rs.getInt("num_page")));
				pw.println(String.format("<h1>Publication Date : %s</h1><br>", rs.getString("publication")));
				pw.println(String.format("<h1>Average Rating : %s</h1><br>", rs.getString("average_rating")));
				pw.println(String.format("<h1>Rating Count : %s</h1><br>", rs.getString("ratings_count")));
				pw.println(String.format("<h1>Stock : %s</h1><br>", rs.getString("stock")));
			}
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