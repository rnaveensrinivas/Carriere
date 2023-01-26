<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
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

	<%@include file="userNavbar.jsp"%>
	<%
	response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
	response.setHeader("Expires", "0");

	if (session.getAttribute("isAdmin") != null)
		response.sendRedirect("adminIndex.jsp");
	if (session.getAttribute("isUser") == null)
		response.sendRedirect("index.jsp");
	%>
	<img src="logoTagline.png" alt="logo_with_tagline"
		style="display: block; margin-left: auto; margin-right: auto; margin-top: 5%; width: 30%;">
</body>
</html>