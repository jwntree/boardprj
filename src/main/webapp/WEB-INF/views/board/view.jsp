<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 목록</title>
<%@ include file="../include/header.jsp" %>
</head>
<body>
    <h2>회원 목록</h2>
    <input type="button" value="게시글 등록" onclick="location.href='${path}/board/write.do'">
    <table border="1" data-width="700px">
    </table>
</body>
</html>