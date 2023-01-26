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

	if (session.getAttribute("isAdmin") == null || request.getParameter("id") == null || request.getParameter("id") == "")
		response.sendRedirect("adminIndex.jsp");
	%>

	<c:if test="${not empty msg}">
		<div class="alert alert-success" role="alert">${ msg}</div>
		<c:remove var="msg" />
	</c:if>
	<%
	int id = Integer.parseInt(request.getParameter("id"));
	JobDAO dao = new JobDAO(DBConnect.getConn());
	Job j = dao.getJobById(id);
	if (j == null) {
		System.out.println("j is " + j);
		response.sendRedirect("adminIndex.jsp");
		return;
	}
	%>


	<div class="card"
		style="width: 60rem; margin-left: auto; margin-right: auto; margin-top: 1%;">

		<div class="card-body">
			<c:if test="${not empty editJobMessage}">
				<div class="alert alert-dark" role="alert">${editJobMessage}</div>
				<c:remove var="editJobMessage" />
			</c:if>
			<form action="editJob" method="post">

				<input type="hidden" name="id" value="<%=j.getId()%>"></input> <input
					type="hidden" name="code" value="<%=j.getCode()%>"></input>
				<div class="form-group">
					<label for="exampleInputEmail1">Role</label> <input type="text"
						class="form-control" id="exampleInputEmail1"
						aria-describedby="emailHelp" placeholder="Enter Job Role"
						name="role" value="<%=j.getRole()%>">
				</div>

				<div class="form-group">
					<label for="exampleInputEmail1">Location</label> <input type="text"
						class="form-control" id="exampleInputEmail1"
						aria-describedby="emailHelp" placeholder="Enter Location"
						name="location" value="<%=j.getLocation()%>">
				</div>

				<div class="form-group">
					<label for="exampleInputEmail1">Pay</label> <input type="text"
						class="form-control" id="exampleInputEmail1"
						aria-describedby="emailHelp" placeholder="Enter pay" name="pay"
						value="<%=j.getPay()%>">
				</div>

				<div class="form-group">
					<label for="exampleInputEmail1">Date Of Interview</label> <input
						type="date" class="form-control" id="exampleInputEmail1"
						aria-describedby="emailHelp" name="dateOfInterview"
						value="<%=j.getDateOfInterview()%>">
				</div>

				<div class="form-group">
					<label for="exampleInputEmail1">Description</label>
					<textarea rows="15" cols="60" name="description"
						class="form-control" id="exampleInputEmail1"
						aria-describedby="emailHelp"><%=j.getDescription()%></textarea>
				</div>

				<div class="form-group">
					<label class="mr-sm-2" for="inlineFormCustomSelect">Job
						Status</label> <select class="custom-select mr-sm-2"
						id="inlineFormCustomSelect" name="jobStatus">
						<option value="Active">Active</option>
						<option value="Closed">Closed</option>
					</select>
				</div>

				<div class="form-check">
					<input type="checkbox" class="form-check-input" id="exampleCheck1"
						name="notificationStatus" value="1" checked> <label
						class="form-check-label" for="exampleCheck1">Push job
						notification to users.</label>
				</div>

				<div class="form-group" style="padding-top: 10px;">
					<input type="submit" class="btn btn-primary" value="Update Job"
						style="background-color: black; border: none;"> <a
						href="delete?id=<%=j.getId()%>" class="btn btn-light"
						style="background-color: black; color: white; border: none;">Delete
						Job</a>
					<button class="btn btn-primary"
						style="background-color: black; color: white; border: none;">
						<a href="adminViewListing.jsp" style="color: white">Cancel</a>
					</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>