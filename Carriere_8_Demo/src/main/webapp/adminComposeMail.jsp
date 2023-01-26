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
	<%@include file="adminNavbar.jsp"%>
	<%
	response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
	response.setHeader("Expires", "0");

	if (session.getAttribute("isAdmin") == null || request.getParameter("id") == null || request.getParameter("id") == "")
		response.sendRedirect("adminIndex.jsp");

	int id = Integer.parseInt(request.getParameter("id"));
	%>

	<div class="card"
		style="width: 60rem; margin-left: auto; margin-right: auto; margin-top: 1%;">

		<div class="card-body">
			<c:if test="${not empty composeMailMessage}">
				<div class="alert alert-dark" role="alert">${composeMailMessage}</div>
				<c:remove var="composeMailMessage" />
			</c:if>
			<form action="composeMail" method="post">

				<div class="form-group">
					<label for="exampleInputEmail1">Subject</label> <input type="text"
						class="form-control" id="exampleInputEmail1"
						aria-describedby="emailHelp" placeholder="Enter subject"
						name="subject">
				</div>

				<input type="hidden" value="<%= id %>" name="id"></input>
				
				<div class="form-group">
					<label for="exampleInputEmail1">Body</label>
					<textarea rows="15" cols="60" name="body" class="form-control"
						id="exampleInputEmail1" aria-describedby="emailHelp"></textarea>
				</div>

				<div class="form-group" style="padding-top: 10px;">
					<input type="submit" class="btn btn-primary" value="Mail"
						style="background-color: black; border: none;">
				</div>
			</form>
		</div>
	</div>
</body>
</html>