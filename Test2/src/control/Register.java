package control;

import java.io.*;
import java.util.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.*;
import view.*;

/** Servlet implementation class Login */
@WebServlet("/Register")

public class Register extends HttpServlet 
{
	
	private static final long serialVersionUID = 1L;
	/** @see HttpServlet#HttpServlet() */
	public Register() 
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
		MyUtil.PrintRegisterHead(pw);
		pw.println("<form action = Register method = POST name = RegisterForm>");
		pw.println("Account: <input type = text name = account><br><br>");
		pw.println("Password: <input type = password name = password><br><br>");
		pw.println("E-mail: <input type = text name = email><br><br>");
		pw.println("<input type = submit onClick = \"return CheckString3(RegisterForm.account.value, RegisterForm.password.value, RegisterForm.email.value);\" style = width:60 value = Submit>");
		pw.println("</form>");
		pw.println("</body></html>");
		pw.close();
	}
	
	/** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		DBCon dbc = new DBCon();
		dbc.connect();
		ResultSet rs = dbc.exec(String.format("select * from User where account='%s' or email='%s';", account, email));
		//PrintWriter pw = response.getWriter();
		//yUtil.PrintRegisterHead(pw);
		try {
			if (rs.next()) 
			{
				response.sendRedirect("Register");
			}
			else 
			{
				dbc.update(String.format("insert into User (account, password, email, privilege) values('%s', '%s', '%s', %d)", account, password, email, 1));
				session.setAttribute("action", "Register");
				response.sendRedirect("Login");
			}
		} catch(SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
		//pw.close(); 
	}
}