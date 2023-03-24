<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 목록</title>
<%@ include file="../include/header.jsp" %>
<script>
	<c:if test="${sessionScope.userId != null && dto.writerId !=null}">
		//다른 유저의 게시글 수정에 접근 금지
  		alert("잘못된 접근입니다.");
  		location.href="/board/list.do"; 
	</c:if>

    $(document).ready(function(){
        $("#btnDelete").click(function(){
        	if(confirm("삭제하시겠습니까")){
        		<%--
            	//document.deleteform.action = "${path}/board/delete.do";
            	//document.deleteform.submit();
            	--%>
            	f_submit();
        	}
        });
        $("#btnCancel").click(function(){
        	location.href = "/board/view.do?bno=${bno}";
        });
    });
    function f_submit(){
        var formData = $("#deleteform").serialize();
        $.ajax({
			type : "POST",
			cache: false,
			async: false,
            data : formData, 
			url : "/board/delete.do",
			success : function(data) {
				if($.trim(data) == "false") {
					alert('게시물 삭제에 실패했습니다. 비밀번호를 확인해주세요');
				} 
				else if($.trim(data) == "true") {
				   alert('게시물이 삭제 되었습니다.');
				   location.href = "/board/list.do";
                }
			},
    		error : function(request,status,error) {
    	        //alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
    	        alert("code:"+request.status +" 에러 발생");

			}
    })
    }
</script>
</head>
<body>
    <h2>게시글 삭제</h2>
    <div id="deleteBox">
    <span>게시글을 삭제하시겠습니까?</span><br><br>
    </div>
    <div>
    <form name="deleteform" id="deleteform">
        <input type= "hidden"name="bno" value="${bno}">
        <!-- 비회원용 -->   
    	<c:if test="${dto.writerId == null}">
    	<div>
    		<label for="password">비밀번호</label>
    		<input type="password" name="password" id="password">
    	</div>
    	</c:if> 
    	<div>
        <button type="button" id="btnDelete">삭제</button>
    	<button type="button" id="btnCancel">취소</button>
    	</div>
    </form>
    </div>
    
    
</body>
</html>