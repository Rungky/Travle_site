<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사적모임 | Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/insert_admin.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

<script>

</script>
</head>
<body>
<h1 style="text-align: center;">추가</h1>

<div id="mem" class="nodisplay">
	<table>
			<tr>
				<th>아이디</th>
				<td></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td></td>
			</tr>
			<tr>
				<th>닉네임</th>
				<td></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td></td>
			</tr>
	</table>
</div>

<div id="dorm" class="nodisplay">
	<table>
		<tr>
			<th>카테고리</th>
			<td>
				<select id="category" name="category">
					<option value="1">호텔</option>
					<option value="2">게스트 하우스</option>
					<option value="3">리조트</option>
					<option value="4">펜션</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>숙소이름</th>
			<td>
				<input id="name" class="input_name" type="text" name="name">
			</td>
		</tr>
		<tr>
			<th>숙소소개</th>
			<td>
				<textarea id="contents" name="contents"></textarea><div>숙소소개 엔터로 한줄씩 구분 지어주세요!</div>
			</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>
				<input id="addr" class="input_addr" type="text" name="addr">
			</td>
		</tr>
		<tr>
			<th>그림 파일</th>
			<td>
				<input id="picture" type="file" name="picture">
			</td>
		</tr>
		<tr>
		<th>체크인 / 체크아웃</th>
			<td>
				<input id="in_time" class="input_time" type="text" name="in_time"> : <input id="out_time" class="input_time" type="text" name="out_time">  
			</td>
		</tr>
		<tr>
			<th>옵션</th>
			<td>
				<input id="wifi" type="checkbox" name="wifi" value="1">wifi 
				<input id="parking" type="checkbox" name="parking" value="1">parking
				<input id="aircon" type="checkbox" name="aircon" value="1">aircon 
				<input id="dryer" type="checkbox" name="dryer" value="1">dryer 
				<input id="port" type="checkbox" name="port" value="1">port 
			</td>
		</tr>
	</table>
	<div class="fl_between">
		<button id="insert_bt" type="button">추가</button>
		<button id="close_bt" type="button">닫기</button>
	</div>
</div>


<script>
	let type = opener.$("#insert_type").attr("data-type");
	console.log(type);
	console.log("type : " + type);
	if(type=="mem"){
		$("#mem").removeClass("nodisplay");
	} else if(type=="dorm"){
		$("#dorm").removeClass("nodisplay");
	}
	$("#id_check").off("click").on("click", function() {
		let dormno = $("#dormno").val();
		if(dormno=="" || /\s/.test(dormno)){
			alert("공백이 포함되어있습니다!");
			return false
		}else if(!/\d/.test(dormno)){
			alert("숫자만 입력 가능합니다!");
			return false;
		}
		$.ajax({
			url : "dormnoCheck.do",
			type : "post",
			data : {
				dormno : dormno
			},
			success : function(data) {
				if(data == "0" ){
					$("#dormno_checking").attr("data-dormno", dormno);
					alert("사용 가능한 넘버입니다!");
				}else {
					alert("중복된 번호입니다!")
				}
			}
		})
	});
	
	$("#insert_bt").off("click").on("click", function() {
		let dormno_val = $("#dormno").val();
		let data_dormno = $("#dormno_checking").attr("data-dormno");
		if( dormno_val!= data_dormno) {
			alert("중복 체크를 확인해 주세요!");
			return false;
		}
		if(($("#contents").val()).includes(",")){
			alert(",은 사용하지 못합니다.")		;	
			return false;
		}
		if (type == "mem") {
			
		} else if (type == "dorm") {
			let category = $("#category").val();
			let name = $("#name").val();
			let contents_val = $("#contents").val();
			let addr = $("#addr").val();
			let picture = $("#picture").val();
			let in_time = $("#in_time").val();
			let out_time = $("#out_time").val();
			let wifi=0;
			let parking=0;
			let aircon=0;
			let dryer=0;
			let port=0;
			if($("#wifi").prop("checked"))
				wifi = 1;
			if($("#parking").prop("checked"))
				parking = 1;
			if($("#aircon").prop("checked"))
				aircon = 1;
			if($("#dryer").prop("checked"))
				dryer = 1;
			if($("#port").prop("checked"))
				port = 1;

			$.ajax({
				url : "insert_admin2.do",
				type : "post",
				data : {
					type : type,
					category : category,
					name : name,
					contents : contents_val,
					picture : picture,
					addr : addr,
					wifi : wifi,
					parking : parking,
					aircon : aircon,
					dryer : dryer,
					port : port,
					in_time : in_time,
					out_time : out_time
				},
				success : function(data) {
					alert("숙소 넘버는 ["+data+"] 입니다!");
					opener.parent.location.href = "${pageContext.request.contextPath}/trip/admin.do?tabMove=st2";
					window.close();
				}
			})
		}

	})
</script>
</body>
</html>