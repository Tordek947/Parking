<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "/WEB-INF/mytaglib.tld" prefix = "mytag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>Page not found</title>
	<!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script><!--1.12.4-->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/common.js"></script>
    <script src="js/registrationSuccessful.js"></script>
<body>
	<div class="container-fluid">
		<header class = "row">
			<div class = "logo_holder col-md-offset-1 col-md-2">
				<mytag:refmain><img src = "data/images/logo.png" alt = "MyParking"/></mytag:refmain>
			</div>
			<!-- <div class="col-md-offset-4 col-md-2">
				<button class="btn btn-secondary btn-block" onclick="toRegister()">Register</button>
			</div>
			<div class = "main_button col-md-2">
				<button class="btn btn-primary btn-block" onclick="toLogIn()">Log in</button>
			</div>  -->
		</header>
		<!-- <div hidden>
			<a href="login.jsp" id="toLogin"></a>
			<a href="registration.jsp" id="toRegister"></a>
		</div>  -->
		<div class="row">
			<div class="contentHolder col-md-8 col-md-offset-2">
				<p class="textCenter textHeader">Ooops! Error 404</p>
				<p class="textCenter textMessage">
					Requested page was not found. You can start from the main page.
  				</p>
  			</div>
		</div>
		<div class="row">
			<button class="btn btn-primary btn-lg col-md-offset-4 col-md-4" onclick="followHref('mainPage')">
				Back to the main page
			</button>
		</div>
	</div>
</body>
</html>