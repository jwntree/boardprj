<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 목록</title>
<%@ include file="../include/header.jsp"%>
<script type="text/javascript">
	$(document).ready(function(){	
		$("#btnSearch").click(function(e){
			e.preventDefault();
			location.href = "/board/list.do"+ "?searchOption=" + $('#searchOption').val()+ "&keyword="+ $('#keyword').val();
		})
		
	});

	function list(page){
        location.href="${path}/board/list.do?curPage="+page+"perPage=${pageMaker.getCri().getPerPageNum()}"+"&searchOption=${searchOption}"+"&keyword=${keyword}";
    }
</script>
<style type="text/css">
li {
	list-style: none;
	float: left;
	padding: 6px;
}
</style>
</head>
<body>
    <h2>게시글 목록</h2>
    
    	<form id="SearchForm">
        <select name="searchOption" id="searchOption">
        <option value="all" <c:out value="${searchOption == 'all'?'selected':''}"/>>제목+작성자+내용</option>
        <option value="writer"<c:out value="${searchOption == 'writer'?'selected':''}"/>>제목</option>
        <option value="content"<c:out value="${searchOption == 'content'?'selected':''}"/>>작성자</option>
        <option value="title"<c:out value="${searchOption == 'title'?'selected':''}"/>>내용</option>
        
        </select>
        <input name="keyword" id="keyword" value="${keyword}">
    	<button type="button" type="submit" id="btnSearch">검색</button>
        <%--
        <!-- 로그인한 사용자만 글쓰기 버튼을 활성화 -->
    	<c:if test="${sessionScope.userId != null}">
        	<button type="button" id="btnWrite" onclick="location.href='${path}/board/write.do'">글쓰기</button>
    	</c:if>
    	--%>
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
            <td>${row.bno}</td>
            <td><a href="/board/view.do?bno=${row.bno}">${row.title}</a></td>
            <td>${row.writer}<c:if test="${row.writerId != null}"><a href="/member/info.do?userId=${row.writerId}">*</a></c:if></td>
            <td>
            <fmt:formatDate value="${row.regdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
            <td>${row.viewcnt}</td>
        </tr>
        </c:forEach>
    </table>
	<div class="pagenation" id="pagenation">
		<ul>
			<c:if test="${pageMaker.prev}">
				<li><a
					href="list.do${pageMaker.makeQuery(pageMaker.startPage - 1)}">이전</a></li>
			</c:if>

			<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}"
				var="idx">
				<li><a href="list.do${pageMaker.makeQuery(idx)}+"&searchOption=${searchOption}"+"&keyword=${keyword}">${idx}</a></li>
			</c:forEach>

			<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
				<li><a href="list.do${pageMaker.makeQuery(pageMaker.endPage + 1)}+"&searchOption=${searchOption}"+"&keyword=${keyword}">다음</a></li>
			</c:if>
		</ul>
	</div>
</body>
</html>