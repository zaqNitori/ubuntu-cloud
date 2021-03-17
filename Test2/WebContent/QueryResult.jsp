<%@ page language="java" contentType="text/html; charset=UTF-8" import="model.*,control.*"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Query Result</title>
	</head>
	<body>
		<%
		String strSub = request.getParameter("submit");
		Customer customer = (Customer)session.getAttribute("customer");
		if (customer==null) response.sendRedirect("Login");
		else if(strSub == null) 
		{
			response.sendRedirect("Query");
		}
		else 
		{
			String title = request.getParameter("title");
			MyUtil.printHead(response.getWriter());
			int price = Product.searchPriceByTitle(title);
			out.println("Title: " +title + ", Price: " + price + "<br><br>");
			out.println("<a href=menu><input type=button value=Menu name=B1><br><br><br>");
		}
		%>
	</body>
</html>