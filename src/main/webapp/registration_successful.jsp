<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Thank you for registration</title>
</head>
<body>
	<div class="center">
		<p>Thank you for registration</p>
		<p>You will recieve a letter on your email 
			<c:if test="${lastRegisteredEmail != null}">
			<%= session.getAttribute("lastRegisteredEmail") %>
  			</c:if>
  			as soon as our administrator confirms your application
  		</p>
	</div>
	<div>
		<a href="index.jsp">Back to the main page</a>
	</div>
</body>
</html>