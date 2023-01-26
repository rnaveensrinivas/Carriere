<%@page import="com.DB.DBConnect"%>
<%@page import="java.sql.Connection"%>

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
	%>
	<h1
		style="padding-left: auto; padding-right: auto; margin-left: auto; margin-right: auto; margin-top: 2%; width: 30%;">Please
		Check your inbox.</h1>
	<img src="MailSent.png" alt="logo_with_tagline"
		style="display: block; margin-left: auto; margin-right: auto; margin-top: 2%; width: 30%;">
</body>
</html>