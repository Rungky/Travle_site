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
<title>로그인</title>

<script>
function loginChk() {
    var form = document.f1;
    if (!form.member_id.value) {
        alert("아이디를 입력해 주십시오. ");
        form.member_id.focus();
        return;
    }
 
    if (!form.member_pw.value) {
        alert("비밀번호를 입력해 주십시오.");
        form.member_pw.focus(); 
        return;
    }
}
</script>

</head>
<body>
	<div class="wrap_top">
        <section>
	        <div class="login_main">
	            <a href="${contextPath}/trip">
					<img src="${contextPath}/resources/image/logo-black.png" alt="로고">
				</a><p></p>
	            <form action="${ contextPath}/trip/loginCheck.do" method="post" name="f1">
	                <div class="login_top">
	                    <input type="text" name="member_id" placeholder="✉️아이디" />
	                    <input type="password" name="member_pw" placeholder="🔒비밀번호" />
	                </div>
	                <div>
	                    <input class="login_btn_box" type="submit" value="로그인" onclick="loginChk()"/>
	                </div>
	            </form>
                <p>
                    <a href="${contextPath}/trip/idFind.do">|아이디 찾기|</a>
                    <a href="${contextPath}/trip/pwFind.do">|비밀번호 찾기|</a>
                    <a href="${contextPath}/trip/signup.do">|회원가입|</a>
                </p>
	        </div>
	    </section>
    </div>
</body>
</html>