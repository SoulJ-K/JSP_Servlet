<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 작성</title>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	<div class="outer">
		<br>
		<h2 align="center">게시판 작성</h2>
		<div class="tableArea">
			<form action="<%= request.getContextPath() %>/insert.no" method="post">
				<table>
					<tr>
						<th>제목</th>
						<td colspan="3"><input type="text" size="50" name="title"></td>				
					</tr>
					<tr>
						<th>작성자</th>
						<td>
							<%= loginUser.getNickName() %>
						</td>
						<th>작성일</th>
						<td><input type="date" name="date"></td>
					</tr>
					<tr>
						<th>내용</th>
					</tr>
					<tr>
						<td colspan="4">
							<textarea name="content" cols="60" rows="15" style="resize:none;"></textarea>
						</td>
					</tr>
				</table>
				
				<br>
				
				<div align="center">
					<button type="submit" id="insertNoBtn">등록</button>
					<div onclick="location.href='javascript:history.go(-1);'" id="cancelBtn">취소</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>