<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${ pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${contextPath}/resources/css/header_footer.css">
<link rel="stylesheet" href="${contextPath}/resources/css/main.css">
<title>main</title>

	<%@ include file="./header.jsp"%>
	<div class="wrap_top">
		<section>
			<div class="banner">
				<img
					src="${contextPath}/resources/image/main.jpg">
			</div>
		</section>
	</div>
	<div class="main_link">
		<ul>
			<li><a href="${contextPath}/trip/reservation.do&dorm_category_no=1">
					<span class="link_icon"> <img
						src="${contextPath}/resources/image/ico_category_h.png"></span>
					<p style="color: black; font-weight: 600;">호텔</p>
			</a></li>
			<li><a href="${contextPath}/trip/reservation.do&dorm_category_no=4"> <span class="link_icon"><img
						src="${contextPath}/resources/image/ico_category_g.png"></span>
					<p style="color: black; font-weight: 600;">게스트하우스</p>
			</a></li>	
			<li><a href="${contextPath}/trip/reservation.do"> <span class="link_icon"><img
						src="${contextPath}/resources/image/ico_category_do.png"></span>
					<p style="color: black; font-weight: 600;">전체 보기</p>
			</a></li>
			<li><a href="${contextPath}/trip/reservation.do&dorm_category_no=3"> <span class="link_icon"><img
						src="${contextPath}/resources/image/ico_category_r.png"></span>
					<p style="color: black; font-weight: 600;">리조트</p>
			</a></li>
			<li><a href="${contextPath}/trip/reservation.do&dorm_category_no=2"> <span class="link_icon"> <img
						src="${contextPath}/resources/image/ico_category_p.png"></span>
					<p style="color: black; font-weight: 600;">펜션</p>
			</a></li>
		</ul>
	</div>
	<div class="wrap_middle">
		<section>
			<div class="ad">
				<p>
					앱 다운 받고 <span style="font-weight: 700;">더 많은 혜택</span> 받으세요!
				</p>
				<div class="ad_imgs">
					<img src="${contextPath}/resources/image/playStore.png"> <img
						src="${contextPath}/resources/image/appStore.png">
				</div>
			</div>
		</section>
	</div>

	<div class="wrap_bottom" >
		<h2 >🔥요즘 뜨는 장소🔥</h2>
	</div>

<div class="slidebox">
    <input type="radio" name="slide" id="slide01" checked>
    <input type="radio" name="slide" id="slide02">
    <input type="radio" name="slide" id="slide03">
    <input type="radio" name="slide" id="slide04">
    <input type="radio" name="slide" id="slide05">
    <input type="radio" name="slide" id="slide06">
    
    <ul class="slidelist">
	
			<c:forEach var="i" items="${dormList}"  begin="0" end="5" varStatus="st">
			<li class="slideitem">
				<div>
					<div class="a_wrap">
						<a href="trip/detail.do&dormno=${i.dorm_no }&reserve_checkin=${reserve_checkin}&reserve_checkout=${reserve_checkout}">
						<img src="${contextPath}/resources/image/dorm/${i.dorm_picture}">
						<!-- 	<div class="category">숙소</div> -->
							<div class="wrap_text">
								<div>
									<p style="font-size: 19px;">${i.dorm_name}</p>
									<p>${i.dorm_addr}</p>
								</div>
							</div> </a>
					</div>
				</div>
			</li>
			</c:forEach>
		</ul>

		<ul class="paginglist">
        <li> <label for="slide01"></label> </li>
        <li> <label for="slide02"></label> </li>
        <li> <label for="slide03"></label> </li>
        <li> <label for="slide04"></label> </li>
        <li> <label for="slide05"></label> </li>
        <li> <label for="slide06"></label> </li>
    </ul>
</div>
	
	<%@ include file="./footer.jsp"%>
</body>
</html>