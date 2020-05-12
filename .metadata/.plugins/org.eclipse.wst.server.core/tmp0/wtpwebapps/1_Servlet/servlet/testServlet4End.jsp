<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//스크립틀릿(scriptlet)
	String name = (String) request.getAttribute("name");
	String age = (String) request.getAttribute("age");
	String city = (String) request.getAttribute("city");
	String height = (String) request.getAttribute("height");
	String gender = (String) request.getAttribute("gender");
	String food = (String) request.getAttribute("foods");
	String rec = (String) request.getAttribute("recommendation");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	h2{color: red;}
	span.name{color: orange; font-weight: bold;}
	span.gender{color: yellow; font-weight: bold; background-color: black;}
	span.age{color: green; font-weight: bold;}
	span.city{color: blue; font-weight: bold;}
	span.height{color: navy; font-weight: bold;}
	span.food{color: purple; font-weight: bold;}
	</style>
<title>Insert title here</title>
</head>
<body>
	<h2>개인취향 테스트 결과(POST)</h2>
		<span class='name'><%= name %></span>님은  
		<span class='age'><%= age %></span>이시며
		<span class='city'><%= city %></span>에 사는
		키 <span class='height'><%= height %></span>CM인 
		<span class='gender'><%= gender %></span>입니다.
		좋아하는 음식은 <span class= 'food'><%=food %></span>
		<hr>
		<h3><%=age %>에 맞는 선물 추천</h3>
		<%=rec %>선물은 어떠신가요?
</body>
</html>