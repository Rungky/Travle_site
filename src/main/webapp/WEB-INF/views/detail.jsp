<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>사적모임 | details</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/detail.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header_footer.css">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script>
	window.onload= function(){
		$("#map").css("display","none");
	}
	if(${!empty dateerror}){
		alert("날짜가 맞지않아 오늘 값으로 되었습니다.");
	}

	var getCookie = function(name) {
		var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
		return value? value[2] : null;
	};
	
	var setCookie = function(name, value, exp) {
		var date = new Date();
		date.setTime(date.getTime() + exp*24*30*60*1000);
		document.cookie = name + '=' + value + ';expires=' + date.toUTCString() + ';path=/';
	};
	
	var dormno = "${param.dormno}";
	var pic = "${dormdto.dorm_picture}";
	
	// 특정 이름을 가진 쿠키의 value를 변수에 담음
	var dormCookie = getCookie("dormno");
	var picCookie = getCookie("pic");

	var dom; 
	var pi;
	
	// 기존 쿠키가 있을 경우 배열로 변환하여 변수에 담음
	var domList = [];
	var picList= [];
	if(dormCookie != null){
		dom = dormCookie.split(",");
		pi = picCookie.split(",");
		for(var i=0;i<dom.length;i++){	
			domList.push(dom[i]);
			picList.push(pi[i]);
			if(dom[i]==dormno){
				domList	.splice(i,1);
				picList.splice(i,1);
			}
		}
		console.log("domList:" +  domList.length);
		if(domList.length>=5){
			domList.splice(0,dom.length-4);
			picList.splice(0,dom.length-4);
		}
	}
	domList.push(dormno);
	picList.push(pic);
	
	// 배열을 다시 String 값으로 변환하여 cookie의 value값에 넣어줌
	var domm = domList.join(",");
	var pict = picList.join(",");
	
	setCookie("dormno", domm, 1);
	setCookie("pic", pict, 1);
	


