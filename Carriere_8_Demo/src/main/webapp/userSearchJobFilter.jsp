<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@page import="com.DB.DBConnect"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.dao.JobDAO"%>
<%@page import="java.util.*"%>
<%@page import="com.entity.Job"%>

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
	<%@include file="navbar.jsp"%>
	<%
	response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
	response.setHeader("Expires", "0");

	if (session.getAttribute("isAdmin") != null)
		response.sendRedirect("adminIndex.jsp");
	if (session.getAttribute("isUser") == null)
		response.sendRedirect("index.jsp");
	%>
	<c:if test="${not empty msg}">
		<div class="alert alert-success" role="alert">${ msg}</div>
		<c:remove var="msg" />
	</c:if>
	<form action="login" method="post">
		Location: <select id="location" name="location"><option
				selected value="Chennai">Chennai</option>
			<option value="Bombay">Bombay</option></select> Category: <select
			id="category" name="category"><option selected value="IT">IT</option>
			<option value="Accounting">Accounting</option></select> <input type="submit">
	</form>
	<%
	String location = request.getParameter("location");
	String category = request.getParameter("category");
	JobDAO dao = new JobDAO(DBConnect.getConn());
	List<Job> list = dao.getJobFiltered(location, category);

	if (list.isEmpty()) {
	%>
	OOps... No entry found.
	<%
	} else {
	for (Job j : list) {
	%>
	<p><%=j.getId()%></p>
	<p><%=j.getTitle()%></p>

	<%
	if (j.getDescription().length() < 120) {
	%>
	<p><%=j.getDescription()%></p>
	<%
	} else {
	%>
	<p><%=j.getDescription().substring(0, 120)%>...
	</p>
	<%
	}
	%>

	<p><%=j.getCategory()%></p>
	<p><%=j.getStatus()%></p>
	<p><%=j.getLocation()%></p>
	<p><%=j.getPdate()%></p>
	<a href="view_job.jsp?id=<%=j.getId()%>" class="btn btn-light">View</a>
	<%
	}
	}
	%>


</body>
</html>