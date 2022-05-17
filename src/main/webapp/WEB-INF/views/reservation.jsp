<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script>
	window.onload = function(){		  
		const resultElement = document.getElementById('result');
		
		var room_member = '${param.room_person}';
		    console.log(room_member);
		if (room_member != null && room_member != ''){
			  
			resultElement.innerText = room_member;
			document.getElementById('result2').value = room_member;
		}
	}

	function count(type)  {
	  // 결과를 표시할 element
	  const resultElement = document.getElementById('result');

	  
	  // 현재 화면에 표시된 값
	  let number = resultElement.innerText;
	  	  
		  if(type === 'plus') {
		    number = parseInt(number) + 1;
		  }else if(type === 'minus')  {
		    if(number == 1){
		    	number = 1;
		    } else if(number > 1){		    	
		    	number = parseInt(number) - 1;
		    }
		  }
	  
	  // 결과 출력
	  resultElement.innerText = number;
	  document.getElementById('result2').value = number;
	  
	  
	}
</script>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>목록 페이지</title>
    <link rel="stylesheet" href="css/reser.css">
    <link rel="stylesheet" href="./css/header_footer.css">
</head>

<body topmargin="0" bottommargin="0" leftmargin="0" rightmargin="0">
    <%@ include file="./header.jsp" %>

