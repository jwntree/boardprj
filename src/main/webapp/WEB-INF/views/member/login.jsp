<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
<%@ include file="../include/header.jsp" %>
<c:if test="${sessionScope.userId != null}">
    <script>
	alert("중복 로그인은 불가능합니다.");
	location.href="/"; 
	</script>
</c:if>
<script type="text/javascript">


 $(document).ready(function(){
	
	 $("#btnLogin").click(function(){
         var userId = $("#userId").val();
         var userPw = $("#userPw").val();
         if(userId == ""){
             alert("아이디를 입력하세요.");
             $("#userId").focus(); // 입력포커스 이동
             return; // 함수 종료
         }
         if(userPw == ""){
             alert("비밀번호를 입력하세요.");
             $("#userPw").focus();
             return;
         }
         document.loginForm.action="${path}/member/loginCheck.do"
         document.loginForm.submit();
	 })
	 
 })
</script>
</head>
<body>
    <h2>로그인</h2>
    <form name="loginForm" method="post">
            <div data-width="400px">
            <div>
                <label for="userId">아이디</label>
                <input name="userId" id="userId">
            </div>
            <div>
                <label for="userPw">비밀번호</label>
                <input name="userPw" id="userPw" type="password">
            </div>
			<div>
				<input type="checkbox" name="autologin" id="autologin">
				<label>로그인유지</label>
			</div>
			<div>
					<button type="button" id="btnLogin">로그인</button> <input
					type="button" value="회원가입"
					onclick="location.href='${path}/member/write.do'"> <c:if
						test="${msg == 'failure'}">
						<div style="color: red">아이디 또는 비밀번호가 일치하지 않습니다.</div>
					</c:if> <c:if test="${msg == 'logout'}">
						<div style="color: red">로그아웃되었습니다.</div>
					</c:if>
			</div>
		</div>
            
    </form>
</body>
</html>