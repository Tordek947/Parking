<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ua.hpopov.parking.beans.UserBean" %>
<%@ page import="ua.hpopov.parking.beans.UserTypeBean" %>
<%@ page import="ua.hpopov.parking.presentation.commands.Page" %>
<%@ page import="ua.hpopov.parking.presentation.commands.CommandType" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "/WEB-INF/mytaglib.tld" prefix = "mytag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>Parking view</title>
	<!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="css/styles.css" rel="stylesheet">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script><!--1.12.4-->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/common.js"></script>
	
</head>
<body>
<c:if test="${loginnedUserBean == null}">
	<jsp:forward page="<%=Page.LOG_IN.getPath() %>"></jsp:forward>
</c:if>
<div class="container-fluid">
    	<header class = "row">
			<div class = "logo_holder col-md-offset-1 col-md-2">
				<mytag:refmain><img src = "data/images/logo.png" alt = "MyParking"/></mytag:refmain>
			</div>
			<div class="dropdown col-md-offset-3 col-md-1">
  				<button class="btn btn-default events dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
    				<i class="fa fa-bell" aria-hidden="true"></i>
    				<!-- <span class="caret"></span>-->
  				</button>
  				<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
    				<mytag:events count="10"/>
  				</ul>
			</div>
			<p class="col-md-2 textCenter">
				<mytag:greeting/>
			</p>
			<div class = "main_button col-md-2">
				<button class="btn btn-primary btn-block" onclick="forwardByForm('logOut')">Log out</button>
			</div>
		</header>
		<div hidden>
			<form id="logOut" action="ParkingServlet" method=post accept-charset=utf-8>
					<input type="hidden" name="command" value="<%=CommandType.LOG_OUT.toString() %>">
					<input type="submit"/>
			</form>
		</div>
		<div class = "page_view col-md-12">
			<div class = "row">
				<ul class="nav nav-tabs col-md-offset-1 col-md-3" role="tablist">
					<li role="presentation" class="active"><a href="#drivers" aria-controls="drivers" role="tab" data-toggle="tab">Drivers</a></li>
					<li role="presentation"><a href="#buses" aria-controls="buses" role="tab" data-toggle="tab">Buses</a></li>
					<li role="presentation"><a href="#routes" aria-controls="routes" role="tab" data-toggle="tab">Routes</a></li>
					<li role="presentation"><a href="#routeVertices" aria-controls="routeVertices" role="tab" data-toggle="tab">Route vertices</a></li>
				</ul>
				
				<p class="col-md-3">
					<c:if test="${parkingViewAdminMessage != null}">
						<%= request.getAttribute("parkingViewAdminMessage") %>
  					</c:if>
  				</p>
			</div>
			<div class="tab-content row">
				<div role = "tabpanel" class = "tab-pane col-md-offset-1 col-md-10" id = "drivers">
				</div>
				<div role = "tabpanel" class = "tab-pane active col-md-offset-1 col-md-10" id = "buses">
				</div>
				<div role = "tabpanel" class= "col-md-offset-1 col-md-10" id = "routes">
				</div>
				<div role = "tabpanel" class= "col-md-offset-1 col-md-10" id = "routeVertices">
				</div>
			</div>
		</div>
    </div>
</body>
</html>