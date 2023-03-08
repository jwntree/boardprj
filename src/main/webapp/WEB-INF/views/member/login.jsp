<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
<%@ include file="../include/header.jsp" %>
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
            <table border="1" data-width="400px">
            <tr>
                <td><label for="userId">아이디</label>
                <input name="userId" id="userId"></td>
            </tr>
            <tr>
                <td><label for="userPw">비밀번호</label>
                <input name="userPw" id="userPw"></td>
            </tr>
            <tr>
                    <td colspan="2" align="center">
                    <button type="button" id="btnLogin">로그인</button>
                    <c:if test="${msg == 'failure'}">
                    	<div style="color: red">
                    	아이디 또는 비밀번호가 일치하지 않습니다.
                    	</div>
                    </c:if>
                    <c:if test="${msg == 'logout'}">
                    	<div style="color: red">
                       	 로그아웃되었습니다.
                    	</div>
                    </c:if>
            </tr>
            </table>
    </form>
</body>
</html>