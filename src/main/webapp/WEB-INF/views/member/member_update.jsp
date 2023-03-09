<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 상세 페이지</title>
<%@ include file="../include/header.jsp" %>
<%@ include file="../include/sessionCheck.jsp" %>
<script>
    $(document).ready(function(){
        $("#btnUpdate").click(function(){
        	if(confirm("수정하시겠습니까")){
            	document.form1.action = "${path}/member/update.do";
            	document.form1.submit();
        	}
        });
        
        $("#btnDelete").click(function(){
        	if(confirm("삭제하시겠습니까")){
            	document.form1.action = "${path}/member/delete.do";
            	document.form1.submit();
        	}
        });
    });
</script>
</head>
<body>
    <h2>회원 정보 상세</h2>
        <form name="form1" method="post">
        <table border="1" data-width="400px">
        <tr>
        <td>아이디</td>
                <td>
                <input name="userId" value="${dto.userId}" readonly="readonly">
                </td>
        </tr>
        <tr>
        	<td>비밀번호</td>
            <td><input type="password" name="userPw"></td>
        </tr>
        <tr>
        	<td>이름</td>
             <td><input name="userName" value="${dto.userName}"></td>
         </tr>
         <tr>
                <td>이메일주소</td>
                <td><input name="userEmail" value="${dto.userEmail}"></td>
         </tr>
         <tr>
            <td>회원가입일</td>
                <td>
                    <fmt:formatDate value="${dto.regDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
          </tr> 
                    
          <tr>
         	<td>회원정보수정일</td>
         	<td>
         	<fmt:formatDate value="${dto.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
         	</td>
         </tr>
          <tr>
                <td colspan="2" align="center">
                    <input type="button" value="수정" id="btnUpdate">
                    <input type="button" value="삭제" id="btnDelete">
                    <div style="color: red;">${message}</div>
                </td>
           </tr>
    </table>
    </form>
</body>
</html>