<section>
	<div style="height:15px">
	</div>
    <div class="container">
        <div class="con1">
        	<form method="get" action="${contextPath}/trip" >
	            <div class="ner1">
	            	<c:if test="${not empty param.dorm_category_no }">
            			<input type="hidden" name="dorm_category_no" value="${param.dorm_category_no}">
            		</c:if>
	            	<input type="hidden" name="action" value="reservation.do">
	                <h3>날짜</h3>
	                check In>> <input type="date" id="start" name="start" value="${date_s}" min="${date_s}"><br>
	                check Out>> <input type="date" id="end" name="end" value="${date_e}" min="${date_s}">
	            </div>
	            <div class="ner2">
	                <h3>상세조건</h3>
	                <div class="boxx">
	                	<input type="text" name="search" placeholder="숙소명 검색">
	                	<br><br>
	                	<c:choose>
	                		<c:when test="${empty param.dorm_category_no }">
			                	<a href="trip?action=reservation.do">
				                    <button type="button" class="bu re">초기화</button>
				                </a>
	                		</c:when>
	                		<c:otherwise>
	                			<a href="trip?action=reservation.do&dorm_category_no=${param.dorm_category_no}">
				                    <input type="button" class="bu re" value ="초기화">
				                </a>
	                		</c:otherwise>
	                	</c:choose>
	                    <button type="submit" class="bu che" onClick="compleBtn()">적용</button>
	                </div>
	            </div>
	            <div class="ner3">
	                <h3>인원</h3>
	                	<input type="button" class="p minus" value="-" onClick='count("minus")' />
	                	<span id="result">2</span>
	                	<input id="result2" type="hidden" name="room_person" value="2"/>
	                	<input type="button" class="p plus" value="+" onClick='count("plus")' />
	            </div>
	            <div class="ner4">
	                <div id="bom">
	                    <h3>객실 내 시설</h3>
	                </div>
	                <div>
	                   <ul style="list-style-type: none;">
	                        <li class="tum">
		                        <c:choose>
		                        	<c:when test="${empty param.wifi}">
			                            <input type="checkbox" id="check_1" class="choice" name="wifi" value="1">
			                            <label for="check_1" class="label_check">와이파이</label>
		                        	</c:when>
		                        	<c:otherwise>
		                        		<input type="checkbox" id="check_1" class="choice" name="wifi" value="1" checked>
			                            <label for="check_1" class="label_check">와이파이</label>
		                        	</c:otherwise>
		                        </c:choose>
	                        </li>
	                        <li  class="tum">
	                        	<c:choose>
		                        	<c:when test="${empty param.parking}">
		                        		<input type="checkbox" id="check_2" class="choice" name="parking" value="1">
	                            		<label for="check_2" class="label_check">주차장</label>
		                        	</c:when>
			                        <c:otherwise>
			                        	<input type="checkbox" id="check_2" class="choice" name="parking" value="1" checked>
	                            		<label for="check_2" class="label_check">주차장</label>
			                        </c:otherwise>
		                        </c:choose>
	                        </li>
	                        <li  class="tum">
	                        	<c:choose>
		                        	<c:when test="${empty param.aircon}">
		                    			<input type="checkbox" id="check_3" class="choice" name="aircon" value="1">
			                            <label for="check_3" class="label_check">에어컨</label>
		                        	</c:when>
			                        <c:otherwise>
			                            <input type="checkbox" id="check_3" class="choice" name="aircon" value="1" checked>
			                            <label for="check_3" class="label_check">에어컨</label>
			                        </c:otherwise>
		                        </c:choose>
	                        </li>
	                        <li  class="tum">
	                        	<c:choose>
		                        	<c:when test="${empty param.dryer}">
			                            <input type="checkbox" id="check_4" class="choice" name="dryer" value="1">
			                            <label for="check_4" class="label_check">드라이기</label>
		                        	</c:when>
			                        <c:otherwise>
			                        	<input type="checkbox" id="check_4" class="choice" name="dryer" value="1" checked>
			                            <label for="check_4" class="label_check">드라이기</label>
			                        </c:otherwise>
		                        </c:choose>
	                        </li>
	                        <li  class="tum">
	                        	<c:choose>
		                        	<c:when test="${empty param.port}">
			                        	<input type="checkbox" id="check_5" class="choice" name="port" value="1">
		                            	<label for="check_5" class="label_check">커피포트</label>
		                        	</c:when>
			                        <c:otherwise>
			                            <input type="checkbox" id="check_5" class="choice" name="port" value="1" checked>
			                            <label for="check_5" class="label_check">커피포트</label>
			                        </c:otherwise>
		                        </c:choose>
	                        </li>
	                   </ul>
	                </div>
	                
	            </div>

	            <div class="ner5">
	                <h3>가격</h3>
	                <div class="dropdown">
	                    <select name="price" class="dropbtn">
	                    	<option value="5">-------선택-------</option>
	                    	<c:choose>
	                    		<c:when test="${param.price eq 1}">
	                    			<option value="1" selected>5만원이하</option>
			                    	<option value="2">5만원~10만원이하</option>
			                    	<option value="3">10만원~20만원이하</option>
			                    	<option value="4">20만원~</option>
	                    		</c:when>
	                    		<c:when test="${param.price eq 2}">
	                    			<option value="1">5만원이하</option>
			                    	<option value="2" selected>5만원~10만원이하</option>
			                    	<option value="3">10만원~20만원이하</option>
			                    	<option value="4">20만원~</option>
	                    		</c:when>
	                    		<c:when test="${param.price eq 3}">
	                    			<option value="1">5만원이하</option>
			                    	<option value="2">5만원~10만원이하</option>
			                    	<option value="3" selected>10만원~20만원이하</option>
			                    	<option value="4">20만원~</option>
	                    		</c:when>
	                    		<c:when test="${param.price eq 4}">
									<option value="1">5만원이하</option>
			                    	<option value="2">5만원~10만원이하</option>
			                    	<option value="3">10만원~20만원이하</option>
			                    	<option value="4" selected>20만원~</option>
	                    		</c:when>
	                    		<c:otherwise>
			                    	<option value="1">5만원이하</option>
			                    	<option value="2">5만원~10만원이하</option>
			                    	<option value="3">10만원~20만원이하</option>
			                    	<option value="4">20만원~</option>
	                    		</c:otherwise>
	                    	</c:choose>
	                    </select>
					 </div>
	            </div>
            </form>
        </div>
        <div class="con2">
            <div class="order"> 
            	<form method="post" action="${contextPath}/trip" >
            		<c:if test="${not empty param.dorm_category_no }">
            			<input type="hidden" name="dorm_category_no" value="${param.dorm_category_no}">
            		</c:if>
	            	<input type="hidden" name="action" value="reservation.do">
	            	<c:if test="${not empty param.start}">
	            		<input type="hidden" name="start" value="${param.start}">
	            	</c:if>
	            	<c:if test="${not empty param.end}">
	            		<input type="hidden" name="end" value="${param.end}">
	            	</c:if>
	            	<c:if test="${not empty param.wifi}">
	            		<input type="hidden" name="wifi" value="${param.wifi}">
	            	</c:if>
	            	<c:if test="${not empty param.parking}">
	            		<input type="hidden" name="parking" value="${param.parking}">
	            	</c:if>
	            	<c:if test="${not empty param.aircon}">
	            		<input type="hidden" name="aircon" value="${param.aircon}">
	            	</c:if>
	            	<c:if test="${not empty param.dryer}">
	            		<input type="hidden" name="dryer" value="${param.dryer}">
	            	</c:if>
	            	<c:if test="${not empty param.port}">
	            		<input type="hidden" name="port" value="${param.port}">
	            	</c:if>
	            	<c:if test="${not empty param.room_person}">
	            		<input type="hidden" name="room_person" value="${param.room_person}">
	            	</c:if>
	            	<c:if test="${not empty param.price}">
	            		<input type="hidden" name="price" value="${param.price}">
	            	</c:if>
    	            <button type="submit" class="button button4" name="order" value="1">낮은 가격 순</button>
	                <button type="submit" class="button button4" name="order" value="2">높은 가격 순</button>
                </form>
            </div>
        
            <div class="towroom">
				<c:choose>
					<c:when test="${not empty dormList }">
		            	<c:forEach var="i" items="${dormList }" step="1" >
		               		<a href="trip?action=detail.do&dormno=${i.dorm_no }&reserve_checkin=${date_s}&reserve_checkout=${date_e}">
		                    <div class="romm" style="background-image:linear-gradient( rgba(0, 0, 0, 0), rgba(0, 0, 0.5, 0.8) ),url(./image/dorm/${i.getDorm_picture()})">
		                        <div class="ggumim">
		                            <p>
		                                <strong>
		                                    <h3 class="font1">${i.dorm_name }</h3>
		                                </strong>
		                            </p>
		                            <p>
		                                <span class="font1">리뷰(<span class="font1">${i.count_reserve_no}</span>)</span>
		                            </p>
		                            <p>
		                                <strong class="font1 addr">${i.dorm_addr}</strong>
		                            </p>
		                        </div>
		                        <div class="price">
		                            <p><strong>
		                                <h3 class="font1">${i.min_pay_night}원</h3>
		                            </strong></p>
		                        </div>
		                    </div>
		                    </a>
						</c:forEach>
                    </c:when>
                    <c:otherwise>
                    	<h3 style="text-align:center">현재 조건에 맞는 숙소가 없습니다.</h3>
                    </c:otherwise>
				</c:choose>
			
            </div>
       </div>

    </div>

</section>
<%@ include file="./footer.jsp" %>
</body>
</html>