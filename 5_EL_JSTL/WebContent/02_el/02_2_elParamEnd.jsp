<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>주문 내역</h2>
	상품명 : ${param.pname }<br>
	수량 : ${param.pcount} <br>
	옵션1 : ${paramValues.option[0] }<br>
	옵션2 : ${paramValues.option[1] }<br>
	<!-- vo에서 vo로 넘어갈때는 scope영역에서 받아온게 아니기 때문에 parameter의 param.을 붙여야함 -->
</body>
</html>