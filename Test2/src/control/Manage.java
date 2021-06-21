package control;

import java.io.*;
import java.util.*;
import java.io.IOException;
import java.sql.Date;

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
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setLocale(new Locale(new String("zh"), new String("TW")));
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String user = (String)session.getAttribute("user");
		MyUtil.printMemberHead(pw, user);
		
		if(session.getAttribute("action") == "Insert Success")
			MyUtil.printAlert(pw, "Insert Success");
		else if(session.getAttribute("action") == "Delete Success")
			MyUtil.printAlert(pw, "Delete Success");

		if(request.getParameter("action") == "Insert") {
			pw.println("<form action = Manage method = POST name = INSERT>");
			pw.println("ISBN: <input type = text name = isbn><br><br>");
			pw.println("Title: <input type = text name = title><br><br>");
			pw.println("Authors: <input type = text name = authors><br><br>");
			pw.println("Number Of Pages: <input type = text name = number><br><br>");
			pw.println("Publication Date: <input type = text name = date><br><br>");
			pw.println("Average Rating: <input type = text name = ave_rating><br><br>");
			pw.println("Rating Count: <input type = text name = rating_count><br><br>");
			pw.println("<input type = submit onClick = \"return CheckString7(INSERT.isbn.value, INSERT.title.value, INSERT.authors.value, INSERT.number.value, INSERT.date.value, INSERT.ave_rating.value, INSERT.rating_count.value) style = width:100 value = Add>");
			pw.println("</form>");
		}else if(request.getParameter("action") == "Delete") {
			pw.println("<form action = Manage method = POST name = DELETE>");
			pw.println("ISBN: <input type = text name = isbn_delete><br><br>");
			pw.println("<input type = submit onClick = \"return checkOneStr(DELETE.isbn_delete.value)\" style = width:100 value = Delete>");
			pw.println("</form>");
		}else {
			pw.println("<a href=Manage?action=Insert>");
			pw.println("<input type = submit style = width:100 value = \"Add New Book\"");
			pw.println("</a><br><br>");
			pw.println("<a href=Manage?action=Delete>");
			pw.println("<input type = submit style = width:100 value = \"Delete a Book\"");
			pw.println("</a>");
			pw.println("<a href=menu>");
			pw.println("<input type = submit style = width:100 value = \"Back to Menu\"");
			pw.println("</a>");
		}
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
		DBCon dbc = new DBCon();
		dbc.connect();
		
		if(isbn_delete == "") {
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			String authors = request.getParameter("authors");
			String number = request.getParameter("number");
			Date date = request.getParameter("date");
			Double ave_rating = request.getParameter("ave_rating");
			int rating_count = request.getParameter("rating_count");
			dbc.update(String.format("insert into Book (ISBN13, title, authors, num_page, publication, average_rating, ratings_count)", isbn, title, authors, number, date, ave_rating, rating_count));
			session.setAttribute("action", "Insert Success");
			response.sendRedirect("Manage");
		} else {
			dbc.update(String.format("delete from Book where ISBN13 = '%s'", isbn_delete));
			session.setAttribute("action", "Delete Success");
			response.sendRedirect("Manage");
		}
		//pw.close(); 
	}
}