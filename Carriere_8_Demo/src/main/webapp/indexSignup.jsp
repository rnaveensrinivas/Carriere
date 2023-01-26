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
	<%@include file="indexNavbar.jsp"%>
	<%
	response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
	response.setHeader("Expires", "0");

	if (session.getAttribute("isAdmin") != null )
		response.sendRedirect("adminIndex.jsp");
	if (session.getAttribute("isUser") != null )
		response.sendRedirect("userIndex.jsp");
	%>
	<div class="card"
		style="width: 30rem; margin-left: auto; margin-right: auto; margin-top: 3%;">

		<div class="card-body">
			<c:if test="${not empty signupMessage}">
				<div class="alert alert-dark" role="alert">${signupMessage}</div>
				<c:remove var="signupMessage" />
			</c:if>
			<form action="signup" method="post">

				<div class="form-group">
					<label for="exampleInputEmail1">Full Name</label> <input
						type="text" class="form-control" id="exampleInputEmail1"
						aria-describedby="emailHelp" placeholder="Enter Full Name"
						name="fullname">
				</div>

				<div class="form-group">
					<label for="exampleInputEmail1">Email address</label> <input
						type="email" class="form-control" id="exampleInputEmail1"
						aria-describedby="emailHelp" placeholder="Enter email"
						name="email"> <small id="emailHelp"
						class="form-text text-muted">We'll never share your email
						with anyone else.</small>
				</div>

				<div class="form-group">
					<label for="exampleInputPassword1">Password</label> <input
						type="password" class="form-control" id="exampleInputPassword1"
						placeholder="Password" name="password">
				</div>

				<div class="form-group">
					<label for="exampleInputEmail1">Experience in years</label> <input
						type="number" class="form-control" id="exampleInputEmail1"
						aria-describedby="emailHelp" placeholder="Experience(In years)"
						name="experience" min="0" max="40">
				</div>

				<div class="form-check">
					<input type="checkbox" class="form-check-input" id="exampleCheck1"
						name="notificationStatus" value="1"> <label
						class="form-check-label" for="exampleCheck1">Notify me
						when new job is posted</label>
				</div>

				<div class="form-group" style="padding-top: 10px;">
					<input type="submit" class="btn btn-primary" value="Signup"
						style="background-color: black; border: none;">
				</div>
			</form>
		</div>
	</div>

</body>
</html>