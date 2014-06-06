<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Welcome</title>
<!--[if lt IE 9]>
	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
</head>
<body>
	<h1>SAMPLE INTEGRATION APP</h1>
	<p>Welcome ${ad_full_name}</p>
	<h4>Users:</h4>
	<c:if test="${not empty userList}">
		<ul>
			<c:forEach var="user" items="${userList}">
				<li>${user.fullName}</li>
			</c:forEach>
		</ul>
	</c:if>
</body>
</html>