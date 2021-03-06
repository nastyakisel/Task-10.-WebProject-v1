<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.requestLocale}"/>
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
		<form method="post" action="controller.html" >
		
			<c:if test="${has_errors != null}">
				<c:forEach items="${has_errors}" var="error">
					<c:if test="${error.errorMessage == 'loginPage.errorUser'}">
						<span class="errorText">
							<fmt:message key="loginPage.errorUser" bundle="${msgs}" />
						</span>
					</c:if>
				</c:forEach>
			</c:if>
			<br />

			<input type="hidden" name="action" value="login" />

			<fmt:message key="loginPage.userName.label" bundle="${msgs}" />
			<br />
			<input type="text" id="user_login" name="user_login" value="<c:out value='${current_user.userName}' default=''  />" />

			<br />
			<br />

			<fmt:message key="loginPage.password.label" bundle="${msgs}" />
			<br />
			<input type="text" id="user_password" name="user_password" />
				
				<c:if test="${has_errors != null}">
				<c:forEach items="${has_errors}" var="error">
					<c:if test="${error.errorMessage == 'loginPage.errorPassword'}">
						<span class="errorText">
							<fmt:message key="loginPage.errorPassword" bundle="${msgs}" />
						</span>
					</c:if>
				</c:forEach>
			</c:if>
			
			<br />
			<br />
			<input type="submit" name="login_but" value="<fmt:message key='loginPage.button' bundle='${msgs}' />" />
		</form>
		
	</div>
	
	
</body>
</html>