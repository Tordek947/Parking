<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ua.hpopov.parking.presentation.commands.Page" %>
<%@ page import="ua.hpopov.parking.presentation.commands.CommandType" %>
<%@ page import="ua.hpopov.parking.presentation.paginationwrappers.PaginationBeanSet" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "/WEB-INF/mytaglib.tld" prefix = "mytag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>New Users</title>
	<!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="css/styles.css" rel="stylesheet">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script><!--1.12.4-->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/common.js"></script>
    <script src="js/pagination.js"></script>
    <script src="js/newUsers.js"></script>
	
</head>
<body onload="onNewUsersLoaded()">
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
					<input type="hidden" name="command" value="<%=CommandType.LOG_OUT.toString() %>"/>
					<input type="submit"/>
			</form>
		</div>
		<div class="row content">
			<div class="col-md-offset-1 col-md-10">
				<c:if test="${resolveNewUserPopupMsg != null}">
					<mytag:popupMsg popupMsgName="resolveNewUserPopupMsg"/>
				</c:if>
				
				<c:choose>
					<c:when test="${newUsersBeanSet != null}">
        				<mytag:pageContent pgBeanSetAttrName="newUsersBeanSet" 
							rowClass="row clkRow clkRowDefault" cellClass="col-6 textCenter"
						/>
						<mytag:walkPager ariaLabel="New users pages"
							prevDisabled='<%=!(Boolean)request.getAttribute("hasPrevPage") %>'
							nextDisabled='<%=!(Boolean)request.getAttribute("hasNextPage") %>'
						/>
    				</c:when>  
    				<c:otherwise>
        				<c:if test="${newUsersMessage != null}">
							<div class="serverMsg">
  								<p><%= request.getAttribute("newUsersMessage") %></p>
  							</div>
  						</c:if>
   					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div hidden>
			<form id="defineNewUser" action="ParkingServlet" method="post" accept-charset=utf-8>
				<input type="hidden" name="command" value=""/>
				<input type="hidden" name="choosenBeanId" value=""/>
				<input type="submit"/>
			</form>
			<form id="newUserPaging" action="ParkingServlet" method="get" accept-charset=utf-8>
				<input type="hidden" name="command" value="<%= CommandType.NEW_USERS %>"/>
				<input type="hidden" name="pageSize" value="10"/>
				<input type="hidden" name="fromIndex" value=""/>
				<input type="hidden" name="forward" value=""/>
				<input type="submit"/>
			</form>
		</div>
		<nav class="navbar navbar-default navbar-fixed-bottom">
  			<div class="container-fluid">
    			<div class="row">
    				<div class="col-md-offset-4 col-md-2">
						<button class="btn btnDecline btn-block disabled" command="<%= CommandType.DECLINE_NEW_USER%>">Decline user</button>
					</div>
					<div class = "main_button col-md-2">
						<button class="btn btn-primary btn-block disabled" command="<%= CommandType.CONFIRM_NEW_USER%>">Confirm user</button>
					</div>
    			</div>
  			</div>
		</nav>
    </div>
</body>
</html>