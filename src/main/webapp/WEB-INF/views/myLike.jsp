<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${ pageContext.request.contextPath}" />
<c:set var="URL" value="${pageContext.request.requestURL}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사적모임 | 관심숙소</title>
<link rel="stylesheet" href="${contextPath}/resources/css/header_footer.css">
<link rel="stylesheet" href="${contextPath}/resources/css/myLike.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<style>
	.underline{
		text-decoration: underline;
		font-weight: 600;
	}
</style>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div id="wrap_mypage">
	<%@include file = "mypage_nav.jsp" %>
		<section id="like_section">
			<div id="like_header">
				내 관심숙소 👁️👁️
				<hr>
			</div>
			<div id="like_main">
				<c:forEach var="i" items="${dorm_list }">
					<a style="text-decoration: none"
						href="${contextPath}/trip/detail.do?dormno=${i.dorm_no }&reserve_checkin=${reserve_checkin}&reserve_checkout=${ reserve_checkout}">
						<div class="room"
							style="background-image:linear-gradient( rgba(0, 0, 0, 0), rgba(0, 0, 0.5, 0.8) ),url(../resources/image/dorm/${i.getDorm_picture()}); ">
							<div class="ggumim">
								<p>
									<strong>
										<h3 class="font1">${i.dorm_name }</h3>
									</strong>
								</p>
								<p>
									<span class="font1">리뷰(<span class="font1">${i.count_reserve_no}</span>)
									</span>
								</p>
								<p>
									<strong class="font1 addr">${i.dorm_addr}</strong>
								</p>
							</div>
							<div class="price">
								<p class="logo_h">💙</p>
								<p>
									<strong>
										<h3 class="font1" style="font-size: 1.3em;">${i.min_pay_night}원</h3>
									</strong>
								</p>
							</div>
						</div>
					</a>
				</c:forEach>
				<c:if test="${empty dorm_list}">
					<div style="margin: 100px auto 0; text-align: center; font-size: 1.2em">
						<span>관심등록한 숙소가 없습니다.</span><br> 
						<a	href="${contextPath}/trip/reservation.do" style="font-size: 0.9em;">사적모임의 다양한 숙소 보러가기</a>
					</div>
				</c:if>
			</div>

		</section>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>