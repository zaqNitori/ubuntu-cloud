package control;

import java.io.*;
import java.util.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.*;
import view.*;

/** Servlet implementation class Login */
@WebServlet("/Manage")

public class Manage extends HttpServlet 
{
	
	private static final long serialVersionUID = 1L;
	/** @see HttpServlet#HttpServlet() */
	public Manage() 
	{
		super(); // TODO Auto-generated constructor stub
	}
	/** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setLocale(new Locale(new String("zh"), new String("TW")));
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String user = (String)session.getAttribute("user");
		String action = request.getParameter("action");
		MyUtil.printMemberHead(pw, user);
		
		if(session.getAttribute("action") == "Insert Success")
			MyUtil.printAlert(pw, "Insert Success");
		else if(session.getAttribute("action") == "Delete Success")
			MyUtil.printAlert(pw, "Delete Success");
		else if(session.getAttribute("action") == "Borrowing")
			MyUtil.printAlert(pw, "Delete Failed. Somone is Borrowing This Book.");
		
		session.setAttribute("action", "");
		
		if(action == null)
		{
			pw.println("<a href=Manage?action=Insert>");
			pw.println("<input type = submit style = width:100 value = \"Add New Book\">");
			pw.println("</a><br><br>");
			pw.println("<a href=Manage?action=Delete>");
			pw.println("<input type = submit style = width:100 value = \"Delete a Book\">");
			pw.println("</a><br><br>");
		}
		else
		{
			if(action.matches("Insert")) {
				pw.println("<form action = Manage method = POST name = INSERT>");
				pw.println("ISBN: <input type = text name = isbn required><br><br>");
				pw.println("Title: <input type = text name = title required><br><br>");
				pw.println("Authors: <input type = text name = authors required><br><br>");
				pw.println("Number Of Pages: <input type = text name = number required><br><br>");
				pw.println("Publication Date: <input type = date name = date required><br><br>");
				pw.println("Average Rating: <input type = text name = ave_rating required><br><br>");
				pw.println("Rating Count: <input type = text name = rating_count required><br><br>");
				pw.println("Stock: <input type = text name = stock required><br><br>");
				pw.println("<input type = submit style = width:100 value = ADD>");
				pw.println("</form>");
			}
			else if(action.matches("Delete")) 
			{
				pw.println("<form action = Manage method = POST name = DELETE>");
				pw.println("ISBN: <input type = text name = isbn_delete required><br><br>");
				pw.println("<input type = submit style = width:100 value = DELETE>");
				pw.println("</form>");
			}
		}
		pw.println("<a href=menu>");
		pw.println("<input type = button style = width:100 value = \"Back to Menu\">");
		pw.println("</a></body></html>");
		pw.close();
	}
	
	/** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		//PrintWriter pw = response.getWriter();
		String user = (String)session.getAttribute("user");
		//MyUtil.printMemberHead(pw, user);
		String isbn_delete = request.getParameter("isbn_delete");
		ResultSet rs;
		DBCon dbc = new DBCon();
		dbc.connect();
		
		if(isbn_delete == null) {
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			String authors = request.getParameter("authors");
			int number = Integer.valueOf(request.getParameter("number"));
			int stock = Integer.valueOf(request.getParameter("stock"));
			String date = request.getParameter("date");
			SimpleDateFormat format = new SimpleDateFormat("MMddyyyy");
	        Date parsed = null;
    		try 
    		{
    	        parsed = format.parse(date);
    	    } catch (ParseException e1) {
    	        // TODO Auto-generated catch block
    	        e1.printStackTrace();
    	    }
	        java.sql.Date sql = new java.sql.Date(parsed.getTime());
			
			Double ave_rating = Double.valueOf(request.getParameter("ave_rating"));
			int rating_count = Integer.valueOf(request.getParameter("rating_count"));
			dbc.update(String.format("insert into Book (ISBN13, title, authors, num_page, publication, average_rating, ratings_count, stock) values('%s', '%s', '%s', %d, '%s', %f, %d, %d)"   
					, isbn, title, authors, number, sql.toString(), ave_rating, rating_count, stock));
			session.setAttribute("action", "Insert Success");
			response.sendRedirect("Manage");
		} else {
			rs = dbc.exec(String.format("select * from UserBook where ISBN13 = '%s'", isbn_delete));
			try
			{
				if(rs.next()) 
				{
					session.setAttribute("action", "Borrowing");
				}				
				else {
					dbc.update(String.format("delete from Book where ISBN13 = '%s'", isbn_delete));
					session.setAttribute("action", "Delete Success");
				}
				response.sendRedirect("Manage");
			}catch(SQLException ex)
            {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
			catch(NullPointerException ex)
            {
                System.out.println("SQLException: " + ex.getMessage());
            }
		}
		//pw.close(); 
		dbc.close();
	}
}