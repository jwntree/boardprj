<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 작성</title>
<%@ include file="../include/header.jsp" %>
<script type="text/javascript">
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
            document.boardForm.submit();
		})
		
		$("#btnCancel").click(function(){
			location.href = "/board/list.do";
		})
		
	})
</script>
</head>
<body>
    <h2>게시글 작성</h2>
    <form name="boardForm" method="post" action="/board/insert.do">
    <div>
    	<label for="title">제목</label>
    	<input name="title" id="title">
    </div>
    <div>
    	<label for="content">내용</label>
    	<textarea name="content" id="content" rows="10" cols="80"></textarea>
    </div>
    <div>
    	<label for="writer">이름</label>
    	<input name="writer" id="writer">
    </div>
    <div>
        <button type="button" id="btnSave">확인</button>
    	<button type="button" id="btnCancel">취소</button>
    </div>
    </form>
</body>
</html>