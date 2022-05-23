<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
    import="java.sql.*"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath }" />

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

<script>

	window.onload = function(){
		const resultElement = document.getElementById('result');
		
		var room_member = '${param.room_person}';
		    console.log(room_member);
		if (room_member != null && room_member != ''){
			  
			resultElement.innerText = room_member;
			document.getElementById('result2').value = room_member;
		}
		
		$("#sub_btn").off("click").on("click", function(){
			var start = $("#start").val();
			var startArr = start.split('-');
			var startCompare = new Date(startArr[0], parseInt(startArr[1])-1, startArr[2]);
			
			var end = $("#end").val();
			var endArr = end.split('-');
			var endCompare = new Date(endArr[0], parseInt(endArr[1])-1, endArr[2]);
			
			
			if(startCompare.getTime() > endCompare.getTime()){
				alert("날짜 오류");

				return false;
			}

			
			
		})
			if(${opt_wifi}==1){
				$("#check_1").prop("checked", true);
			}
			if(${opt_parking}==1){
				$("#check_2").prop("checked", true);
			}
			if(${opt_aircon}==1){
				$("#check_3").prop("checked", true);
			}
			if(${opt_dryer}==1){
				$("#check_4").prop("checked", true);
			}
			if(${opt_port}==1){
				$("#check_5").prop("checked", true);
			}

			let sb = ${param.price} + 0;
			if(sb != 0){
				$("#select_box").val(sb).prop("selected", true);
			}
			
			$("#search_box").val(${param.search});
			
			var getCookie = function(name) {
				var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
				return value? value[2] : null;
			}
			
			// 특정 이름을 가진 쿠키의 value를 변수에 담음
			var dormCookie = getCookie("dormno");
			var picCookie = getCookie("pic");
			
			// 쿠키의 value 값을 배열로 변환
			var dormno = dormCookie.split(",");
			var pic = picCookie.split(",");
			
			console.log(dormno);
			console.log(pic);
			
			
			
			var Arr = [];
			console.log("dormno.length:" + dormno.length);
			if(dormno.length > 0){	
				for(var i=0; i<dormno.length;i++){					
					Arr.push(dormno[i]);
					Arr.push(pic[i]);
				}
			}
			console.log(Arr.length);
			if(Arr.length != 0){
				for(var i =0; i<Arr.length; i+=2){
					if(i<5*2){
					console.log(i)
						var html = "";
						html += "<tr>";
						html += "	<td>";
						html += "		<a href='detail.do?dormno="+Arr[i]+"&reserve_checkin=${date_s}&reserve_checkout=${date_e}'>";
						html += "		<img class='img_sz' src='${pageContext.request.contextPath}/resources/image/dorm/"+Arr[i+1]+"'>";
						html += "		</a>";
						html += "	</td>";
						html += "</tr>";
						
						$("#listbody").append(html);
					}
					
				}
			}
 			if($("#tb_list > tbody tr").length>5){
				$("#tb_list > tbody").find("tr:first-child").remove();
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
    <title>사적모임 | 목록 페이지</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/reser.css" >
    <link rel="stylesheet" href="${contextPath}/resources/css/header_footer.css">
</head>

<body>
    <%@ include file="./header.jsp" %>
    <br>
<div class="clear" style="position:relative;">
	<div class="order">
		<div class="or_l" style="float:left; line-height:50px; margin-left:20px; font-size:16px;">
		총 ${dormsList.size()} 개의 숙소가 검색되었습니다.
		</div>
		<div></div>
		<div class="or_r">
			<form method="get" action="${contextPath}/trip/reservation.do" >
			    <c:if test="${not empty param.dorm_category_no }">
			    	<input type="hidden" name="dorm_category_no" value="${param.dorm_category_no}">
			    </c:if>
				<c:if test="${not empty param.date_s}">
					<input type="hidden" name="date_s" value="${param.date_s}">
				</c:if>
				<c:if test="${not empty param.date_e}">
					<input type="hidden" name="date_e" value="${param.date_e}">
				</c:if>
				<c:if test="${not empty param.opt_wifi}">
					<input type="hidden" name="opt_wifi" value="${param.opt_wifi}">
				</c:if>
				<c:if test="${not empty param.opt_parking}">
					<input type="hidden" name="opt_parking" value="${param.opt_parking}">
				</c:if>
				<c:if test="${not empty param.opt_aircon}">
					<input type="hidden" name="opt_aircon" value="${param.opt_aircon}">
				</c:if>
				<c:if test="${not empty param.opt_dryer}">
					<input type="hidden" name="opt_dryer" value="${param.opt_dryer}">
				</c:if>
				<c:if test="${not empty param.opt_port}">
					<input type="hidden" name="opt_port" value="${param.opt_port}">
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
	</div>
</div>
</div>
<section>
    <div class="container">
       <div class="con1">
        	<form method="get" action="${contextPath}/trip/reservation.do" >
	            <div class="ner1">
	            	<c:if test="${not empty param.dorm_category_no }">
            			<input type="hidden" name="dorm_category_no" value="${param.dorm_category_no}">
            		</c:if>
	                <h3>날짜</h3>
	                check In>> <input type="date" id="start" name="date_s" value="${date_s}" min="${today}"><br>
	                check Out>> <input type="date" id="end" name="date_e" value="${date_e}" min="${today}">
	            </div>
	            <div class="ner2">
	                <h3>상세조건</h3>
	                <div class="boxx">
	                	<input type="text" id="search_box" name="search" placeholder="숙소명 검색">
	                	<br><br>
	                	<c:choose>
	                		<c:when test="${empty param.dorm_category_no }">
			                	<a href="${contextPath}/trip/reservation.do">
				                    <button type="button" class="bu re">초기화</button>
				                </a>
	                		</c:when>
	                		<c:otherwise>
	                			<a href=${contextPath}/trip/reservation.do?dorm_category_no=${param.dorm_category_no}">
				                    <input type="button" class="bu re" value ="초기화">
				                </a>
	                		</c:otherwise>
	                	</c:choose>
	                    &nbsp&nbsp<button type="submit" id="sub_btn" class="bu che">적용</button>
	                </div>
	            </div>
	            <br><br>
	            <div class="ner3">
	                <h3>인원</h3>
	                	<input type="button" class="p minus" value="-" onClick='count("minus")' />
	                	<span id="result">2</span>
	                	<input id="result2" type="hidden" name="room_person" value="2"/>
	                	<input type="button" class="p plus" value="+" onClick='count("plus")' />
	            </div>
	            <br>
	            <div class="ner4">
	                <div id="bom">
	                    <h3>객실 내 시설</h3>
	                </div>
	                <div>
	                   <ul style="list-style-type: none;">
	                        <li class="tum">
			                	<input type="checkbox" id="check_1" class="choice" name="opt_wifi" value="1">
			                    <label for="check_1" class="label_check">와이파이</label>
	                        </li>
	                        <li  class="tum">
		                        <input type="checkbox" id="check_2" class="choice" name="opt_parking" value="1">
	                            <label for="check_2" class="label_check">주차장</label>
	                        </li>
	                        <li  class="tum">
		                    	<input type="checkbox" id="check_3" class="choice" name="opt_aircon" value="1">
			                    <label for="check_3" class="label_check">에어컨</label>
	                        </li>
	                        <li  class="tum">
			                    <input type="checkbox" id="check_4" class="choice" name="opt_dryer" value="1">
			                    <label for="check_4" class="label_check">드라이기</label>
	                        </li>
	                        <li  class="tum">
			                    <input type="checkbox" id="check_5" class="choice" name="opt_port" value="1">
		                        <label for="check_5" class="label_check">커피포트</label>
	                        </li>
	                   </ul>
	                </div>
	                
	            </div>
				<br><br>
	            <div class="ner5">
	                <h3>가격</h3>
	                <div class="dropdown">
	                    <select id="select_box" name="price" class="dropbtn">
	                    	<option value="5">-------선택-------</option>
	                    	<option value="1">5만원이하</option>
			                <option value="2">5만원~10만원이하</option>
			                <option value="3">10만원~20만원이하</option>
			                <option value="4">20만원~</option>
	                    </select>
					 </div>
	            </div>
            </form>
        </div>
        <div></div>
        <div class="con2">
        
            <div class="towroom">
				<c:choose>
					<c:when test="${not empty dormsList }">
		            	<c:forEach var="i" items="${dormsList }" step="1" >
		               		<a href="detail.do?dormno=${i.dorm_no}&reserve_checkin=${date_s}&reserve_checkout=${date_e}">
		                    <div class="romm" style="background-image:linear-gradient( rgba(0, 0, 0, 0), rgba(0, 0, 0.5, 0.8) ),url(${contextPath}/resources/image/dorm/${i.getDorm_picture()})">
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
	    <div>
		    <div style="margin-left:10px;border:solid 1px rgb(230,230,230);text-align:center;">
			<div>Quick Menu</div>
			<div style="background-color:orange;color:white; width:100px; height:30px; font-size:bold; vertical-align:center;">최근본숙소</div>
			<div style="width:100px;">
				<table id="tb_list">
					<tbody id="listbody">
					
					</tbody>
				</table>
			</div>
	    </div>
	</div>
</section>
<%@ include file="./footer.jsp" %>
</body>
</html>