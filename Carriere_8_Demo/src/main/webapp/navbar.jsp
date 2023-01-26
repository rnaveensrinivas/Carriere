<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<nav class="navbar navbar-expand-lg navbar-light bg-light"
	style="border-bottom: 1px solid #c0c2c0">
	<a class="navbar-brand" href="#">CARRIÈRE</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<!-- <li class="nav-item active"><a class="nav-link" href="#">Home
					<span class="sr-only">(current)</span>
			</a></li> -->
			<%-- <c:if test="${userobj.role eq 'admin' }">
				<li class="nav-item"><a class="nav-link" href="post_job.jsp">Post
						Job</a></li>
				<li class="nav-item"><a class="nav-link"
					href="view_listing.jsp">View Listings</a></li>
			</c:if>
			<c:if test="${userobj.role eq 'user' }">
				<li class="nav-item"><a class="nav-link" href="search_job.jsp">
						Search Job</a></li>
			</c:if> --%>


			<!-- <li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> Dropdown </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="#">Action</a> <a
						class="dropdown-item" href="#">Another action</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="#">Something else here</a>
				</div></li>
			<li class="nav-item"><a class="nav-link disabled" href="#">Disabled</a>
			</li> -->


		</ul>
		<form class="form-inline my-2 my-lg-0" action="logout">
			<c:if test="${not empty userobj }">
				<a href="logout" class="btn btn-light btn-custom">Logout</a>
			</c:if>
		</form>
		<form class="form-inline my-2 my-lg-0">
			<c:if test="${empty userobj }">
				<a href="login.jsp" class="btn btn-light btn-custom">Login</a>
				<a href="Signup.jsp" class="btn btn-light btn-custom">Sign Up</a>
			</c:if>
		</form>
	</div>
</nav>