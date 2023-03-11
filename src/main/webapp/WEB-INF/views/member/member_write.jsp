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
            var password = $("#password").val();
            var userName = $("#userName").val();
            var userEmail = $("#userEmail").val();

            if(userId == ""){
                alert("아이디를 입력하세요");
                document.boardForm.title.focus();
                return;
            }
            if(password == ""){
                alert("비밀번호를 입입력하세요");
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
            // 폼에 입력한 데이터를 서버로 전송
            document.boardForm.submit();
		})
		
    });
</script>
</head>
<body>
    <h2>회원가입 페이지</h2>
        <form name="form1" method="post" action="${path}/member/insert.do">
        <table border="1" data-width="400px">
        <tr>
        <td>아이디</td>
                <td><input name="userId"></td>
        </tr>
        <tr>
        	<td>비밀번호</td>
            <td><input type="password" name="userPw"></td>
        </tr>
        <tr>
        	<td>이름</td>
             <td><input name="userName"></td>
         </tr>
         <tr>
                <td>이메일주소</td>
                <td><input name="userEmail"></td>
         </tr>
          <tr>
                <td colspan="2" align="center">
                   <input type="submit" value="확인">
                    <input type="reset" value="취소">
                </td>
           </tr>
    </table>
    </form>
</body>
</html>