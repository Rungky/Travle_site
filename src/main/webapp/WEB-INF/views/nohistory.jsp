<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>history</title>

<link rel="stylesheet" href="../css/history.css">
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

	<%@ include file="./header.jsp" %>
	<section>
		<article style='display:flex; justify-content:center;'>
				<div class="pd">
					<div class="atc">예약 내역</div>

				
					<table>
						<tr>
							<td>예약내역이 없습니다</td>
						</tr>
						<tr>
							<td><a href="trip?action=reservation.do">예약하러 가기</a></td>
						</tr>
					</table>
			</div>
		</article>
	</section>
	<%@ include file="./footer.jsp" %>
</body>

</html>