<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02_2_ifNumEnd</title>
</head>
<body>
	<%-- <c:if test="${ param.num1 > param.num2}">
		${param.num1 } 크다, ${param.num2 } 보다
		</c:if>
		<c:if test="${param.num2 > param.num1 }"> 
			${param.num2 } 크다, ${param.num1 } 보다
		</c:if>
	 --%>
	 <!-- parameter로 받아오면 string으로 받아오게되므로 형변환 필요 -->
	<br><br>
	
	<c:if test="${ Integer.parseInt(param.num1) > Integer.parseInt(param.num2)}">
		${param.num1 } 크다, ${param.num2 } 보다
	</c:if>
	<c:if test="${Integer.parseInt(param.num2) > Integer.parseInt(param.num1) }"> 
		${param.num2 } 크다, ${param.num1 } 보다
	</c:if>
</body>
</html>