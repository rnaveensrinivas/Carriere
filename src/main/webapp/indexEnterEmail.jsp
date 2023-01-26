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
	<div class="card"
		style="width: 30rem; margin-left: auto; margin-right: auto; margin-top: 3%;">

		<div class="card-body">

			<%
			response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
			response.setHeader("Expires", "0");

			if (session.getAttribute("isAdmin") != null)
				response.sendRedirect("adminIndex.jsp");
			if (session.getAttribute("isUser") != null)
				response.sendRedirect("userIndex.jsp");
			%>

			<c:if test="${not empty indexEmailMessage}">
				<div class="alert alert-dark" role="alert">${indexEmailMessage}</div>
				<c:remove var="indexEmailMessage" />
			</c:if>

			<form action="enterEmail" method="post">

				<div class="form-group">
					<label for="exampleInputEmail1">Email address</label> <input
						type="email" class="form-control" id="exampleInputEmail1"
						aria-describedby="emailHelp" placeholder="Enter email"
						name="email">
				</div>

				<div class="form-group">
					<input type="submit" class="btn btn-primary btn-custom"
						value="Request Password Change" style="background-color: black; border: none;">
				</div>

			</form>
		</div>
	</div>
</body>
</html>