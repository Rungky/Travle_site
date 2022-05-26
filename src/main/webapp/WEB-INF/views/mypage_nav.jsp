<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<nav id="mypage_nav">
			<div>
				<ul class="nav_menu">
					<li class="fw_b" style="font-size: 1.1em; color: black;">사적모임✈️</li>
					<br>
					<li><a href="${contextPath}/trip/mypage.do">마이페이지</a></li>
					<c:if test="${member.member_authority eq 'admin'}">
						<li id="admin"><a href="${contextPath}/trip/admin.do">관리자</a></li>
					</c:if>
					<li><a href="${contextPath}/trip/myLike.do">내 관심숙소</a></li>
					<li><a href="${contextPath}/trip/history.do">내 예약내역</a></li>
					<li><a href="${contextPath}/trip/qna.do">Q&A</a></li>
				</ul>
			</div>
		</nav>
		
		<!-- 해당 페이지 nav에 언더라인 표시  -->
		<script>
			let a = $(".nav_menu li a");
			let url = "http://final_trip/trip/myLike.do"; 
			url = "http://final_trip/trip/mypage.jsp"; 
			url = "${URL}";
			
			for(let i=0; i<a.length; i++){
				let full_url = $(a[i]).attr("href");
				console.log(full_url);
				let list_splitUrl = full_url.split("/");
				let final_url = list_splitUrl[list_splitUrl.length-1];

				let ffinal_url = final_url.split(".")[0];
				console.log("final_url: "+final_url);
				if(url.includes(ffinal_url)){
					//$(a[i]).addClass("underline");
					$($(a[i]).parent()).addClass("underline");
				}else{
					$($(a[i]).parent()).removeClass("underline");
				}	
			}
		</script>
		
