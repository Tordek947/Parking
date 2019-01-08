<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ua.hpopov.parking.presentation.commands.Page" %>
<%@ page import="ua.hpopov.parking.presentation.commands.CommandType" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "/WEB-INF/mytaglib.tld" prefix = "mytag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>Administrator</title>
	<!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script><!--1.12.4-->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/loginnedHeader.js"></script>
	
</head>
<body>
<div class="container-fluid">
    	<header class = "row">
			<div class = "logo_holder col-md-offset-1 col-md-2">
				<mytag:refmain><img src = "data/images/logo.png" alt = "MyParking"/></mytag:refmain>
			</div>
			<div class="col-md-offset-4 col-md-2">
				<button class="btn btn-secondary btn-block">Register</button>
			</div>
			<div class = "main_button col-md-2">
			<c:choose>
    			<c:when test="${loginnedUserBean != null}">
					<button class="btn btn-primary btn-block" onclick="logOut()">Log out</button>
    			</c:when>    
    			<c:otherwise>
        			<jsp:forward page="<%=Page.LOG_IN.getPath() %>"></jsp:forward>
   				</c:otherwise>
			</c:choose>
			</div>
		</header>
		<div hidden>
			<form id="logOut" action="ParkingServlet" method=post accept-charset=utf-8>
					<input type="hidden" name="command" value="<%=CommandType.LOG_OUT.toString() %>">
					<input type="submit"/>
			</form>
		</div>
    </div>
</body>
</html>