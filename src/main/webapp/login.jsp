<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>Log in</title>
	<!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script><!--1.12.4-->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/unloginnedHeader.js"></script>
    <script src="js/login.js"></script>
	
<body>
	<div class="container-fluid">
		<header class = "row">
			<div class = "logo_holder col-md-offset-1 col-md-2">
				<a href = "index.jsp"><img src = "data/images/logo.png" alt = "MyParking"/></a>
			</div>
			<div class="col-md-offset-4 col-md-2">
				<button class="btn btn-secondary btn-block" onclick="toRegister()">Register</button>
			</div>
			<div class = "main_button col-md-2">
				<button class="btn btn-primary btn-block" onclick="toLogIn()">Log in</button>
			</div>
		</header>
		<div hidden>
			<a href="login.jsp" id="toLogin"></a>
			<a href="registration.jsp" id="toRegister"></a>
			<a href="forgot_password.jsp" id="toForgotPassword"></a>
		</div>
		<form id="login" class="col-md-offset-3 col-md-6" action="ParkingServlet" method=post>
			<p class="textCenter textHeader">Log in to Parking System</p>
			<div class="row formRow">
				<p class="col-md-offset-2 col-md-4">Login or email<span class=red>*</span>: </p>
				<input class="col-md-4" type="text" name="loginOrEmail"/>
			</div>
			<div class="row formRow">
				<p class="col-md-offset-2 col-md-4">Password<span class=red>*</span>: </p>
				<input class="col-md-4" type="password" name="password"/>
			</div>
			<p class="textCenter"><span class="red">*</span> fields are neccessary to fill</p>
			<c:if test="${loginMessage != null}">
				<div class="serverMsg">
  					<p><%= session.getAttribute("loginMessage") %></p>
  				</div>
  			</c:if>
			<input type="hidden" name="command" value="LOG_IN">
			<div class="formRow formButtonGroup">
				<input class="btn btn-secondary formButton" type="button" onclick="toForgotPassword()" value="Forgot password?"/>
				<input class="btn btn-primary formButton" type="submit" value="Log in"/>
			</div>
		</form>
	</div>
</body>
</html>