</script>
</head>
<body>
    <%@ include file="./header.jsp" %>
    <section>
        <div class="center">
            <div class="st">
                <aside>
                    <img class="image" src="${pageContext.request.contextPath}/resources/image/dorm/${dormdto.dorm_picture }"> 
                	<div id="map" style="margin-left:100px; width:300px;height:250px;"></div>
                </aside>
                <article>
                    <div class="dormtt">
                        <h2>${dormdto.dorm_name }</h2>
                    </div>
                    <div class="inf">
                    	<div>
                    		<span class="avr">${dormdto.scoreAvr}</span>리뷰 ${dormdto.review_count}개
                    	</div>
                    	<div>
                    		<span>추천 <span data-likecnt="${dormdto.like_cnt }" id="like_cnt">${dormdto.like_cnt }</span>개
	                    		<c:choose>
	                    		<c:when test="${like_tg}">
	                    			<button id="like" data-img="1" class="likebt">💙</button>
	                    		</c:when>
	                    		<c:otherwise>
	                    			<button id="like" data-img="0" class="likebt">🤍</button>
	                    		</c:otherwise>
	                    		</c:choose>                    		
                    		</span>
                    	</div>
                    </div>
                    <br>
                    <div class="dormaddr">
                        ${dormdto.dorm_addr }
                    </div>
                    <br>
                   	<div class="off" style="display: -webkit-box;">
                    	<div class="top">
                    		<c:forTokens var="item2" items="${dormdto.dorm_contents }" begin="0" end="0" delims=",">
								${item2}
							</c:forTokens>
							<button onclick="drop_contents()"class="onbutton">더보기</button>
						</div>
						<ul>
							<c:forTokens var="item" items="${dormdto.dorm_contents }" begin="1" delims=",">
								<li>${item}</li>
							</c:forTokens>
						</ul>
                        취소 및 환불 규정<br>
                        <ul>
                            <li>체크인일 기준 7일 전 : 100% 환불</li>
                            <li>체크인일 기준 4~6일 전 : 50% 환불</li>
                            <li>체크인일 기준 3일 전 ~ 당일 및 : 환불 불가</li>
                            <li>취소, 환불시 수수료가 발생할 수 있습니다.</li>
                        </ul>
                    </div>
                </article>
            </div>
        </div>
    </section>
    <div>
	<!-- 지도 script -->
		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5379b3b7db21ac1cccc9bb03d7433c9c&libraries=services,clusterer,drawing"></script>
		<script>
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		        level: 4 // 지도의 확대 레벨
		    };  
		
		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption); 
		
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();
		
		// 주소로 좌표를 검색합니다
		geocoder.addressSearch('${dormdto.dorm_addr } ${dormdto.dorm_name }', function(result, status) {
	
	    // 정상적으로 검색이 완료됐으면 
		     if (status === kakao.maps.services.Status.OK) {
		
		        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		
		        // 결과값으로 받은 위치를 마커로 표시합니다
		        var marker = new kakao.maps.Marker({
		            map: map,
		            position: coords
		        });
		
		        // 인포윈도우로 장소에 대한 설명을 표시합니다
		        var infowindow = new kakao.maps.InfoWindow({
		            content: '<div style="width:150px;text-align:center;padding:6px 0;">${dormdto.dorm_name }</div>'
		        });
		        infowindow.open(map, marker);
		
		        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		        map.setCenter(coords);
		    } 
	    });    
		</script>
    
    </div>
   
    <div id="cancel" onclick="cancel()" class="hiddenbt" style="display:none">
		&nbsp;	
	</div>
    <section>
        <div class="center">
            <div class="st2">
                <input id="tab1" type="radio" name="tabs" checked>
                <label for="tab1">객실 안내/예약</label>
                <input id="tab2" type="radio" name="tabs">
                <label for="tab2">리뷰</label>
			 	<form  id="tb1" class="calender_box" action="detail.do">
			 		<input  class="calender" type="date" name="reserve_checkin" min="${checkin}" value="${checkin}">
			 		<span style="font-size:30px;">~</span>
			 		<input class="calender" type="date" name="reserve_checkout" min="${tomorrow}" value="${checkout}">
			 		<button class="datebt">적용</button>
			 		<input type="hidden" name="dormno" value="${dormdto.dorm_no}">
			 	</form>
	                <c:forEach var="item" items="${roomsList }">
	                <c:choose>
	                <c:when test="${0 == item.room_pay_day }"> <!-- 대실 금액이 눌이아니면 둘다 표시 눌이면 하나만 표시로 두개 나눠서 조건에 맞게 출력 int라서 0이 눌 -->
		                <table id="tb1">
		                    <tr>
		                        <td rowspan="4"><img class="image2" src="${pageContext.request.contextPath}/resources/image/room/${item.room_picture }"></td>
		                        <td colspan="2" class="ti">${item.room_name }</td>
		                        
		                    </tr>
		                    <tr>
		                        <td style="font-weight:bold;">가격</td>
		                        <td class="pr">${item.room_pay_night }원</td>
		                    </tr>
		                    <tr>
		                    	<td colspan="2" class="bd-top bdbt">
		                    		<button id="roombt">객실 상세</button>
		                    	</td>
		                    	<td class="roomdetail" style="display :none;">
		                    		<div class="right pd" onclick="cancel()">
		                    			<button class="closebt">X</button>
		                    		</div>
		                    		<ul>
		                        		<c:forTokens var="item2" items="${item.room_contents }" delims=",">
	                            			<li>${item2}</li>
	                            		</c:forTokens>
	                        		</ul> 
		                    	</td>
		                    </tr>
		                    <tr>
		                        <td class="bdbt" colspan="2">
		                        	<c:if test="${item.reserved == 0}">
		                            <form action="page8.do">
		                                <button class="rsv2">예약</button>
		                                <input type="hidden" name="roomno" value="${item.room_no}">
		                                <input type="hidden" name="roomname" value="${item.room_name}">
		                                <input type="hidden" name="dormno" value="${dormdto.dorm_no}">
		                                <input type="hidden" name="dormname" value="${dormdto.dorm_name}">
		                                <input type="hidden" name="reserve_pay" value="${roomday * item.room_pay_night}">
		                                <input type="hidden" name="reserve_checkin" value="${param.reserve_checkin}">
		                                <input type="hidden" name="reserve_checkout" value="${param.reserve_checkout}">
		                            </form>
		                            </c:if>
		                            <c:if test="${item.reserved == 1}">
		                            	<div class="rsv2-2">판매 완료</div>
		                            </c:if>
		                        </td>
		                    </tr>
		                </table>
	                </c:when>
	                <c:otherwise>
		                <table id="tb1">
		                    <tr>
		                        <td rowspan="4"><img class="image2" src="${pageContext.request.contextPath}/resources/image/room/${item.room_picture }"></td>
                        		<td colspan="2" class="ti">${item.room_name }</td>
		                    </tr>
		                    <tr>
		                    	<td><span class="wd" style="font-weight:bold;">대실</span></td>
                        		<td><span class="wd" style="font-weight:bold;">숙박</span></td>
		                    </tr>
		                    <tr>
		                        <td>
			                        <div class="wd right">
				                        <span class="pr">${item.room_pay_night }원</span>
			                        </div>
		                        </td>
		                        <td>
		                        	<div class="wd right">
			                       	 	<span class="pr">${item.room_pay_night }원</span>
			                        </div>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td class="bdbt">
		                            <form class="wd" action="">
		                                <button class="rsv2" name="action" value="page8.do">예약</button>
		                                <input type="hidden" name="roomno" value="${item.room_no}">
		                                <input type="hidden" name="roomname" value="${item.room_name}">
		                                <input type="hidden" name="dormno" value="${dormdto.dorm_no}">
		                                <input type="hidden" name="dormname" value="${dormdto.dorm_name}">
		                                <input type="hidden" name="reserve_pay" value="${item.room_pay_day}">
		                                <input type="hidden" name="reserve_checkin" value="${param.reserve_checkin}">
		                                <input type="hidden" name="reserve_checkout" value="${param.reserve_checkout}">
		                            </form>
		                        </td>
		                        <td class="bdbt">
		                            <form class="wd" action="">
		                                <button class="rsv2" name="action" value="page8.do">예약</button>
		                                <input type="hidden" name="roomno" value="${item.room_no}">
		                                <input type="hidden" name="roomname" value="${item.room_name}">
		                                <input type="hidden" name="dormno" value="${dormdto.dorm_no}">
		                                <input type="hidden" name="dormname" value="${dormdto.dorm_name}">
		                                <input type="hidden" name="reserve_pay" value="${roomday * item.room_pay_night}">
		                                <input type="hidden" name="reserve_checkin" value="${param.reserve_checkin}">
		                                <input type="hidden" name="reserve_checkout" value="${param.reserve_checkout}">
		                            </form>
		                        </td>
		                    </tr>
		                </table>
	                </c:otherwise> 
	                </c:choose>
	                </c:forEach>
                <c:forEach var="item" items="${reviewsList}">       
	                <table id="tb2">
	                    <tr>
	                        <td colspan="2" class="title">${item.review_title}</td>
	                    </tr>
						
	                    <tr>
	                        <td class="star<fmt:formatNumber type="number" maxFractionDigits="0"  value="${item.review_score}" />"><span class="score">${item.review_score}</span></td>
	                        <td></td>
	                    </tr>
	                    <tr>
	                        <td colspan="2" class="rev">${item.member_id}</td>
	                    </tr>
	                    <tr>
	                        <td colspan="2">${item.review_contents}</td>
	                    </tr>
	                    <tr>
	                        <td colspan="2" class="date">${item.review_date}</td>
	                    </tr>
	                    <c:if test="${item.review_picture != \"none\"}">
	                    <tr>
	                    	<td>
		                  		<img class="review_img" src="${pageContext.request.contextPath}/resources/review/${item.review_picture}">
		                	</td>
	                	</tr>
	                    </c:if>
	                </table>
                </c:forEach>   
            </div>
        </div>
    </section>
    <%@ include file="./footer.jsp" %>
