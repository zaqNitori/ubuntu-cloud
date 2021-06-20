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
 * Servlet implementation class Query
 */
@WebServlet("/Query")
public class Query extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Query() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession(true);
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setLocale(new Locale(new String("zh"), new String("TW")));
		response.setContentType("text/html");
		String user = (String)session.getAttribute("user");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String isbn = request.getParameter("isbn");
		
		PrintWriter pw = response.getWriter();
		MyUtil.printMemberHead(pw, user);
		pw.println("<form action = Query method=GET name=FORM1>");
		pw.println("Find By Title : <input type = text name = title><br><br>");
		pw.println("Find By Author : <input type = text name = author><br><br>");
		pw.println("Find By ISBN : <input type = text name = isbn><br><br>");
		pw.println("<br><br><input type = submit onClick=\"return CheckString3(FORM1.title.value, FORM1.author.value, FORM1.isbn.value);\" name=submit value=Submit>");
		pw.println("</form>");
		pw.println("<br><br><a href=menu><input type=button value=menu name=B1><br><hr><br>");
		
		try {
			DBCon dbc = new DBCon();
			ResultSet rs;
			dbc.connect();
			if(title == "" && author == "" && isbn == "") {
				//Random display
			} else {
				rs = dbc.exec(String.format("select title, authors, average_rating from Book where 1=1%s%s%s;", (title == ""? "":String.format(" and title like '%%%s%%'", title)), (author == ""? "":String.format(" and authors like '%%%s%%'", author)), (isbn == ""? "":String.format(" and ISBN13='%s'", isbn))));
				pw.println("<table><tr>");
				pw.println("<th width=1000>Title</th>");
				pw.println("<th width=500>Authors</th>");
				pw.println("<th width=300 >Ave_Rating</th></tr>");
				while(rs.next()) {
					MyUtil.printRow(pw, rs.getString("title"), rs.getString("author"), rs.getString("average_rating"));
				}
				pw.println("</table>");
			}
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
		pw.println("</body></html>");
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession session = request.getSession(true);
		response.setLocale(new Locale(new String("zh"), new String("TW")));
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String usrName = (String)session.getAttribute("user");
		MyUtil.printMemberHead(pw, usrName);
		if (usrName == null)
		{
			session.setAttribute("action","query");
			response.sendRedirect("Login");
		}
	}

}
