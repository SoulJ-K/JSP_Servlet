<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	String pizza = (String) request.getAttribute("pizza");
    	String top = (String) request.getAttribute("top");
    	String side = (String) request.getAttribute("side");
    	int price = (int) request.getAttribute("price");
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pizza</title>
<style>
	span#price{}
	.pizza{color: red;}
	.top{color: green;}
	.side{color : blue;}
	h2{color: pink;}
</style>
</head>
<body>
	<h2>주문 내역</h2>
	피자는<span class="pizza"><%=pizza %></span>,
	토핑은<span class="top"><%=top %></span>,
	사이드는<span class="side"><%=side %></span>
	<br><br><br><br>
	총합 : <span id="price"><%=price %></span>
	
	<h2>즐거운 식사시간 되세요~</h2>
</body>
</html>