<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 상세 페이지</title>
<%@ include file="../include/header.jsp" %>
<script>
    $(document).ready(function(){
    });
</script>
</head>
<body>
    <h2>회원 정보</h2>
        <table border="1" data-width="400px">
        <tr>
        <td>아이디</td>
                <td>
                <span id="userId">${dto.userId}</span>
                </td>
        </tr>
         <tr>
			<td>이메일주소</td>
			<td><span id="userEmail">${dto.userEmail}</span></td>


		</tr>
		<tr>
			<td>회원가입일</td>
			<td><span id="userCreated"><fmt:formatDate
						value="${dto.regDate}" pattern="yyyy-MM-dd HH:mm:ss" /></span></td>
		</tr>
	</table>
</body>
</html>