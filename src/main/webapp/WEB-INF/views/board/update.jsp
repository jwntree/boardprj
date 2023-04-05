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
<c:if test="${sessionScope.userId != null && dto.writerId !=null}">
<script type="text/javascript">
    alert("잘못된 접근입니다.");
    location.href="/board/list.do"; 
</script>
</c:if>
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
            //document.boardForm.submit();
            f_submit();
		})
		
		$("#btnCancel").click(function(){
			location.href = "/board/list.do";
		})
		
		$('.summernote').summernote({
			height: 500,
			lang: "ko-KR",
				callbacks : {
					onImageUpload : function(files, editor, welEditable) {     
						for (var i = 0; i < files.length; i++) {
							sendFile(files[i], this);
						}
					}
				}			
			});
		
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
	
	function sendFile(file, editor) {
		var form_data = new FormData();
		form_data.append('file', file);
		$.ajax({
			data : form_data,
			type : "POST",
			url : '/board/uploadImage',
			cache : false,
			contentType : false,
			enctype : 'multipart/form-data',
			processData : false,
			success : function(data) {

				if (data.responseCode != "success") {
					alert("오류가 발생했습니다.")
					return;
				}
				$(editor).summernote('insertImage', data.url, function($image) {
					$image.css('height', "auto");
					$image.css('width', "auto");
					$image.css('max-width', "500px");
				});
				
			}
		});
	}
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
    	<textarea class="summernote" name="content" id="content" rows="10" cols="80"><c:out value="${dto.content}" /></textarea>
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