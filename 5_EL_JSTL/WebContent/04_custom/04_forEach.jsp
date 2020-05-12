<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>forEach</title>
</head>
<body>
<%
	for(int i = 1; i <= 6; i++){
%>

	<h<%= i%>>반복문<%= i %></h<%= i %>>
<%
	}
%>

	<c:forEach var="i" begin="1" end="6">
		<h${i }>반복문${i }</h${i }>
	</c:forEach>
	
	<hr>
	
	<c:forEach var="i" begin="1" end="6" step="2">
		<h${i }>건너뛰기${i }</h${i }>
	</c:forEach>
	
	<!-- step은 0보다 크거나 같아야한다 -->
	
	<%-- <c:set var="num" value="7"></c:set>
	<c:forEach var="i" begin="1" end="${num }" step="1">
		<h${num-i }>거꾸로출력${num-i }</h${num-i}>
	</c:forEach> --%>
	
	<c:forEach var="i" begin="1" end="6" >
		<h${7-i }>거꾸로출력${7-i }</h${7-i}>
	</c:forEach>
	

</body>
</html>