</body>
<script>
	var likebt = document.getElementById("like");
	var id = "${id}";
	var like_tg = ${like_tg};
	var like_cnt = document.getElementById("like_cnt");
	likebt.addEventListener("click", function(){
		if( id=="" ){		
			let result = confirm("로그인하고 이용 가능합니다. 로그인 하시겠습니까?");
			if(result){
				location.href= "${pageContext.request.contextPath}/trip/loginForm.do";
			}
		} else {
				let xhr = new XMLHttpRequest();
				xhr.open('GET', '${pageContext.request.contextPath}/trip/like.do?dormno='+${dormdto.dorm_no}+'&like='+like_tg);
				xhr.send()
				xhr.onload = function (e) {
					let data_text = xhr.responseText;
					let data = JSON.parse(data_text);
					like_tg = data.param;
				}
				if(likebt.getAttribute("data-img") ==1) {
					likebt.innerHTML = "🤍";
					likebt.setAttribute("data-img", "0");
					like_cnt.setAttribute("data-likecnt", (Number(like_cnt.getAttribute("data-likecnt"))-1));
					like_cnt.innerHTML = like_cnt.getAttribute("data-likecnt");
				}else {
					likebt.innerHTML = "💙";
					likebt.setAttribute("data-img", "1");
					like_cnt.setAttribute("data-likecnt", (Number(like_cnt.getAttribute("data-likecnt"))+1));
					like_cnt.innerHTML = like_cnt.getAttribute("data-likecnt");
				}
		}
	});
	
	function drop_contents() {
		let click = document.getElementsByClassName("onbutton");
		let contents = document.getElementsByClassName("off");
		if (contents[0].style.display == ("-webkit-box")) {
			click[0].innerHTML = "접기";
			contents[0].style.display = "block";
			$("#map").css("display","");
		} else {
			click[0].innerHTML = "더보기";
			contents[0].style.display = "-webkit-box";
			$("#map").css("display","none");
		}
	}
	
	var roomdetail_list = document.getElementsByClassName("roomdetail");
	var roombt_list = document.querySelectorAll("#roombt");
	var hiddenbt = document.getElementsByClassName("hiddenbt");
	for (let i = 0; i < roombt_list.length; i++) {
		roombt_list[i].addEventListener("click", function () {
	        if (hiddenbt[0].style.display == ("none")) {
	        	roomdetail_list[i].style.display = "block";
	            hiddenbt[0].style.display = "block";
	        }
	    });
	}
	function cancel() {
		for(let j = 0;j<roomdetail_list.length;j++) {
			if(roomdetail_list[j].style.display == ("block")){
				roomdetail_list[j].style.display = "none";
				hiddenbt[0].style.display = "none";
			}
		}  
	}
</script>
</html>