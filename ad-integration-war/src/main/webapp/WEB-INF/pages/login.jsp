<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Login</title>
<!--[if lt IE 9]>
	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
</head>
<body>
	<p>${errorMsg}</p>
	<c:url var="openIDLoginUrl" value="/auth/form/login" />
	<form action="${openIDLoginUrl}" method="post">
		<label for="openid_identifier">OpenID Login</label>: <input
			id="openId" name="openId" type="text" /> <input
			type="submit" value="Login" />
	</form>
</body>
</html>