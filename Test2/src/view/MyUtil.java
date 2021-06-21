package view;

import java.io.*;
import java.net.URLEncoder;

public class MyUtil 
{
	public static void printHead(PrintWriter pw) 
	{
		pw.println("<html><head>");
		pw.println("<content=\"text/html; charset=Big5\">");
		pw.println("<title>Library</title>");
		pw.println("<script language=javascript>");
		pw.println("function checkDel(url) {if (confirm('Sure, y/n ?')) { location.href=url; } }");
		pw.println("function checkString(s1,s2) { if ((s1==\"\")||(s2==\"\")) {alert(\"Please fill complete\"); return false;}} ");
		pw.println("function checkOneStr(s1) { if (s1==\"\") { alert(\"Please fill complete\"); return false; } } ");
		pw.println("function checkModify(f) { ");
		pw.println(" if (f.length == 0) { alert(\"Please fill complete\"); return false; } ");
		pw.println(" if (confirm('Sure, y/n ?')) { return true; } ");
		pw.println(" return false; } ");
		pw.println("</script></head>");
		pw.println("<BODY TEXT=#FFFFFF BGCOLOR=#000000 link=#00FFFF vlink=#CCFF33 alink=#FFCCFF ><font size=2>");
		pw.println("<h3> Welcome To Library </h3>");
	} 
	
	public static void printMemberHead(PrintWriter pw, String name) 
	{
		pw.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">");
		pw.println("<head>");
		pw.println("<content=\"text/html; charset=Big5\">");
		pw.println("<title>Library</title>");
		pw.println("<h3>" + name + "</h3>");
		pw.println("<script language=javascript>");
		pw.println("function CheckString3(s1, s2, s3) { if ((s1==\"\")&&(s2==\"\")&&(s3==\"\")) {alert(\"Please fill complete\"); return false;}} ");
		pw.println("function checkBooks(n) { if(n < 5) alert(\"Borrow Successfully\"); else alert(\"You already borrow 5 books\")");
		pw.println("</script></head>");
		pw.println("</head>");
		pw.println("<BODY TEXT=#FFFFFF BGCOLOR=#000000>");
		pw.println("<h3> Welcome To Library </h3>");
	}
	
	public static void printRow(PrintWriter pw, String s1, String s2, String s3) 
	{
		String url="";
		try
		{
			url = URLEncoder.encode(s1, "UTF-8");
		}catch(UnsupportedEncodingException e)
		{

		}
		pw.println("<tr>");
		pw.println("<td width = 1000><a href=BookInfo?bookname=" + url + " />" + s1 + "</td>");
		pw.println("<td width = 500>" + s2 + "</td>");
		pw.println("<td width = 150>" + s3 + "</td>");
		
	}
	
	public static void endprintRow(PrintWriter pw)
	{
		pw.println("</tr>");
	}
	
	public static void printRow4(PrintWriter pw, String s1, String s2, String s3, String s4) 
	{
		pw.println("<tr>");
		pw.println("<td width = 200>" + s1 + "</td>");
		pw.println("<td width = 200>" + s2 + "</td>");
		pw.println("<td width = 200>" + s3 + "</td>");
		pw.println("<td width = 100>" + s4 + "</td>");
		pw.println("</tr>");
	}
	
	public static void PrintRegisterHead(PrintWriter pw) 
	{
		pw.println("<html><head>");
		pw.println("<content=\"text/html; charset=Big5\">");
		pw.println("<title>Library</title>");
		pw.println("<script language=javascript>");
		pw.println("function CheckString3(s1, s2, s3) { if ((s1==\"\")||(s2==\"\")||(s3==\"\")) {alert(\"Please fill complete\"); return false;}} ");
		pw.println("</script></head>");
		pw.println("<BODY TEXT=#FFFFFF BGCOLOR=#000000 link=#00FFFF vlink=#CCFF33 alink=#FFCCFF ><font size=2>");
		pw.println("<h3> Welcome To Library </h3>");
	}
	
	public static void printAlert(PrintWriter pw, String str)
	{
		pw.println("<script language=javascript>");
		pw.println("alert(\"" + str + "\");");
		pw.println("</script></head>");
	}
}