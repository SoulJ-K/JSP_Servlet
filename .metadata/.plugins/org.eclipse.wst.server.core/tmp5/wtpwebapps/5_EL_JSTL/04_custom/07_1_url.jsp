<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="07_2_urlEnd.jsp?pname=노트북&pcount=1&option=램추가&option=하드추가">
		07_2_urlEnd.jsp(직접 명시)
	</a>
	
	<hr>
	
	<c:url value="07_2_urlEnd.jsp" var="u">
		<c:param name="pname" value="노트북"></c:param>
		<c:param name="pcount" value="1"></c:param>
		<c:param name="option" value="램추가"></c:param>
		<c:param name="option" value="하드추가"></c:param>
	</c:url>
	<a href="${ u }">
		07_2_urlEnd.jsp(jstl)
	</a>
</body>
</html>