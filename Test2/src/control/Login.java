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
@WebServlet("/Login")

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
		session.setAttribute("name", "12345");
		if((String)session.getAttribute("action") == "Register")
			MyUtil.printAlert(pw, "Register Success!");
		else if((String)session.getAttribute("action") == "error")
			MyUtil.printAlert(pw, "Invalid Account or Password!");
		//else if((String)session.getAttribute("action") == "setting")
		else if((String)session.getAttribute("action") == "setting"
				|| (String)session.getAttribute("action") == "query")
			MyUtil.printAlert(pw, "Login First!!");
			
		session.setAttribute("action", "");
		pw.println("<form action = Login method=POST name=FORM1>");
		pw.println("name : <input type = text name = name><br><br>");
		pw.println("passwd: <input type = password name = passwd><br><br>");
		pw.println("<br><br><input type = submit onClick=\"return checkString(FORM1.name.value, FORM1.passwd.value);\"style=width:60 value =Ok>");
		pw.println("</form>");
		pw.println("</body></html>");
		pw.println("<a href=Register><input type=button value=Register name=B1><br><br>");
		pw.println("<a href=menu><input type=button value=menu name=B1><br><br>");
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