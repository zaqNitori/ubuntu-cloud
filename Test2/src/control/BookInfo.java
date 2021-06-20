package control;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
		PrintWriter pw = response.getWriter();
		MyUtil.printHead(pw);
		HttpSession session = request.getSession(true);
		String title = "";
		try
		{
			title = URLDecoder.decode(request.getParameter("bookname"), "UTF-8");
		}catch(UnsupportedEncodingException e)
		{

		}
		pw.println(title);
		pw.println("</body></html>");
		pw.close();
	}
	
	/** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}
}