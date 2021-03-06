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
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setLocale(new Locale(new String("zh"), new String("TW")));
		response.setContentType("text/html");
		String user = (String)session.getAttribute("user");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String isbn = request.getParameter("isbn");
		String action = request.getParameter("action");
		String url = String.format("title=%s&author=%s&isbn=%s&submit=Submit", title, author, isbn);
		PrintWriter pw = response.getWriter();
		int number=0, id;
		number = (int)session.getAttribute("number");
		
		if(action != null)
		{
			if(action.matches("borrow"))
			{
				id = (int)session.getAttribute("UserID");
				if(number > 4)
					MyUtil.printAlert(pw, "You already borrow 5 books!!");
				else
				{
					session.setAttribute("number", number+1);
					String isbn13 = request.getParameter("ISBN13");
					DBCon dbc = new DBCon();
					ResultSet rs;
					dbc.connect();
					dbc.update(String.format("Update Book set stock=(stock-1) where ISBN13='%s'", isbn13));
					dbc.update(String.format("Insert into UserBook (UserID,ISBN13) values (%d,'%s');",id, isbn13));
					dbc.update(String.format("update UserBook set returnDate=DATE_ADD(borrowDate,INTERVAL 30 DAY) where ISBN13='%s';",isbn13));
					response.sendRedirect("Query?action=borrowOK&" + url);
				}
			}
			else if(action.matches("borrowOK"))
				MyUtil.printAlert(pw, "Borrow Successfully!");
		}
		
		MyUtil.printMemberHead(pw, user);
		pw.println("<h2 style=\"color:red;\">You already borrow " + String.valueOf(number) + " Books</h2>");
		pw.println("<form action = Query method=GET name=FORM1>");
		pw.println("Find By Title : <input type = text name = title><br><br>");
		pw.println("Find By Author : <input type = text name = author><br><br>");
		pw.println("Find By ISBN : <input type = text name = isbn><br><br>");
		pw.println("<br><br><input type = submit onClick=\"return CheckString3(FORM1.title.value, FORM1.author.value, FORM1.isbn.value);\" name=submit value=Submit>");
		pw.println("</form>");
		pw.println("<br><br><a href=menu><input type=button value=menu name=B1></a><br><hr><br>");
		
		try {
			int stock;
			DBCon dbc = new DBCon();
			ResultSet rs;
			dbc.connect();
			if(title == "" && author == "" && isbn == "") {
				//Random display
			} else {
				rs = dbc.exec(String.format("select * from Book where 1=1%s%s%s;", (title == ""? "":String.format(" and title like '%%%s%%'", title)), (author == ""? "":String.format(" and authors like '%%%s%%'", author)), (isbn == ""? "":String.format(" and ISBN13='%s'", isbn))));
				pw.println("<table><tr>");
				pw.println("<th width=1000>Title</th>");
				pw.println("<th width=500>Authors</th>");
				pw.println("<th width=150>Ave_Rating</th>");
				pw.println("<th width=50>Stock</th></tr>");
				while(rs.next()) {
					url += "&ISBN13=" + rs.getString("ISBN13");
					MyUtil.printRow(pw, rs.getString("title"), rs.getString("authors"), rs.getString("average_rating"), url);
					stock = rs.getInt("stock");
					pw.println("<td width = 50>" + String.valueOf(stock) + "</td>");
					if(stock > 0)
					{
						pw.println("<td width = 100>");
						pw.println("<a href=Query?action=borrow&" + url + "><input type=button value=Borrow name=borrow>");
						pw.println("</td>");  
					}
					MyUtil.endprintRow(pw);
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
