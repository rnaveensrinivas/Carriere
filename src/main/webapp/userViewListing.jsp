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
	<%@include file="userNavbar.jsp"%>

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
	<!-- <div class="card"
		style="width: 80rem; margin-left: auto; margin-right: auto; margin-top: 1%;"> -->
		<div class='search-worker'>
			<%
			JobDAO dao = new JobDAO(DBConnect.getConn());
			List<Job> list = dao.getAllJob();
			for (Job j : list) {
			%>

			<div class="card" style='width: 18rem; margin: 20px; float: left'>
				<div class="card border-dark mb-3" style='text-align: center;'>
					<div class="card-header">
						<h5 class="card-title" align="center"><%=j.getRole()%></h5>
					</div>
					<div class="card-body text-dark">
						<p class="card-text">
							Location:
							<%=j.getLocation()%><br> Pay:
							<%=j.getPay()%><br> Date Of Interview:
							<%=j.getDateOfInterview()%><br>
						</p>
					</div>
					<div class="card-footer">
						<a href="userViewJob.jsp?id=<%=j.getId()%>" class="btn btn-light"
							style="background-color: black; color: white; border: none;">View</a>
					</div>
				</div>
			</div>
		</div>
		<%
		}
		%>
	<!-- </div> -->
</body>
</html>