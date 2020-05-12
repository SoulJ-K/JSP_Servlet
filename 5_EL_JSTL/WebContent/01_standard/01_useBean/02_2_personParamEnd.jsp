<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>JSP표준 액션 태그 중 useBean의 param 속성 이용하기</h2>
	<jsp:useBean id="person" class="action.model.vo.Person">
		<%-- <jsp:setProperty property="name" name="person" param="name"/>
		<jsp:setProperty property="gender" name="person" param="gender"/>
		<jsp:setProperty property="nai" name="person" param="nai"/> --%>
		
<%-- 		<jsp:setProperty property="name" name="person"/>
		<jsp:setProperty property="gender" name="person" />
		<jsp:setProperty property="nai" name="person" />
 --%>		<!-- 프로퍼티와 파람의 이름이 같으면 파람 생략 가능  -->
	
		<jsp:setProperty property="*" name="person"/>
		<!-- 전체 다 출력할테니 알아서 가져와~  : *를 와일드카드라 명명-->
	</jsp:useBean>
	
	<p>정보 출력</p>
	이름 : <jsp:getProperty property="name" name="person"/><br>
	성별 : <jsp:getProperty property="gender" name="person"/><br>
	나이 : <jsp:getProperty property="nai" name="person"/><br>

	
	
</body>
</html>