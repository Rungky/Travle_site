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
<title>사적모임 | 답변작성 페이지</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<link href="${contextPath}/resources/css/review.css" rel="stylesheet">
</head>
<body>
	<section>
        <div id="main_wrap">
            <div id="main">
                <div id="main_title">
                    <div class="margin_auto"><img class="review_logo"
                            src="${contextPath}/resources/image/logo-black.png" alt="로고"> </div>
                    <div style="margin-top: 5px;">
                        <div class="fs_m2 fw_6">문의에 답변을 남겨주세요!</div>
                        <div class="fs_s">
                            답변작성시 운영정책을 지켜주세요.<br>
                            친절하고 명확한 답변은 여기어때의 발전에 도움이 됩니다.
                        </div>
                        <hr>
                    </div>
                </div>
                <c:forEach var="question" items="${questionList}" varStatus="questionNum">
                <div id="main_contents">
                    <form id="review_form" action="${contextPath}/trip/adminreplyqna.do">
                    
                        <div class="review_title">
                            <!--데이터 받아와서 표시하기-->
                            <div class="fs_m2 fw_6">질문제목 : ${question.question_title}</div><hr>
                            <div class="fs_m2">${question.question_contents}</div>
                        </div> <br>
                    
                        <div class="review_contents">
                            <div class="fs_m fw_6" style="margin-top: 5px;">답변내용 작성</div>
                            <textarea class="input_contents" type="text" name="adminrecontent" placeholder="질문에 대한 답변을 남겨주세요!"></textarea>
                        </div>
                        <div class="margin_auto">
                            <br>
                            <input type="hidden" name="adminparentNO" value="${question.question_no}">
                            <input class="input_submit" type="submit" value="답변등록">
                            <input class="input_reset" type="reset" value="지우기">
                        </div>
                    </form>
                    <!-- <input class="input_close" type="button" value="취소하기"> -->
                </div>
               </c:forEach>
            </div>
        </div>
    </section>
    <!-- <script>
    $(".input_close").off("click").on("click", function(){
    	window.close();
    });
    </script> -->
</body>
</html>