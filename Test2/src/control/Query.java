package control;

import java.io.IOException;
import java.io.PrintWriter;
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
		// TODO Auto-generated method stub
		/*HttpSession session = request.getSession(true);
		response.setLocale(new Locale(new String("zh"), new String("TW")));
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String usrName = (String)session.getAttribute("user");
		MyUtil.printMemberHead(pw, usrName);
		response.getWriter().append("Served at: ").append(request.getContextPath());*/
		doPost(request,response);
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
		if (usrName == null) response.sendRedirect("Login");
		else 
		{
			pw.println("<form action = QueryResult.jsp method=POST name=FORM1>");
			pw.println("Query Title : <input type = text name = title><br><br>");
			pw.println("<br><br><input type = submit onClick=\"return checkOneStr(FORM1.title.value);\" name=submit value=Submit>");
			pw.println("</form>");
			pw.println("<br><br><a href=menu><input type=button value=menu name=B1><br>");
			pw.println("</body></html>");
		}
	}

}
