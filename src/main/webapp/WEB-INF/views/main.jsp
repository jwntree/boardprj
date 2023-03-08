<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<title>main</title>
</head>
<body>
<%@ include file="include/header.jsp" %>
<c:choose>
    <c:when test="${msg == 'success'}">
    <h2>${sessionScope.userName}(${sessionScope.userId})님 환영합니다.</h2>
    </c:when>
    <c:otherwise>
    <h1>${msg}</h1>
    </c:otherwise>
</c:choose>    
</body>
</html>
