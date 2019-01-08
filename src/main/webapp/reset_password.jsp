<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>Reset password</title>
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
		<form id="login" class="col-md-offset-3 col-md-6" action="ParkingServlet" method=post>
			<p class="textCenter textHeader">Setting a new password</p>
			<c:choose>
    				<c:when test="${resetPasswordHeaderMessage != null}">
        				<p class="textCenter"><%= session.getAttribute("resetPasswordHeaderMessage") %></p>
    				</c:when>    
    				<c:otherwise>
        				<jsp:forward page="/error.jsp"></jsp:forward>
   					</c:otherwise>
			</c:choose>
			<div class="row formRow">
				<p class="col-md-offset-2 col-md-4">Your email: </p>
				<c:choose>
    				<c:when test="${email != null}">
        				<p class="col-md-4"><%= session.getAttribute("email") %></p>
    				</c:when>    
    				<c:otherwise>
        				<jsp:forward page="/error.jsp"></jsp:forward>
   					</c:otherwise>
				</c:choose>
			</div>
			<div class="row formRow">
				<p class="col-md-offset-2 col-md-4">Verification code<span class=red>*</span>: </p>
				<input class="col-md-4" type="text" name="verificationCode"/>
			</div>
			<div class="row formRow">
				<p class="col-md-offset-2 col-md-4">New password<span class=red>*</span>: </p>
				<input class="col-md-4" type="password" name="password"/>
			</div>
			<div class="row formRow">
				<p class="col-md-offset-2 col-md-4">Repeat password<span class=red>*</span>: </p>
				<input class="col-md-4" type="password" name="repeatPassword"/>
			</div>
			<p class="textCenter"><span class="red">*</span> fields are neccessary to fill</p>
			<c:if test="${resetPasswordMessage != null}">
				<div class="serverMsg">
  					<p><%= session.getAttribute("resetPasswordMessage") %></p>
  				</div>
  			</c:if>
			<input type="hidden" name="command" value="RESET_PASSWORD">
			<input class="btn btn-primary formButton right" type="submit" value="Reset password"/>
		</form>
	</div>
</body>
</html>