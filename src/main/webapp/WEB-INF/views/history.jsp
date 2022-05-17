<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>history</title>
<script type="text/javascript">



</script>

<link rel="stylesheet" href="./css/history.css">
<link rel="stylesheet" href="./css/header_footer.css">
<style>
.atc {
	font-size: 25px;
	font-weight: bold;
	padding-bottom: 10px;
	margin-bottom: 10px;
	border-bottom: 1px solid black;
}

table {
	margin-bottom: 20px;
	background-color: antiquewhite;
	padding: 10px 80px 10px 10px;
	width: 1000px;
}
</style>
</head>

<body>

	<%@ include file="./header.jsp"%>
	<section>
		<article style='display: flex; justify-content: center;'>
			<div class="pd">
				<div class="atc">예약 내역</div>

				<c:forEach var="result" items="${reserList}">
					<table>
						<tr>
							<td colspan="3">숙소 예약번호 ${result.reserve_no}</td>
						</tr>
						<tr>
							<td rowspan="4" class="img"><img class="img2"
								style="width: 150px; height: 100px; padding: 10px"
								src="./image/room/${result.room_picture}"></td>
							<td>${result.dorm_name}</td>
							<td rowspan="5" class="rv"><a
								href="/project_trip/trip?action=review.do&reserve_no=${result.reserve_no}"><button
										class="rvbt" name="action" value="review">리뷰</button></a> <a
								href="/project_trip/trip?action=reserDelete.do&reserve_no=${result.reserve_no}"><button
										class="re" name="action" value="review" onclick="return confirm('예약을 취소하시겠습니까?')">예약 취소하기</button></a></td>
						</tr>
						<tr>

							<td>${result.room_name}</td>
						</tr>
						<tr>
							<td>예약 날짜 : ${result.reserve_date}</td>
						</tr>
						<tr>
							<td>체크인 : ${result.reserve_checkin} / 체크아웃 :
								${result.reserve_checkout}</td>
						</tr>
						<tr>
							<td colspan="3" class="right"
								style="text-align: right; font-weight: bold;">금액 <span
								class="pri">${result.reserve_pay}</span></td>

						</tr>
					</table>
				</c:forEach>
			</div>
		</article>
	</section>
	<%@ include file="./footer.jsp"%>
</body>

</html>