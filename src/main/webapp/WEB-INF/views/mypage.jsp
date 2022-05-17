<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<c:set var="contextPath" value="${ pageContext.request.contextPath}" />
	
<!DOCTYPE html>
<html> 
<head>
<meta charset="UTF-8"> 
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My page</title>
<link rel="stylesheet" href="/project_trip/css/header_footer.css">
<link rel="stylesheet" href="/project_trip/css/mypage-style.css">
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

	<section>
		<div class="mypage_main">
			<div class="mypage_header">
				<div>
					<img src="https://image.goodchoice.kr/profile/ico/ico_25.png" alt="이미지">
				</div>
				<div>
					<h3>마이페이지</h3>
					<!--로그인한 회원 정보에 맞게 출력되야함-->
					<p>${member.member_id}</p>
					<br>
				</div>
			</div>
			<hr>

			<%-- 백단에서 조건걸어서 수정하기 --%>
			<form method="get" id="modify_name" class="mypage_form1" action="${contextPath}/trip">
				<span>닉네임 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${member.member_name}</span>
					  <input type="hidden" name="action" value="modify_name.do">
					<input id="tab"class="input1" type="button" value="수정"><br> 
					<input name="member_name"  style="display: none;" class="re_input" type="text" >
					 <input style="display: none;" class="re_input1" type="submit" value="수정하기">
					 <input style="display: none;" class="re_input1" type="button" value="취소">
					 <input type="hidden" name ="member_id" value="${member.member_id}">
			</form>
			<br>
			<form method="post" id="modify_pw" class="mypage_form1" action = "${contextPath}/trip">
                <span> 비밀번호 &nbsp;&nbsp;${member.member_pw}</span>
                <input id="tab" class="input2" type="button" value="수정"><br>
                <input name="member_pw"  style="display: none;" class="re_input2" type="text">
                <input style="display: none;" class="re_input2_1" type="submit" value="수정하기">
                <input style="display: none;" class="re_input2_1" type="button" value="취소">
                <input type="hidden" name ="member_id" value="${member.member_id}">
                 <input type="hidden" name="action" value="modify_pw.do">
                <br>
                <a href="trip?action=history.do">내가 예약한 숙소보기</a>
            </form>
			 <br>
            <hr>
            <div class="memberOut"><br>
                <p>사적모임 사이트를 더이상 이용하고 싶지 않으신가요?</p>
                <div class="wrap_form2">
                    <form method="get"   id="logout_form" class="mypage_form2" action = "${contextPath}/trip">
                        <input type="hidden" name ="member_id" value="${member.member_id}">
                        <input type="button" onclick="out_button_event()"  value="로그아웃">
                    	<input type="hidden" name="action" value="logout.do">
                    </form>
                    <form method="get"  id="removeMember_form"  class="mypage_form2" action = "${contextPath}/trip">
                        <input type="hidden" name ="member_id" value="${member.member_id}">
                        <input type="button" onclick="del_button_event()" value="회원탈퇴">
                        <input type="hidden" name="action" value="removeMember.do">
                    </form>
                </div>
            </div>
        </div>
	</section>
	<%@ include file="footer.jsp"%>
</body>
<script>

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