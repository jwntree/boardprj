<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 목록</title>
<%@ include file="../include/header.jsp" %>
</head>
<body>
    <h2>게시글 목록</h2>
    <input type="button" value="게시글 등록" onclick="location.href='${path}/board/write.do'">
    <table border="1" data-width="700px">
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>조회수</th>
        </tr>
        <c:forEach var="row" items="${list}">
        <tr>
            <td>${row.bno}</td>
            <td>${row.title}</td>
            <td>${row.writer}</td>
            <td>${row.regdate}</td>
            <td>${row.viewcnt}</td>
        </tr>
        </c:forEach>
    </table>
</body>
</html>