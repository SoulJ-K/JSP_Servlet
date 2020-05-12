<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" errorPage="error/err.jsp" %>
 <%-- <%@ page import="java.util.ArrayList" %> --%>
<%--
	page지시어 : 현재 JSP페이지를 컨테이너 에서  처리하는데 필요한 각종 속성을 기술하는 부분
	 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
		//자바코드 넣는 곳
		//<% % > : 스크립틀릿(자바코드 작성)
		
		int total = 0;
		for(int i = 1; i <= 10; i++){
			total += 1;
			
	%>
		이렇게도 쓸 수 있다.<br>
	<%
		}
		
		System.out.println(total);
		
		ArrayList<String> list = new ArrayList<String>();
		list.add(0, null);
		
		System.out.println(list.get(0).charAt(0));
	%>
	<br>
	expression 출력 : 1부터 10까지의 합은 <%= total %>입니다.<br>
	
	scriptlet 출력 : 1부터 10까지의 합은 <% out.println(total); %>입니다. <br>
	
	<%-- <%= exception %>  에러를 처리하는 페이지가 아니기때문에 사용불가능--%>
</body>
</html>