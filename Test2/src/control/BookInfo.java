package control;

import java.io.*;
import java.util.*;
import java.io.IOException;
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

public class Login extends HttpServlet 
{
	
	private static final long serialVersionUID = 1L;
	/** @see HttpServlet#HttpServlet() */
	public Login() 
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
		pw.println(request.getParameter("bookname"));
		pw.println("</body></html>");
		pw.close();
	}
	
	/** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		String userName = request.getParameter("name");
		String userPasswd = request.getParameter("passwd");
		DBCon dbc = new DBCon();
		PrintWriter pw = response.getWriter();
		Boolean b = dbc.check(userName, userPasswd);
		MyUtil.printHead(pw);
		
		if (b==true) 
		{
			pw.println("name:" + userName + ", password:" + userPasswd);
			session.setAttribute("user", userName);
			session.setAttribute("action", "Login");
			response.sendRedirect("menu");
		}
		else 
		{
				
			session.setAttribute("action", "error");
			response.sendRedirect("Login");
		}
		pw.close(); 
	}
}