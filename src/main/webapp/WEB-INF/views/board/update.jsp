<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 수정</title>
<%@ include file="../include/header.jsp" %>
<%-- 
<%@ include file="../include/sessionCheck.jsp" %>
--%>
<script type="text/javascript">
<c:if test="${sessionScope.userId != null && dto.writerId !=null}">
//다른 유저의 게시글 수정에 접근 금지
    alert("잘못된 접근입니다.");
    location.href="/board/list.do"; 
</c:if>

	$(document).ready(function(){
		$("#btnSave").click(function(){
            var title = $("#title").val();
            var content = $("#content").val();
            var writer = $("#writer").val();
            if(title == ""){
                alert("제목을 입력하세요");
                document.boardForm.title.focus();
                return;
            }
            if(content == ""){
                alert("내용을 입력하세요");
                document.boardForm.content.focus();
                return;
            }
            if(writer == ""){
                alert("이름을 입력하세요");
                document.boardForm.writer.focus();
                return;
            }
            // 폼에 입력한 데이터를 서버로 전송
            //document.boardForm.submit();
            f_submit();
		})
		
		$("#btnCancel").click(function(){
			location.href = "/board/list.do";
		})
	    function f_submit(){
	        var formData = $("#boardForm").serialize();
	        $.ajax({
				type : "POST",
				cache: false,
				async: false,
	            data : formData, 
				url : "/board/update.do",
				success : function(data) {
					if($.trim(data) == "false") {
						alert('게시물 수정에 실패했습니다. 비밀번호를 확인해주세요');
					} 
	                if($.trim(data) == "true") {
					   location.href = "/board/list.do";
	                }
				},
	    		error : function(request,status,error) {
	    	        //alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	    	        alert("code:"+request.status +" 에러 발생");

				}
	    })
	    }
		
	})
</script>

</head>
<body>
    <h2>게시글 수정</h2>
    <form name="boardForm" id="boardForm">
    <div>
    	<input type="hidden" name="bno" id="bno" value="${dto.bno}">
    </div>
    <div>
    	<label for="title">제목</label>
    	<input name="title" id="title" value="${dto.title}">
    </div>
    <div>
    	<label for="content">내용</label>
    	<textarea name="content" id="content" rows="10" cols="80"><c:out value="${dto.content}" /></textarea>
    </div>

    <c:if test="${sessionScope.userId == null}">
    <div>
    	<label for="writer">이름</label>
    	<input name="writer" disabled="disabled" id="writer" value="${dto.writer}">
    </div>
    </c:if>
    <c:if test="${dto.writerId == null}">
    <div>
    	<label for="password">비밀번호</label>
    	<input type="password" name="password" id="password">
    </div>
    </c:if> 
    
    <div>
        <button type="button" id="btnSave">확인</button>
    	<button type="button" id="btnCancel">취소</button>
    </div>
    </form>
</body>
</html>