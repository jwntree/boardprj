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
    
    <form name="form1" method="post" action="${path}/board/list.do">
        <select name="searchOption">
        <option value="all" <c:out value="${searchOption == 'all'?'selected':''}"/>>제목+작성자+내용</option>
        <option value="writer"<c:out value="${searchOption == 'all'?'selected':''}"/>>제목</option>
        <option value="content"<c:out value="${searchOption == 'all'?'selected':''}"/>>작성자</option>
        <option value="title"<c:out value="${searchOption == 'all'?'selected':''}"/>>내용</option>
        
        </select>
        <input name="keyword" value="${keyword}">
        <input type="submit" value="조회">
        <button type="button" id="btnWrite" onclick="location.href='${path}/board/write.do'">글쓰기</button>
    </form>
    <!-- 레코드의 갯수를 출력 -->
    ${count}개의 게시물이 있습니다
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
            <td>
            <a href="/board/view.do?bno=${row.bno}">${row.bno}</a>
            </td>
            <td>${row.title}</td>
            <td>${row.writer}</td>
            <td>
            <fmt:formatDate value="${row.regdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
            <td>${row.viewcnt}</td>
        </tr>
        </c:forEach>
    </table>
</body>
</html>