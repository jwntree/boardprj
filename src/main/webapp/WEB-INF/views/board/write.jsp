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
            var password = $("#password").val();

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
            if(password == ""){
                alert("비밀번호를 입력하세요");
                document.boardForm.writer.focus();
                return;
            }
            // 폼에 입력한 데이터를 서버로 전송
            document.boardForm.submit();
		})
		
		$("#btnCancel").click(function(){
			location.href = "/board/list.do";
		})
		
		$('.summernote').summernote({
			height: 500,
			width: 800,
			lang: "ko-KR",
				callbacks : {
					onImageUpload : function(files, editor, welEditable) {     
						for (var i = 0; i < files.length; i++) {
							sendFile(files[i], this);
						}
					}
				}			
			});
		
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
				output = '';
				output += '<div id="file_upload_' +data.fileNo +'">'
				output += data.fileName
				output += '<input type="hidden" name="attachList" value='+ data.fileNo +'>';
				output += '<button type="button" id="btnDelete" onclick="file_remove(' + data.fileNo + ')">삭제</button>'			
				output += '</div>'
				$("#fileUpload").append(output);
				
			}
		});
	}
	function file_remove(fileno){
		$('#file_upload_' + fileno).remove()
	}
	
</script>
</head>
<body>
    <h2>게시글 작성</h2>
    <form name="boardForm" method="post" action="/board/insert.do">
    <div>
    	<label for="title">제목</label>
    	<input name="title" id="title">
    </div>
    <!-- 
    <div>
    	<label for="content">내용</label>
    	<textarea name="content" id="content" rows="10" cols="80"></textarea>
    </div>
     -->
    <c:if test="${sessionScope.userId == null}">
    <div>
    	<label for="writer">이름</label>
    	<input name="writer" id="writer">
    </div>
    <div>
    	<label for="password">비밀번호</label>
    	<input name="password" id="password" type="password">
    </div>
    </c:if>
    <p></p>
    <div>
  		<textarea class="summernote" name="content" id="content"></textarea>    
	</div> 
	<div id="fileUpload">
		<span>파일 업로드</span>
		<p></p>
	</div>  
    <div>
        <button type="button" id="btnSave">확인</button>
    	<button type="button" id="btnCancel">취소</button>
    </div>
    </form>
</body>
</html>