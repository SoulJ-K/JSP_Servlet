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
	<%
		String str1 = request.getParameter("str1");
		String str2 = request.getParameter("str2");
		
	%>
	스크립트릿 : <%= str1 == str2 %> : 주소값 비교
	<%= (str1 == str2) ? "두 문자열은 같습니다":"두 문자열은 같지 않습니다" %>
	
	<hr>
	
	<c:if test="${param.str1.equals(param.str2) }">
		${param.str1 } 같다, ${param.str2 }
	</c:if>
	<c:if test="${!param.str1.equals(param.str2) }">
		${param.str1 } 같지 않다, ${param.str2 }
	</c:if>
	
	<br>
	
	<c:if test="${param.str1 == param.str2 }">
		${param.str1 } 같다, ${param.str2 }
	</c:if>
	<c:if test="${param.str1 != param.str2 }">
		${param.str1 } 같지 않다, ${param.str2 }
	</c:if>
	
	<br>
	
	<c:if test="${param.str1 eq param.str2 }">
		${param.str1 } 같다, ${param.str2 }
	</c:if>
	<c:if test="${param.str1 ne param.str2 }">
		${param.str1 } 같지 않다, ${param.str2 }
	</c:if>
</body>
</html>