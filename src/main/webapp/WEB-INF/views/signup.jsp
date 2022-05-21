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
<link rel="stylesheet" href="${contextPath}/resources/css/signup.css">
<title>사적모임 | 회원가입</title>

<script>
function signup() {
	if (confirm("회원가입 완료!") == true) {
		document.getElementById('signup').submit();
	}
}
</script>

<script src="${contextPath}/resources/js/lib/jquery-3.6.0.js"></script>
<script src="${contextPath}/resources/js/signup.js"></script>
</head>
<body>
	<div class="wrap_top">
        <section>
	        <div class="signup_main">
				<a href="${contextPath}/trip/main.do">
					<img src="${contextPath}/resources/image/logo-black.png" alt="로고">
				</a>
	            <form action="${contextPath}/trip/signupCheck.do" method="post" name="f1" enctype="utf-8">
				<h2 style="text-align: center;">회원가입</h2>
				<h5>* 표시는 필수 입력 항목입니다.</h5>
	                <div class="signup_top">
                        <p><span class="red">* </span>이메일 아이디</p>
	                    <input type="text" name="member_id" id="id" class="email" placeholder="이메일 주소를 입력해주세요" />
	                    <div class="hide_1 red_1">유효한 이메일 주소를 입력해 주시기 바랍니다.</div>
	                </div>
	                <div class="signup_top">
                        <p><span class="red">* </span>비밀번호</p>
	                    <input type="password" name="member_pw" id="pw" placeholder="비밀번호를 입력해 주세요" />
	                    <div class="hide_1 red_1">유효한 비밀번호를 입력해 주시기 바랍니다.</div>
	               </div>
	               <div class="signup_top">
	                    <p><span class="red">* </span>비밀번호 확인</p>
	                    <input type="password" name="member_pw2" id="pw_2" placeholder="비밀번호를 다시 한번 입력해 주세요" />
	                    <div class="hide_1 red_1">비밀번호가 일치하지 않습니다.</div>
	               </div>    
	               <div class="signup_top">
                        <p><span class="red">* </span>닉네임</p>
	                    <input type="text" name="member_name" id="name_1" placeholder="닉네임을 입력해 주세요" />
	                    <div class="hide_1 red_1">유효한 닉네임을 입력해 주시기 바랍니다.</div>
	               </div>
	               <div class="signup_top">
                        <p><span class="red">* </span>전화번호</p>
	                    <input type="text" name="member_tel" id="tel_1" placeholder="전화번호를 입력해 주세요" />
	                    <div class="hide_1 red_1">유효한 전화번호를 입력해 주시기 바랍니다.</div>
	                </div>
	                <div>
	                    <input class="signup_btn_box" type="submit" id="signup" value="회원가입" onclick="signup()">
	                </div>
	            </form>
	        </div>
	    </section>
    </div>
</body>
</html>