package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.*;
import view.MyUtil;

/**
 * Servlet implementation class menu
 */
@WebServlet("/menu")
public class menu extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public menu() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		String user = (String)session.getAttribute("user");
		PrintWriter pw = response.getWriter();
		//pw.append("Served at: ").append(request.getContextPath());
		response.setLocale(new Locale(new String("zh"), new String("TW")));
		response.setContentType("text/html");
		if(user == null)
		{
			MyUtil.printHead(pw);
			if(session.getAttribute("action") == "LogOut")
			{
				MyUtil.printAlert(pw, "LogOut Successfully!");
				session.setAttribute("action", "");
			}
			pw.println("<a href=Login><input type=button value=Login name=B1><br><br>");
			pw.println("<a href=Register><input type=button value=Register name=B2><br><br>");
			pw.close();
		}
		else
		{
			session.setAttribute("user", user);
			doPost(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		//doGet(request,response);
		HttpSession session = request.getSession(true);
		response.setLocale(new Locale(new String("zh"), new String("TW")));
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String user = (String)session.getAttribute("user");
		MyUtil.printMemberHead(pw, user);
		int privilege = 0;

		if (user==null) response.sendRedirect("Login");
		else 
		{
			ResultSet rs;
			DBCon dbc = new DBCon();
			dbc.connect();
			int id = dbc.getID(user);
			rs = dbc.exec(String.format("select privilege as pri from User where id=%d", id));
			try
            {
                if(rs.next())
                {
                	privilege = rs.getInt("pri");
                }
            }
            catch(SQLException ex)
            {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
			rs = dbc.exec(String.format("select count(*) as number from UserBook where UserID=%d", id));
			try
            {
                if(rs.next())
                {
                	session.setAttribute("number", rs.getInt("number"));
                	System.out.println(rs.getInt("number"));
                }
            }
            catch(SQLException ex)
            {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
			
			pw.println("Hello~ " + user + "<br>");
			pw.println("<a href=Setting><input type=button value=Setting name=B2><br><br>");
			pw.println("<a href=Query><input type=button value=Query name=B1><br><br>");
			if(privilege == 10)
				pw.println("<a href=Manage><input type=button value=Manage name=B1><br><br>");
			pw.println("<a href=Logout><input type=button value=Logout name=B1><br><br>");
		}
	}

}
