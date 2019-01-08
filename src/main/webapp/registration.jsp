<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>Register in MyParking</title>
	<!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script><!--1.12.4-->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/unloginnedHeader.js"></script>
	
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
		</div>
		<div class="row">
			<form id="register"class="col-md-offset-3 col-md-6" action="ParkingServlet" method=post>
				<p class="textCenter textHeader">Registration form</p>
				<div class="row formRow">
					<p class="col-md-offset-2 col-md-4">Name<span class="red">*</span>:</p>
					<input class="col-md-4" type="text" name="name"/>
				</div>
				<div class="row formRow">
					<p class="col-md-offset-2 col-md-4">Surname<span class="red">*</span>:</p>
					<input class="col-md-4" type="text" name="surname"/>
				</div>
				<div class="row formRow">
					<p class="col-md-offset-2 col-md-4">Google email<span class="red">*</span>:</p>
					<input class="col-md-4" type="text" name="email"/>
				</div>
				<div class="row formRow">
					<p class="col-md-offset-2 col-md-4">Login:</p>
					<input class="col-md-4" type="text" name="login"/>
				</div>
				<div class="row formRow">
					<p class="col-md-offset-2 col-md-4">Password<span class="red">*</span>:</p>
					<input class="col-md-4" type="password" name="password"/>
				</div>
				<div class="row formRow">
					<p class="col-md-offset-2 col-md-4">Repeat password<span class=red>*</span>: </p>
					<input class="col-md-4" type="password" name="repeatPassword"/>
				</div>
				<div class="row formRow">
					<p class="col-md-offset-2 col-md-4"><input type="radio" name="userTypeId" value="2" checked/>
						I am a driver</p>
					<p class="col-md-4"><input type="radio" name="userTypeId" value="1"/> I am an administrator</p>
				</div>
				<p class="textCenter"><span class="red">*</span> fields are neccessary to fill</p>
				<c:if test="${registrationMessage != null}">
				<div class="serverMsg">
  					<p><%= session.getAttribute("registrationMessage") %></p>
  				</div>
  				</c:if>
  				<input type="hidden" name="command" value="REGISTER"/>
  				<input class="btn btn-primary formButton right" type="submit" value="Apply for registration"/>
			</form>
		</div>
	</div>
</body>
</html>