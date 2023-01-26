<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@page import="com.DB.DBConnect"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.dao.UserDAO"%>
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

	<c:if test="${not empty userEditProfileMessage}">
		<div class="alert alert-dark" role="alert">${userEditProfileMessage}</div>
		<c:remove var="userEditProfileMessage" />
	</c:if>
	<%
	UserDAO dao = new UserDAO(DBConnect.getConn());
	String email = (String) session.getAttribute("email");
	boolean isSubscribed = dao.getNotificationStatus(email);
	%>


	<div class="card"
		style="width: 60rem; margin-left: auto; margin-right: auto; margin-top: 1%;">

		<div class="card-body">
			<form action="editProfile" method="get"></form>
			<div class="form-check">
				<%
				if (isSubscribed) {
				%>
				<input type="checkbox" class="form-check-input" id="exampleCheck1"
					name="notificationStatus" value="1" checked> <label
					class="form-check-label" for="exampleCheck1">Notify me when
					new job is posted</label>
				<%
				} else {
				%>
				<input type="checkbox" class="form-check-input" id="exampleCheck1"
					name="notificationStatus" value="1"> <label
					class="form-check-label" for="exampleCheck1">Notify me when
					new job is posted</label>
				<%
				}
				%>
			</div>
			<div class="form-group" style="padding-top: 10px;">
				<input type="submit" class="btn btn-primary" value="Save"
					style="background-color: black; border: none;">
			</div>


			</form>
		</div>
	</div>
</body>
</html>