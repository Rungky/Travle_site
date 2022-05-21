<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${ pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${contextPath}/resources/css/login.css">
<title>사적모임 | 아이디 찾기 결과</title>
</head>
<body>
	<div class="wrap_top">
        <section>
	        <div class="login_main">
	            <a href="${contextPath}/trip/main.do">
					<img src="${contextPath}/resources/image/logo-black.png" alt="로고">
				</a>
				<h2 style="text-align: center;">아이디 찾기</h2>
				
		         <c:if test = "${!empty member_id}">
		            <div class="login_top">
	            		<p>회원님의 아이디는 ${member_id} 입니다.<p>
	            	</div>
		         </c:if>
		         <c:if test = "${empty member_id}">
		         	<div class="login_top">
		            	<p>존재하지 않는 정보입니다.</p>
		            </div>
		         </c:if>
				
	            
	            <div>
	                <input class="back" type="reset" value="로그인 화면으로 돌아가기" onclick="location.href='${contextPath}/trip/login.do'"/>
	            </div>
	        </div>
	        
	    </section>
    </div>
</body>
</html>