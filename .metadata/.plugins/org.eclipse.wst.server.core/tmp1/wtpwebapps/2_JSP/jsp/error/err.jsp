<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error</title>
</head>
<body>
	<h1 style="color: red;">에러다, 에러!</h1>
	<%= exception %><br><!-- 지금 에러나는 이유 -->
	<%= exception.getClass().getName() %> <!-- 에러 이름 -->
	<%= exception.getMessage() %>
</body>
</html>