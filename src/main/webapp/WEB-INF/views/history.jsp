<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>사적모임 | history</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.0.js"></script>
<link rel="stylesheet"
	href="${contextPath}/resources/css/header_footer.css">
<link rel="stylesheet" href="${contextPath}/resources/css/history.css">
<style>
.atc {
	font-size: 25px;
	font-weight: bold;
	padding-bottom: 10px;
	margin-bottom: 10px;
	border-bottom: 1px solid black;
}
</style>
<script>
	$(function() {

		history_del();
		hide();
	})

	function hide() {
		let date = new Date();
		console.log("원래 date", date);

		for (let i = 0; i < $(".checkin").length; i++) {
			let target = $($(".checkin")[i]).html();
			console.log("원래 target", target);
			let target2 = new Date(target).getTime() // 적힌글을 날짜로

			//date값을 toISOString 해주면 2022-05-17 예쁘게 잘라준다
			let date2 = new Date(date.toISOString().split("T")[0]).getTime()
			console.log("바뀐 타겟", target2);
			console.log("바뀐 데이트", date2);

			if (target2 <= date2) {
				//이때에 예약번호를 가진 버튼을 숨긴다
				$($(".re")[i]).hide();
				$($(".rvbt")[i]).show();

			} else {
				$($(".re")[i]).show();
				$($(".rvbt")[i]).hide();
			}
		}
	}

	function history_del() {
		$(".del").off("click").on("click", function() {
			let date = new Date();
			let date2 = new Date(date.toISOString().split("T")[0]).getTime();

			//if($(this).data("checkout")  
			console.log("date값", date2);
			let check = new Date($(this).data("checkout")).getTime();
			console.log("최종 check", check);
			if (check < date2) {
				let no = $(this).data("num");
				console.log("날짜가 같습니다 삭제 가능함", no);
				$.ajax({
					url : "http://localhost:8080/final_trip/trip/Delete.do",
					type : "get",
					data : {
						reserve_no : no
					},
					success : function(data) {
						console.log("컨트롤러에서 넘어온 값4", data.msg);
						if (data.msg == 0 || data.msg == 1) {
							console.log("값 가져옴 성공");
							location.reload();
						}
					},
					fail : function(data) {
						console.log("fail", data);
					},
					complete : function(data) {
						console.log("comp", data);
					}
				})

			} else {
				console.log("날짜가 부합하지 않음");
				alert("예약내역을 삭제할 수 있는 기간이 아닙니다!")
				return false;
			}
		});
	}
</script>
</head>

<body>

	<%@ include file="./header.jsp"%>
	<section>
		<article style='display: flex; justify-content: center;'>
			<div class="pd">
				<div class="atc">예약 내역📆</div>
				<br>

				<c:forEach var="result" items="${reserList}">
					<table style="padding: 10px 10px 10px 10px;" id="table_css">
						<form
							action="${contextPath}/trip/reserDelete.do">
							<tr>
								<td colspan="3">숙소 예약번호 ${result.RESERVE_NO}</td>
								<td><input type="button" class="del check css"
									data-checkout="${result.RESERVE_CHECKOUT}"
									data-num="${result.RESERVE_NO}" value="X"></td>
							</tr>
							<tr>
								<td rowspan="4" class="img"><img class="img2"
									style="width: 150px; height: 130px; padding: 10px"
									src="${contextPath}/resources/image/room/${result.ROOM_PICTURE}"></td>
								<td>${result.DORM_NAME}</td>
								<td rowspan="5" class="rv">
									<div class="forms">
										<a
											href="${contextPath}/trip/review.do?reserve_no=${result.RESERVE_NO}">
											<input type="button" class="rvbt button" id="re_button" name="action"
												value="리뷰">
										</a> 
										<input type="submit" class="re button" value="예약 취소하기"
											data-no="${result.RESERVE_NO}"
											onclick="return confirm('예약을 취소하시겠습니까?')">
										<input
										type="hidden" name="reserve_checkin"
										value="<fmt:formatDate value='${result.RESERVE_CHECKIN}' pattern='yyyy-MM-dd' />"> 
										<input type="hidden" name="reserve_no" value="${result.RESERVE_NO}">
						</form>
						</div>
						</td>
						</tr>
						<tr>

							<td>${result.ROOM_NAME}</td>
						</tr>
						<tr>
							<td>예약 날짜 : <span class="check css"><fmt:formatDate
										value="${result.RESERVE_DATE}" pattern="yyyy-MM-dd" /></span></td>
						</tr>
						<tr>
							<td>체크인 : <span class="checkin css"><fmt:formatDate
										value="${result.RESERVE_CHECKIN}" pattern="yyyy-MM-dd" /></span> /
								체크아웃 : <span class="check css"><fmt:formatDate
										value="${result.RESERVE_CHECKOUT}" pattern="yyyy-MM-dd" /></span>
							</td>
						</tr>
						<tr>
							<td colspan="3" class="right"
								style="text-align: right; font-weight: bold;">금액 <span
								class="pri"> ${result.RESERVE_PAY}</span></td>
						</tr>
					</table>
				</c:forEach>
			</div>
		</article>
	</section>
	<%@ include file="./footer.jsp"%>

</body>

</html>