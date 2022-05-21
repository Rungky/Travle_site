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
<link rel="stylesheet" href="${contextPath}/resources/css/login.css">
<title>사적모임 | 비밀번호 찾기 결과</title>

<script>
function newPw() {
	if (confirm("비밀번호 변경이 완료되었습니다. 로그인 페이지로 이동합니다.") == true) {
		document.getElementById('newPw').submit();
	}
}
</script>

<script src="${contextPath}/resources/js/lib/jquery-3.6.0.js"></script>
<script src="${contextPath}/resources/js/pwFind.js"></script>
</head>
<body>
	<div class="wrap_top">
        <section>
	        <div class="login_main">
	            <a href="#">
					<img src="${contextPath}/resources/image/logo-black.png" alt="로고">
				</a>
				<h2 style="text-align: center;">새 비밀번호 설정하기</h2>
				
				<c:if test = "${!empty member_pw}">
	            	<form action="${ contextPath}/trip/newPw.do" id="newPw" method="post" name="f1">
		                <div class="login_top">
		                    <input type="password" id="pw" name="member_pw" placeholder="새 비밀번호" />
		                   	<input type="password" id="pw_2" name="member_pwCheck" placeholder="새 비밀번호 확인" />
		                   	<div class="hide_1 red_1">비밀번호가 일치하지 않습니다.</div>
		            	</div>
		                <div>
		                    <input class="login_btn_box" id="change_btn" type="submit" value="비밀번호 변경하기" onclick="newPw()"/>
		                </div>
	            	</form>
		         </c:if>
		         <c:if test = "${empty member_pw}">
		         	<div class="login_top">
		            	<p>존재하지 않는 정보입니다.</p>
		            </div>
		         </c:if>
		         <div>
	                <input class="back" type="reset" value="로그인 화면으로 돌아가기" onclick="location.href='${contextPath}/trip/login.do'"/>
	            </div>
	        </div>     
	    </section>
    </div>
</body>

</html>