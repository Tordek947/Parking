<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Log in</title>
</head>
<body>
	<div>
		<form action="ParkingServlet" method=post>
			<span>Log in to Parking System</span>
			<div>
				<span>Login</span><span class=red>*</span><span>: </span>
				<input type="text" name="login"/>
			</div>
			<div>
				<span>Password</span><span class=red>*</span><span>: </span>
				<input type="password" name="password"/>
			</div>
			<div>
				<span class=red>Fields with * mark are required</span>
			</div>
			<input type="hidden" name="command" value="LOG_IN">
			<input type="submit" value="Log in"/>
		</form>
		<jsp:useBean id="msg" scope="request" class="ua.hpopov.parking.presentation.Message"/>  
  
		<p><jsp:getProperty name="msg" property="message"/></p>
		<div>
			<form action="ParkingServlet" method=post>
				<input type="hidden" name="command" value="FORGOT_PASSWORD">
				<input type="submit" value="Forgot password?"/>
			</form>
		</div>
	</div>
</body>
</html>