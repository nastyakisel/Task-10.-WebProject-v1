<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<fmt:setBundle basename="com.finalproject.onlineapteka.messages.msgs" var="msgs" />

<head>
	<meta charset="utf-8">

	<link rel="stylesheet" href="css/style.css" type="text/css">

	<title>
		<fmt:message key="application.title" bundle="${msgs}" />
	</title>
</head>

<body>
	<div class="registration">
		
		<a href = "registrationPage.html"><fmt:message key="startPage.registration" bundle="${msgs}" /></a>
	</div>
	
	<div class="login">
		
		<a href = "loginPage.html"><fmt:message key="startPage.login" bundle="${msgs}" /></a>
		
	</div>
</body>
</html>