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
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>사적모임 | Question page</title>
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <link rel="stylesheet" href="${contextPath}/resources/css/questionWrite-style.css">
</head>
<body>
    <section>
        <div>
            <div class="question_head">
                <h1>새 문의 작성</h1>
            </div>
            <hr><br>
            <div class="question_text">
                <p>이용 중 불편하신 점을 문의주시면 최대한 빠른 시일내에 답변 드리겠습니다.</p>
            </div>
            <c:forEach var="question" items="${questionList}" varStatus="questionNum">
            <div class="question_input">
                <form action="${contextPath}/trip/modqna.do">
                    <p>문의 제목</p>
                    <input class="question_input_title" type="text" placeholder="제목을 입력해주세요." name="title" value="${question.question_title}">
                    <p>문의 내용</p>
                    <textarea placeholder="내용을 입력해주세요." name="content">${question.question_contents}</textarea><br>
                    <input type="hidden" name="questionNO" value="${question.question_no}">
                    <div class="bts">
	                    <input class="question_input_btn" type="submit" value="수정하기"><input class="question_input_btn" type="button" value="지우기">
                    </div>
                </form>
                <!-- <div><input class="input_close" type="button" value="취소하기"></div>    --> 
            </div>
            </c:forEach>
        </div>
    </section>
    <!--  <script>
    $(".input_close").off("click").on("click", function(){
    	window.close();
    });
    </script>  -->
</body>
</html>