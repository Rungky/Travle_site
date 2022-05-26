<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>예약페이지</title>
<link rel="stylesheet"
	href="${contextPath}/resources/css/header_footer.css">
<link rel="stylesheet" href="${contextPath}/resources/css/page8.css">

<title>사적모임 | 예약페이지</title>
<link rel="stylesheet" href="${contextPath}/resources/css/header_footer.css">
<link rel="stylesheet" href="${contextPath}/resources/css/page8.css" >

<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>
<script>

	

	$(function(){
		bind();
		event();
	});
	
	function bind(){
		
		
		jQuery("#check_all").off("click").on("click", function(){
			
			
			if($("#check_all").prop("checked")){
				$(this).parent().find('input').prop("checked", true);	
			} else {
				$(this).parent().find('input').prop("checked", false);			
			}
			
		});
		
		$("#btn_pay").off("click").on("click", function(){
			
			Checkform2();
			
			if(!$(".one").prop("checked")){
				alert("동의 항목을 확인해주세요!");
				return false;
				
			}
			if(!$(".two").prop("checked")){
				alert("동의 항목을 확인해주세요!")
				return false;
			}
			
    		
    		
			let pay_check;
			if($("select[name=pay] option:selected").text() == '현장결제'){
				pay_check = 0;
			} else {
				pay_check = 1;
			}
			
			let dorm_no = document.querySelector("#dorm_no").value
			let room_no = document.querySelector("#room_no").value
			let reserve_checkin = $("#checkin").text();
			let reserve_checkout = $("#checkout").text();
			let reserve_pay = $("#pay").text();
			let pay_ment = $("select[name=pay] option:selected").text();
			let real_name = document.getElementById('real_name').value;
			let pay_num = document.getElementById('pay_num').value;
			let dorm_name = $("#dorm").text();
			let room_name = $("#room").text();
			if(pay_num == '' && $("select[name=pay] option:selected").text() == '현장결제') pay_num = "현장에서 결제 바랍니다.";
			
				
				$.ajax({
					url : "${contextPath}/trip/result.do",
					type : "get",
					data : {
						dorm_no : dorm_no,
						room_no : room_no,
						reserve_checkin : reserve_checkin,
						reserve_checkout : reserve_checkout,
						reserve_pay : reserve_pay,
						pay_ment : pay_ment,
						real_name : real_name,
						pay_num : pay_num,
						pay_check : pay_check,
						dorm_name : dorm_name,
						room_name : room_name
						
					},
					success : function(data) {
							console.log("성공");
							location.href = "${contextPath}/trip/history.do";
						
					},
					fail : function(data) {
						console.log("fail", data);
					},
					complete : function(data) {
						console.log("comp", data);
					}
				})
		})
		
	}
	
	function event() {
		
		
		
		$("#selec").on("change", function(){ 
			console.log($(this).val()); 
			
			if($(this).val() == "non" ){
				$('#pay_not_yet').show();
				$('#pays').hide();
				$('#pay_num').hide();
				$('#phone_not_yet').hide();
				$('#card_not_yet').hide();
			} else if($(this).val() == "payco" ){
				$('#pay_not_yet').hide();
				$('#card_not_yet').hide();
				$('#payco_not_yet').show();
				$('#phone_not_yet').hide();
				$('#pays').show();
				$('#pay_num').show();
			} else if($(this).val() == "phone" ){
				$('#phone_not_yet').show();
				$('#pay_not_yet').hide();
				$('#payco_not_yet').hide();
				$('#card_not_yet').hide();
				$('#pays').show();
				$('#pay_num').show();
			}else if($(this).val() == "card" ){
				$('#phone_not_yet').hide();
				$('#pay_not_yet').hide();
				$('#payco_not_yet').hide();
				$('#card_not_yet').show();
				$('#pays').show();
				$('#pay_num').show();
			}
			})

		
		
	} 


	
	function alarm() {
		alert("예약이 완료되었습니다!");
	}
	
	
	function checkSelectAll()  {
		  let checkboxes 
		    = document.querySelectorAll('input[name="check"]');
		  let checked 
		    = document.querySelectorAll('input[name="check"]:checked');
		  let selectAll 
		    = document.querySelector('input[name="check_one"]');
		  
		  if(checkboxes.length === checked.length)  {
		    selectAll.checked = true;
		  }else {
		    selectAll.checked = false;
		  }

		}

		function selectAll(selectAll)  {
			let checkboxes 
		     = document.getElementsByName('check');
		  
		  checkboxes.forEach((checkbox) => {
		    checkbox.checked = selectAll.checked
		  })
		}
		
		
		function Checkform2() { 
			if( (document.querySelector("#real_name").value == '' && document.querySelector("#pay_num").value == '') 
					|| (document.querySelector("#real_name").value == '' && document.querySelector("#pay_num").value != '') ) { 
				alert("예약자명을 입력해 주십시오.");
				$(document.querySelector("#real_name")).focus();
				return false;
			} else if(document.querySelector("#real_name").value != '' 
					&& $("select[name=pay] option:selected").text() != '현장결제' 
					&& document.querySelector("#pay_num").value == '') {
				alert("결제정보를 입력해 주십시오.");
				$(document.querySelector("#pay_num")).focus();
				return false;
			}
			
		
		
		
		}
