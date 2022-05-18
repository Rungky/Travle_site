<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${contextPath}/resources/css/header_footer.css">
<link rel="stylesheet" href="${contextPath}/resources/css/myLike.css">
</head>
<body>
	<%@ include file="header.jsp"%>
	<div id="wrap_mypage">

		<nav id="mypage_nav">
			<div>
				<ul class="">
					<li class="fw_b" style="font-size: 1.1em; color: black;">사적모임✈️</li>
					<br>
					<li><a href="trip?action=mypage.do">마이페이지</a></li>
					<li><a href="trip?action=myLike.do">내 관심숙소</a></li>
					<li><a href="trip?action=history.do">내 예약내역</a></li>
					<li><a href="trip?action=qna.do">Q&A</a></li>
				</ul>
			</div>
		</nav>
		<section id="like_section">
			<div id="like_header">
				내 관심숙소 👁️👁️
				<hr>
			</div>
			<div id="like_main">
				<c:forEach var="i" items="${dorm_list }">
					<a style="text-decoration: none"
						href="trip?action=detail.do&dormno=${i.dorm_no }&reserve_checkin=${reserve_checkin}&reserve_checkout=${reserve_checkout}">
						<div class="room"
							style="background-image:linear-gradient( rgba(0, 0, 0, 0), rgba(0, 0, 0.5, 0.8) ),url(./image/dorm/${i.getDorm_picture()}); ">
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
			</div>

		</section>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>