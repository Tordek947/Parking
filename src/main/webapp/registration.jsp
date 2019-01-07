<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register in MyParking</title>
</head>
<body>
	<div>
		<form action="ParkingServlet" method=post>
			<span>Registration form</span>
			<div class="row">
				<p>Name<span class="red">*</span>:</p>
				<input type="text" name="name"/>
			</div>
			<div class="row">
				<p>Surname<span class="red">*</span>:</p>
				<input type="text" name="surname"/>
			</div>
			<div class="row">
				<p>Google email<span class="red">*</span>:</p>
				<input type="text" name="email"/>
			</div>
			<div class="row">
				<p>Login:</p>
				<input type="text" name="login"/>
			</div>
			<div class="row">
				<p>Password<span class="red">*</span>:</p>
				<input type="password" name="password"/>
			</div>
			<div class="row">
				<p><input type="radio" name="userTypeId" value="2" checked/>I am a driver</p>
				<p><input type="radio" name="userTypeId" value="1"/>I am an administrator</p>
			</div>
			<p><span class="red">*</span> marks are neccessary to fill</p>
			<c:if test="${registrationMessage != null}">
			<div class="serverMsg">
  				<p><%= session.getAttribute("registrationMessage") %></p>
  			</div>
  			</c:if>
  			<input type="hidden" name="command" value="REGISTER"/>
  			<input type="submit" value="Apply for registration"/>
		</form>
	</div>
</body>
</html>