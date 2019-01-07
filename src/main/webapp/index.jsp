<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MyParking</title>
</head>
<body>
<div class="greeting">
	<h2 class="center">Welcome to MyParking system!</h2>
	<form action="ParkingServlet" method=post accept-charset=utf-8>
		<input type="hidden" name="command" value="FORWARD_TO_LOGIN">
		<input type="submit" value="Log In"/>
	</form>
</div>
<div class="additional">
	<p class="center">You are not a member?</p>
	<form action="ParkingServlet" method=post accept-charset=utf-8>
		<input type="hidden" name="command" value="FORWARD_TO_REGISTRATION">
		<input type="submit" value="Register"/>
	</form>
</div>
</body>
</html>