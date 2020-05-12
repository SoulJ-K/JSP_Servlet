<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>JSP표준액션 태그 중 useBean을 사용하여 VO클래스의 객체 정보 불러와보기</h2>
	<jsp:useBean id="person1" class="action.model.vo.Person" scope="request"/>
	<!-- 무슨 객체를 만들려는지 알지 못함. 그래서 class를 통해 위치를 정해서 어디에 있는 객체를 사용하려는지 표시.
	 scope: 해당 스코프 안에서 속성값이 해당 클래스 타입으로 존재하면 그 객체를 가지고 오고 없으면 새로 생성-->		
	
	이름 : <jsp:getProperty property="name" name="person1"/><br>
	성별 : <jsp:getProperty property="gender" name="person1"/><br>
	<%-- 나이 : <jsp:getProperty property="age" name="person1"/><br> --%>
	나이 : <jsp:getProperty property="nai" name="person1"/><br>
	<!-- Person의 get메소드의 이름을 찾아서 가져옴 -->
	
	<hr>
	
	<h2>JSP표준액션 태그 중 useBean을 사용하여 VO클래스에 데이터 초기화 하기</h2>
	<jsp:useBean id="person2" class="action.model.vo.Person" scope="request"/>
	
	<jsp:setProperty property="name" name="person2" value="박신우"/>
	<jsp:setProperty property="gender" name="person2" value="F"/>
	<jsp:setProperty property="nai" name="person2" value="20"/>
	
	이름 :  <jsp:getProperty property="name" name="person2"/> <br>
	성별 :  <jsp:getProperty property="gender" name="person2"/><br>
	나이 :  <jsp:getProperty property="nai" name="person2"/><br>
</body>
</html>