<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@page import="com.DB.DBConnect"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.dao.JobDAO"%>
<%@page import="com.dao.UserDAO"%>
<%@page import="java.util.*"%>
<%@page import="com.entity.Job"%>
<%@page import="com.entity.User"%>

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
	<%@include file="adminNavbar.jsp"%>

	<%
	response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
	response.setHeader("Expires", "0");

	if (session.getAttribute("isAdmin") == null || request.getParameter("id") == null || request.getParameter("id") == "")
		response.sendRedirect("adminIndex.jsp");
	%>

	<c:if test="${not empty downloadEnrolledUserMessage}">
		<div class="alert alert-success" role="alert">${downloadEnrolledUserMessage}</div>
		<c:remove var="downloadEnrolledUserMessage" />
	</c:if>
	<div class="card"
		style="width: 60rem; margin-left: auto; margin-right: auto; margin-top: 1%;">
		<%
		int id = Integer.parseInt(request.getParameter("id"));
		JobDAO dao = new JobDAO(DBConnect.getConn());
		Job j = dao.getJobById(id);
		if (j == null) {
			response.sendRedirect("adminIndex.jsp");
			return;
		}
		UserDAO udao = new UserDAO(DBConnect.getConn());
		List<User> users = udao.getUserEnrolledList(id);
		if (users == null) {
		%>

		<div class="alert alert-secondary" role="alert">
			<h5 align=center>Enrolled list is empty.</h5>
		</div>

		<%
		} else {
		int i = 1;
		%>
		<div class="form-group"
			style="margin-left: auto; margin-right: auto; padding: 10px;">
		
			<a href="downloadList?id=<%=j.getId()%>" class="btn btn-light"
				style="background-color: black; color: white; border: none;">Download
				List</a>
			<button class="btn btn-primary"
				style="background-color: black; color: white; border: none;">
				<a href="adminComposeMail.jsp?id=<%=id%>"
					style="color: white; text-decoration: none">Compose Mail</a>
			</button>
		</div>

		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">Full Name</th>
					<th scope="col">Email</th>
					<th scope="col">Experience</th>
				</tr>
			</thead>
			<tbody>

				<%
				for (User u : users) {
					System.out.println(u);
				%>

				<tr>
					<th scope="row"><%=i++%></th>
					<td><%=u.getFullname()%></td>
					<td><%=u.getEmail()%></td>
					<td><%=u.getExperience()%></td>
				</tr>

				<%
				}
				%>
			</tbody>
		</table>
		<%
		}
		%>
	</div>
</body>
</html>