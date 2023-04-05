<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<!--  부트스트랩  -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!--  서머노트  -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet"> 
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<script src=" https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/lang/summernote-ko-KR.min.js"></script>
<a href="/">홈페이지</a>
<%-- <a href="/member/list.do">회원관리</a> --%>
<a href="/board/list.do">게시물</a>
<c:choose>
    <c:when test="${sessionScope.userId == null}">
        <a href="${path}/member/login.do">로그인</a>
    </c:when>
    <c:otherwise>
    <div>
        ${sessionScope.userName}님이 로그인중입니다.
        <a href="${path}/member/logout.do">로그아웃</a>
        <a href="/member/updateView.do">회원정보수정</a>
        
     </div>
    </c:otherwise>
</c:choose>



