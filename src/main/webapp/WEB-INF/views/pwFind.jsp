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
<title>사적모임 | 비밀번호 찾기</title>
</head>
<body>
	<div class="wrap_top">
        <section>
	        <div class="login_main">
	            <a href="#">
					<img src="${contextPath}/resources/image/logo-black.png" alt="로고">
				</a>
				<h2 style="text-align: center;">비밀번호 찾기</h2>
	            <form action="${ contextPath}/trip/pwFindCheck.do" method="post" name="f1">
	                <div class="login_top">
	                    <input type="text" name="member_id" placeholder="아이디" />
	                    <input type="text" name="member_tel" placeholder="전화번호" />
	                </div>
	                <div>
	                    <input class="find_btn" type="submit" value="찾기"/>
	                    <input class="reset_btn" type="reset" value="취소" onclick="location.href='${contextPath}/trip/login.do'"/>
	                </div>
	            </form>
	        </div>
	    </section>
    </div>
</body>
</html>