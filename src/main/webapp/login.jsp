<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Log in</title>
</head>
<body>
	<div>
		<form action="ParkingServlet" method=post>
			<p>Log in to Parking System</p>
			<div>
				<p>Login or email<span class=red>*</span>: </p>
				<input type="text" name="loginOrEmail"/>
			</div>
			<div>
				<p>Password<span class=red>*</span>: </p>
				<input type="password" name="password"/>
			</div>
			<div>
				<span class=red>Fields with * mark are required</span>
			</div>
			<input type="hidden" name="command" value="LOG_IN">
			<input type="submit" value="Log in"/>
		</form>
		<c:if test="${loginMessage != null}">
			<div class="serverMsg">
  				<p><%= session.getAttribute("loginMessage") %></p>
  			</div>
  			</c:if>
		<div>
			<form action="ParkingServlet" method=post>
				<input type="hidden" name="command" value="FORGOT_PASSWORD"/>
				<input type="submit" value="Forgot password?"/>
			</form>
		</div>
	</div>
</body>
</html>