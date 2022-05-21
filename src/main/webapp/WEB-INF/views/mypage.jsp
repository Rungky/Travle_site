<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${ pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My page</title>
<link rel="stylesheet"
	href="${contextPath}/resources/css/header_footer.css">
<link rel="stylesheet"
	href="${contextPath}/resources/css/mypage-style.css">
</head>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.2.0.min.js"></script>
<script type="text/javascript">
	$(function() {
		//닉네임
		$('.input1').click(function() {
			$('.re_input').show();
			$('.re_input1').show();
			$('.re_input1').show();
		});
		$('.re_input1').click(function() {
			$('.re_input').hide();
			$('.re_input1').hide();
			$('.re_input1').hide();
		});
		//전화번호
		$('.input2').click(function() {
			$('.re_input2').show();
			$('.re_input2_1').show();
			$('.re_input2_1').show();
		});
		$('.re_input2_1').click(function() {
			$('.re_input2').hide();
			$('.re_input2_1').hide();
			$('.re_input2_1').hide();
		});

	});
</script>


<body>
	<%@ include file="header.jsp"%>
	<div id="wrap_mypage">

		<nav id="mypage_nav">
			<div>
				<ul class="">
					<li class="fw_b" style="font-size: 1.1em; color: black;">사적모임✈️</li>
					<br>
					<li><a href="${contextPath}/trip/mypage.do">마이페이지</a></li>
					<li><a href="${contextPath}/trip/myLike.do">내 관심숙소</a></li>
					<li><a href="${contextPath}/trip/history.do">내 예약내역</a></li>
					<li><a href="${contextPath}/trip/qna.do">Q&A</a></li>
				</ul>
			</div>
		</nav>

		<section>
			<div class="mypage_main">
				<div class="mypage_header">

					<div>
						<h2>마이페이지</h2>
						<!--로그인한 회원 정보에 맞게 출력되야함-->
						<p>${member.member_id}</p>

					</div>
				</div>
				<hr>

				<%-- 백단에서 조건걸어서 수정하기 --%>
				<form method="post" id="modify_form" class="mypage_form1" action="${contextPath}/trip/modifyMember.do">
						<input type="hidden" name="member_id" value="${member.member_id}"> <br>
					<div>
						<span>닉네임
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${member.member_names}</span>
						<input id="tab" class="input1" type="button" value="수정"><br>
						<input name="member_name" style="display: none;" class="re_input" 	type="text"> 
						<input style="display: none;" onclick="modify_button_event()" class="re_input1" value="수정하기">
						<input style="display: none;" class="re_input1" type="button"
							value="취소"> 
					</div>
					<br><br><br>
					<div>
						<span> 전화번호 &nbsp;&nbsp;${member.member_tel}</span>
							 <input id="tab" class="input2" type="button" value="수정"><br> 
							<input name="member_tel" style="display: none;" class="re_input2"	type="text"> 
							<input style="display: none;" onclick="modify_button_event()" class="re_input2_1" value="수정하기">
							<input style="display: none;" class="re_input2_1" type="button" value="취소"> 
					</div>
				</form>
				<br>
				<hr>
				<div class="memberOut">
					<br>
					<p>사적모임 사이트를 더이상 이용하고 싶지 않으신가요?</p>
					<div class="wrap_form2">
						<form method="get" id="logout_form" class="mypage_form2"
							action="${contextPath}/trip/logoutCheck.do">
							<input type="button" onclick="out_button_event()" value="로그아웃">
						</form>
						<form method="post" id="removeMember_form" class="mypage_form2"
							action="${contextPath}/trip/removeMember.do">
							<input type="hidden" name="member_id" value="${member.member_id}">
							<input type="button" onclick="del_button_event()" value="회원탈퇴">
						</form>
					</div>
				</div>
			</div>
		</section>
	</div>
	<%@ include file="footer.jsp"%>
</body>
<script>
	function modify_button_event() {
		if (confirm("수정 하시겠습니까?") == true) { //확인
			document.getElementById('modify_form').submit();
		} else { //취소
			return;
		}
	}

	function out_button_event() {
		if (confirm("로그아웃 하시겠습니까?") == true) { //확인
			document.getElementById('logout_form').submit();
		} else { //취소
			return;
		}
	}

	function del_button_event() {
		if (confirm("사적모임 페이지를 탈퇴하시겠습니까? 탈퇴한 회원은 복구되지 않습니다. ") == true) { //확인
			document.getElementById('removeMember_form').submit();
		} else { //취소
			return;
		}
	}
</script>

</html>