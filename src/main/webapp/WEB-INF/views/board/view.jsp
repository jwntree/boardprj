<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글</title>
<%@ include file="../include/header.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	$("#btnUpdete").click(function(){
		location.href = "/board/updateView.do?bno=${dto.bno}";

	})
	
	$("#btnList").click(function(){
		location.href = "/board/list.do";
	})
})

function deletePost() {
	location.href = "/board/deleteView.do?bno=${dto.bno}";
}

</script>

<%-- 
<script type="text/javascript">
/*
function deletePost() {
	
  if (confirm('게시글을 삭제할까요?')){
	  const formHtml = `
	      <form id="deleteForm" action="/board/delete.do" method="post">
	          <input type="hidden" id="bno" name="bno" value="${dto.bno}" />
	      </form>
	  `;
	  const doc = new DOMParser().parseFromString(formHtml, 'text/html');
	  const form = doc.body.firstChild;
	  document.body.append(form);
	  document.getElementById('deleteForm').submit();
	  }
}
*/
</script>
--%>

</head>
<body>
    <h2>게시글 조회</h2>
    <div id="title">${dto.title}</div>
    <div>
    <span>${dto.writer}<c:if test="${dto.writerId != null}"><a href="/member/info.do?userId=${dto.writerId}">*</a></c:if></span>|
    <span><fmt:formatDate value="${dto.regdate}" pattern="yyyy-MM-dd HH:mm:ss"/></span> |
    <span>${dto.viewcnt}</span>
    </div>
    <div id="content">
    	<div>${dto.content}</div>
    </div>
    <%-- 
    <c:if test="${sessionScope.userId == dto.writerId}">
    <button type="button" id="btnUpdete">수정</button>
    <button type="button" id="btnDelete" onclick="deletePost()">삭제</button>
     <button type="button" id="btnWrite" onclick="location.href='${path}/board/write.do'">글쓰기</button>
    </c:if>
    --%>
    <c:if test="${dto.writerId == null || (sessionScope.userId != null && sessionScope.userId == dto.writerId)}">
    <button type="button" id="btnUpdete">수정</button>
    <button type="button" id="btnDelete" onclick="deletePost()">삭제</button>
    </c:if>
    <button type="button" id="btnList">목록</button>
    <button type="button" id="btnWrite" onclick="location.href='${path}/board/write.do'">글쓰기</button>
    
    
        
</body>
</html>