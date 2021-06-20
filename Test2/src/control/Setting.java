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
@WebServlet("/Setting")
public class Setting extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Setting() 
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		//doGet(request,response);
		ResultSet rs,rs2;
		HttpSession session = request.getSession(true);
		response.setLocale(new Locale(new String("zh"), new String("TW")));
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String user = (String)session.getAttribute("user");
		MyUtil.printHead(pw);
		
		if (user==null)
		{
			session.setAttribute("action","setting");
			response.sendRedirect("Login");
		}

		pw.println("<p>Hello~ " + user + "</p><br>");
		pw.println("<a href=menu><input type=button value=menu name=menu><br><br>");
		
		DBCon dbc = new DBCon();
        dbc.connect();
        int id;
        id = dbc.getID(user);
        String isbn;
        rs = dbc.exec(String.format("select * from UserBook where UserID=%d", id));
        try
		{
        	pw.println("<table border=\"1\" style=\"margin: 0 auto; text-align: center\">");
        	MyUtil.printRow4(pw, "Title", "borrowDate", "returnDate", "renew_count");
            while(rs.next())
            {
            	isbn = rs.getString("ISBN13");
                rs2 = dbc.exec(String.format("select title from Book where ISBN13='%s'", isbn));
                try
                {
                    if(rs2.next())
                    {
                    	MyUtil.printRow4(pw
                                , rs2.getString("title")
                                , rs.getDate("borrowDate").toString()
                                , rs.getDate("returnDate").toString()
                                , rs.getString("renew_count"));
                    }
                }
                catch(SQLException ex)
                {
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
                }

            }
            pw.println("</table>");
		}
		catch(SQLException ex)
		{
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		dbc.close();
		
	}

}
