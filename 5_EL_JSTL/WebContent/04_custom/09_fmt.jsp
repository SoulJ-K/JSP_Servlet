<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fmt</title>
</head>
<body>
	<h2>jstl fmt</h2>
	<p>날짜, 시간, 숫자 데이터의 출력 형식을 지정할 때 사용</p>
	
	<h3>숫자 데이터 포맷 지정 : formatNumber태그</h3>
	천단위 구분 기호 적용 : <fmt:formatNumber value="123456789"></fmt:formatNumber><br>
	<fmt:formatNumber value="123456789" groupingUsed="true"></fmt:formatNumber><br>
	<fmt:formatNumber value="123456789" groupingUsed="false"></fmt:formatNumber><br>
	
	<br>
	
	<b>실수 값 소수점 아래 자리수 지정 : pattern속성 사용</b><br>
	#을 사용한 경우 : <fmt:formatNumber value="1.234567" /><br><!-- 소수점 네번째 자리에서 반올림 -->
	#을 사용한 경우 : <fmt:formatNumber value="1.239567" pattern="#.##"/><br> <!-- pattern에서 정한 소수점 2번째 자리에서 반올림 -->
	#을 사용한 경우 : <fmt:formatNumber value="1.2" pattern="#.##"/><br> <!-- 비어있는곳은 채워지지 않음 -->
	0을 사용한 경우 : <fmt:formatNumber value="1.2" pattern="#.00"/><br> <!-- 빈 자리는 0을 채워넣음 -->
	
	<h3>type속성으로 백분율, 통화기호처리</h3>
	<fmt:formatNumber value="0.12" type="percent"/><br>
	<fmt:formatNumber value="123456789" type="currency"/><br>
	<fmt:formatNumber value="123456789" type="currency" currencySymbol="$"/><br>
	
	
</body>
</html>