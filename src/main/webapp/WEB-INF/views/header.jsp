<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<c:set var="contextPath" value="${ pageContext.request.contextPath}" />
<header>
	<div class="hd">
		<div>
			<form action="${contextPath}/trip/main.do">
				<button class="bt">
					<img class="rogo" src="${pageContext.request.contextPath}/resources/image/logo-white.png">
				</button>
			</form>
		</div>
		<nav>
			<ul>
				<li><a href="${ contextPath}/trip/history.do">예약 내역</a></li>
				<li><a href="${ contextPath}/trip/qna.do">Q&A</a></li>
				
				<c:if test="${empty id}">
					<li><a href="${ contextPath}/trip/login.do">로그인</a></li>
				</c:if>
				<c:if test="${!empty id}">
					<li><a href="${ contextPath}/trip/mypage.do">${id} </a></li>
				</c:if>
			</ul>
		</nav>
	</div>
</header>

