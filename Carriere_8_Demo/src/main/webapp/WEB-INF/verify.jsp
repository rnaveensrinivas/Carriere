<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body
	onload="document.createElement('form').submit.call(document.getElementById('myForm'))">
	<form id="myForm" name="myForm"
		action="" method="POST">
		<input type=hidden name="val1" id="val1" value="value1" /> <input
			type=hidden name="val2" id="val2" value="value2" /> <input
			type=hidden name="val3" id="val3" value="value3" /> <input
			type=hidden name="submit" id="submit" value="Continue" />
	</form>
</body>
</html>