<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
<%@ include file="../include/header.jsp" %>
<script>
    $(document).ready(function(){
		$("#btnSignup").click(function(){
            var userId = $("#userId").val();
            var password = $("#userPw").val();
            var userName = $("#userName").val();
            var userEmail = $("#userEmail").val();

            if(userId == ""){
                alert("아이디를 입력하세요");
                document.boardForm.title.focus();
                return;
            }
            if(password == ""){
                alert("비밀번호를 입력하세요");
                document.boardForm.content.focus();
                return;
            }
            if(userName == ""){
                alert("이름을 입력하세요");
                document.boardForm.writer.focus();
                return;
            }
            if(userEmail == ""){
                alert("이메일을 입력하세요");
                document.boardForm.writer.focus();
                return;
            }
            let regex = new RegExp('[a-z0-9]+@[a-z]+\.[a-z]{2,3}');
            if(!regex.test(userEmail)){
                alert("올바르지 않은 이메일입니다.");
                document.boardForm.writer.focus();
                return; 	
            }
			var idChkVal = $("#idChk").val();
			if(idChkVal != "Y"){
				alert("중복확인 버튼을 눌러주세요.");
				return;
			}
            // 폼에 입력한 데이터를 서버로 전송
            document.form1.submit();
		})
		
		$("#btnCancel").click(function(){
			location.href = "/member/login.do";
		})
		
    });
    
	function fn_idChk(){
		$.ajax({
			url : "/member/idChk",
			type : "post",
			dataType : "json",
			data : {"userId" : $("#userId").val()},
			success : function(data){
				if(data == 1){
					alert("중복된 아이디입니다.");
				}else if(data == 0){
					$("#idChk").attr("value", "Y");
					alert("사용가능한 아이디입니다.");
				}
			}
		})
	}
	
	function IdChanged(){
		$("#idChk").attr("value", "N");
	}
	
</script>
</head>
<body>
	<h2>회원가입 페이지</h2>
	<form name="form1" method="post" action="${path}/member/insert.do">
		<div data-width="400px">
			<div>
				<label for="userId">아이디</label> 
				<input name="userId" id="userId" onkeyup="IdChanged()">
				<button class="idChk" type="button" id="idChk" onclick="fn_idChk();" value="N">중복확인</button>
			</div>
			<div>
				<label for="password">비밀번호</label> 
				<input type="password" name="userPw" id="userPw">
			</div>
			<div>
				<label for="userName">이름</label>
				<input name="userName" id="userName">
			</div>
			<div>
				<label for="userEmail">이메일주소</label>
				<input name="userEmail" id="userEmail">
			</div>
			</div>
	</form>
	<div>
			<button id="btnSignup">확인</button>
			<button id="btnCancel">취소</button>
	</div>
</body>
</html>