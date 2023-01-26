<%@page import="com.DB.DBConnect"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.dao.UserDAO"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CARRIÈRE</title>
<%@include file="all_css.jsp"%>
</head>
<body>
	<%@include file="indexNavbar.jsp"%>
	<%
	response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
	response.setHeader("Expires", "0");

	if (session.getAttribute("isAdmin") != null )
		response.sendRedirect("adminIndex.jsp");
	if (session.getAttribute("isUser") != null )
		response.sendRedirect("userIndex.jsp");

	if( request.getParameter("code") == null || request.getParameter("code") == "")
		response.sendRedirect("index.jsp");
	
	int code = Integer.parseInt(request.getParameter("code"));
	int ret = new UserDAO(DBConnect.getConn()).verify(code);
	if (ret == 1) {
	%>
	<h1>Account Verified. Proceed to login.</h1>
	<%
	} else {
	%>
	<h1>Invalid Account.</h1>
	<%
	}
	%>
</body>
</html>