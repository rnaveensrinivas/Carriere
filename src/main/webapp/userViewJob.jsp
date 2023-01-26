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


	<%
	int id = Integer.parseInt(request.getParameter("id"));
	JobDAO dao = new JobDAO(DBConnect.getConn());
	Job j = dao.getJobById(id);
	if (j == null) {
		response.sendRedirect("userViewListing.jsp");
		return;
	}
	boolean isEnrolled = dao.getEnrollStatus(id, (String) session.getAttribute("email"));
	%>


	<div class="card"
		style="width: 60rem; margin-left: auto; margin-right: auto; margin-top: 1%;">
		<c:if test="${not empty enrollJobMessage}">
			<div class="alert alert-dark" role="alert">${enrollJobMessage}</div>
			<c:remove var="enrollJobMessage" />
		</c:if>
		<div class="card-body">

			<input type="hidden" name="id" value="<%=j.getId()%>"></input> <input
				type="hidden" name="code" value="<%=j.getCode()%>"></input>
			<div class="form-group">
				<label for="exampleInputEmail1">Role</label> <input type="text"
					class="form-control" id="exampleInputEmail1"
					aria-describedby="emailHelp" placeholder="Enter Job Role"
					name="role" value="<%=j.getRole()%>" readonly>
			</div>

			<div class="form-group">
				<label for="exampleInputEmail1">Location</label> <input type="text"
					class="form-control" id="exampleInputEmail1"
					aria-describedby="emailHelp" placeholder="Enter Location"
					name="location" value="<%=j.getLocation()%>" readonly>
			</div>

			<div class="form-group">
				<label for="exampleInputEmail1">Pay</label> <input type="text"
					class="form-control" id="exampleInputEmail1"
					aria-describedby="emailHelp" placeholder="Enter pay" name="pay"
					value="<%=j.getPay()%>" readonly>
			</div>

			<div class="form-group">
				<label for="exampleInputEmail1">Date Of Interview</label> <input
					type="date" class="form-control" id="exampleInputEmail1"
					aria-describedby="emailHelp" name="dateOfInterview"
					value="<%=j.getDateOfInterview()%>" readonly>
			</div>

			<div class="form-group">
				<label for="exampleInputEmail1">Description</label>
				<textarea name="description" class="form-control"
					id="exampleInputEmail1" aria-describedby="emailHelp" readonly><%=j.getDescription()%></textarea>
			</div>

			<div class="form-group">
				<label for="exampleInputEmail1">Job Status</label> <input
					type="text" class="form-control" id="exampleInputEmail1"
					aria-describedby="emailHelp" name="dateOfInterview"
					<%String jobStatus = j.getJobStatus() == 1 ? "Active" : "Closed";%>
					value="<%=jobStatus%>" readonly>
			</div>

			<%
			if (isEnrolled == false) {
			%>
			<form action="enroll" method="post">
				<input type="hidden" value="<%=id%>" name="id"> <input
					type="submit" class="btn btn-primary" value="Enroll"
					style="background-color: black; border: none;">
			</form>
			<%
			} else {
			%>
			<form action="unenroll" method="post">
				<input type="hidden" value="<%=id%>" name="id"> <input
					type="submit" class="btn btn-primary" value="Unenroll"
					style="background-color: black; border: none;">
			</form>
			<%
			}
			%>

		</div>
	</div>
</body>
</html>