</script>
</head>
<body>
	<%@ include file="./header.jsp"%>
	<section>
		<div class="sec">
			<div class="info">예약자 정보</div>
			<br>
			<div>
				<div>예약자 이름</div>
				<div>${id}</div>
			</div>
			<br>
			<div>
				<div>휴대폰 번호</div>
				<div>${dto.member_tel}</div>
			</div>
			<br>
			<!-- 상세페이지에서 넘겨온 내용들 session에 담아서 여기다가 출력 -->
			<!-- session.member_tel -->


			<div class="name">
				<div class="name2">[숙소 이름]</div>
				<div class="c1" id="dorm">${check.dorm_name}</div>
				<br>
				<div class="name2">[객실 이름]</div>
				<div class="c2" id="room">${check.room_name}</div>
				<div class="c3"></div>
				<br>
				<div>
					<div class="name2">[체크인]</div>
					<div class="c4" id="checkin">${check.reserve_checkin} ${check.in_time }</div>
				</div>
				<br>
				<div>
					<div class="name2">[체크아웃]</div>
					<div class="c5" id="checkout">${check.reserve_checkout} ${check.out_time }</div>
				</div>
				<br>
				<div class="c6">
					<div>[총 결제금액(VAT포함)]</div>
					<div id="pay">${check.reserve_pay}</div>
				</div>
				<br>
				<div class="c7">
					<div>[ 💳결제수단선택]</div>
					<div>
						<select name="pay" id="selec">
							<option value="card" selected="selected">신용카드</option>
							<option value="payco">페이 결제</option>
							<option value="phone">휴대폰 결제</option>
							<option value="non">현장결제</option>
						</select>
						<div id="impor">
							<span id="names">예약자명 :</span> <input id="real_name" name="text_one"
								class="name_input" type="text"><br> <span id="pays">결제정보
								입력 :</span> <input id="pay_num" class="pay_input" name="text_two" 
								type="text" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"><br>
							<span id="card_not_yet">*카드번호 16자리를 입력해 주세요.<br>*숫자만 입력 가능합니다.</span> <span
								id="payco_not_yet" style="display: none;">*페이 카드번호를 입력해
								주세요.<br>*숫자만 입력 가능합니다.</span> <span id="phone_not_yet" style="display: none;">*휴대폰
								번호를 입력 해주세요.<br>*숫자만 입력 가능합니다.</span> <span id="pay_not_yet" style="display: none;">*현장에서
								결제 바랍니다.</span>
						</div>
					</div>
				</div>
				<br>
				<div class="c8">
					<div>
						<input type="checkbox" class="check all" id="check_all"
							name="check_one" value="all" onclick='selectAll(this)'> <label
							for="form">전체동의</label><br> <input type="checkbox"
							class="check one" name="check" value="one"
							onclick='checkSelectAll()'> <label for="form">숙소
							이용 규칙 및 취소/환불규정 동의 (필수)</label><br> <input type="checkbox"
							class="check two" name="check" value="two"
							onclick='checkSelectAll()'> <label for="form">개인정보
							수집 및 이용 동의 (필수)</label><br> <input type="checkbox" class="check thr"
							name="check" value="thr" onclick='checkSelectAll()'> <label
							for="form">광고 sns 홍보 수신 동의</label><br>
					</div>
				</div>
			</div>
			<!--  member_id 가져가야함-->
			<form action="result.do">
				<input type="button" id="btn_pay" class="box" name="action" value="결제하기">
				<a href="${contextPath}/trip/detail.do?dormno=${param.dormno}&reserve_checkin=${param.reserve_checkin}&reserve_checkout=${param.reserve_checkout}"><input type="button" class="box" value="돌아가기"></a>
				<input type="hidden"  id="dorm_no" value="${check.dorm_no}">
				<input type="hidden"  id="room_no" value="${check.room_no}">
				<!-- <input type="hidden" name="reserve_checkin"
					value="${check.reserve_checkin}"> 
				<input type="hidden"
					name="reserve_checkout" value="${check.reserve_checkout}">
				<input type="hidden" name="reserve_pay" value="${check.reserve_pay}"> -->
			</form>
		</div>
	</section>
	<%@ include file="./footer.jsp"%>
</body>
</html>