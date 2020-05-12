<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="action.model.vo.Person" %>
<%
	Person person = (Person)request.getAttribute("person");
	String beverage = (String)request.getAttribute("beverage");
	int year = (int)request.getAttribute("year");
	String products[] = (String[])request.getAttribute("products");
	String book = (String)request.getAttribute("book");
	String movie = (String)request.getAttribute("movie");
%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.item{font-weight: bold; color: yellowgreen;}
</style>
</head>
<body>
	<h2>scriptlet을 통해 request객체에 저장된 데이터 출력하기</h2>
 	<h4>개인 정보(<%=year %>)</h4>
	이름 : <%= person.getName()%><br>
	성별 : <%= person.getGender() %><br>
	나이 : <%= person.getNai() %><br>
	<h4>취향 정보</h4>
	<%= person.getName() %>님이 가장 좋아하는 음료 : <%= beverage%><br> 
	<%= person.getName() %>님이 가장 좋아하는 물건 : <%= products[0]%>, <%=products[1]%>, <%=products[2]%><br> 
	<%= person.getName() %>님이 가장 좋아하는 도서 : <%= book%><br> 
	<%= person.getName() %>님이 가장 좋아하는 영화 : <%= movie%><br>  
	<!-- scriptlet 경우 해당 영역에 값이 없으면 null로 뜸  -->
	
	<hr>
	
	<h2>el의 내장객체 XXXScope를 통해 저장된 데이터 출력하기</h2>
	 <h4>개인 정보(${ requestScope.year })</h4>
	이름 : ${ requestScope.person.name }<br>
	성별 : ${ requestScope.person.gender }<br>
	나이 : ${ requestScope.person.nai } <br>
	<h4>취향 정보</h4>
	${ requestScope.person.name }님이 가장 좋아하는 음료 : ${ requestScope.beverage }<br> 
	${ requestScope.person.name }님이 가장 좋아하는 물건 : ${ requestScope.products[0] }, 
													${ requestScope.products[1] },
													${ requestScope.products[2] }<br> 
	${ requestScope.person.name }님이 가장 좋아하는 도서 : ${ requestScope.book }<br> 
	${ requestScope.person.name }님이 가장 좋아하는 영화 : ${ requestScope.movie }<br>
	<!-- el은 영역이 맞지 않을 때 아무것도 뜨지 않음 -->
	${ requestScope.person.name }님이 가장 좋아하는 도서 : ${ sessionScope.book }<br> 
	${ requestScope.person.name }님이 가장 좋아하는 영화 : ${ applicationScope.movie }<br>
	
	<hr>
	
	<h2>Scope를 생략하여 저장된 데이터 출력하기</h2>
	<p>
		el내장객체 pageScope, requestScope, sessionScope, applicationScope 생략 가능<br>
		el은 pageScope -> requestScope -> sessionScope -> applicationScope 순으로 찾음
		여러 scope에 동일한 속성을 기록하여 이름 충돌(naming conflict)이 의심되면 명시적으로 scope를 지정해야 함
	</p>
	 <h4>개인 정보(${ year })</h4>
	이름 : ${ person.name }<br>
	성별 : ${ person.gender }<br>
	나이 : ${ person.nai } <br>
	<h4>취향 정보</h4>
	${ person.name }님이 가장 좋아하는 음료 : ${ beverage }<br> 
	${ person.name }님이 가장 좋아하는 음료 : ${ sessionScope.beverage }<br> 
	${ person.name }님이 가장 좋아하는 음료 : ${ applicationScope.beverage }<br> 
	${ person.name }님이 가장 좋아하는 물건 : ${ products[0] }, 
									 ${ products[1] },
									 ${ products[2] }<br> 
	${ person.name }님이 가장 좋아하는 도서 : ${ book }<br> 
	${ person.name }님이 가장 좋아하는 영화 : ${ movie }<br>
	<!-- el은 영역이 맞지 않을 때 아무것도 뜨지 않음 -->
	${ requestScope.person.name }님이 가장 좋아하는 도서 : ${ sessionScope.book }<br> 
	${ requestScope.person.name }님이 가장 좋아하는 영화 : ${ applicationScope.movie }<br>
	<!--  -->
	
	
</body>
</html>