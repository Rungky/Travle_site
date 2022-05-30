<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>사적모임 | Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/modify_admin.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    
</head>
<body>
<div id="hidden_bg" class="cotents_bg_off"></div>
<div id="hidden_contents" class="nodisplay">
	<textarea id='contents_textarea' name='contents_val'></textarea>
	<div class="ct_bt">
		<button id="ct_inbt">입력</button><button id="ct_outbt">닫기</button>
	</div>
</div>
<h1 style="text-align: center;">수정</h1>
		<div class="fl_center">
			<table >
			<tbody id="mod">
			</tbody>
			</table>
		</div>
		<div class="bts">
			<button id="del_bt" type="button">삭제</button>
			<button type="button" onclick="window.close()"id="bt2">닫기</button>
		</div>
	<script>
		let mod = document.getElementById("mod");
		mod.innerHTML = opener.$("#nextpage").attr("value");
		$("#category").removeAttr("disabled");
		let type = $(".bt").attr("value");
		let i_ = $(".bt").attr("data-i");
		let check_text = opener.$("#tr_time"+i_).html();
		$(".bts").prepend($("#mod tr td:last-child").html());
		$("#mod tr th:last-child").remove();
		$("#mod tr td:last-child").remove();
		$("#mod").append("<tr id='check_'></tr>");
		$("#check_").html(check_text);
		console.log(check_text);
		$("input:checkbox").removeAttr("onClick");
		$("input").removeAttr("readonly");
		$("#hidden_checkinout").removeClass("nodisplay");
		$("#hidden_checkinout").attr("colspan", "11");
		console.log("type");

		$(".bt").off("click").on("click", function() {
			if (type == "mem") {
				let id = $("#id").val();
				let pw = $("#pw").val();
				let name = $("#name").val();
				let tel = $("#tel").val();
				let authority = $("#authority").val();
				$.ajax({
					url : "update_admin.do",
					type : "post",
					data : {
						type : type,
						id : id,
						pw : pw,
						name : name,
						tel : tel,
						authority : authority
					},
					complete : function() {
						opener.parent.location.href = "${pageContext.request.contextPath}/trip/admin.do?tabMove=st1";
						window.close();
					}
				})
			} else if (type == "dorm") {
				let dormno = $("#dormno").val();
				let category = $("#category").val();
				let name = $("#name").val();
				let contents_val = $("#contents_val").val();
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
					url : "update_admin.do",
					type : "post",
					data : {
						type : type,
						dormno : dormno,
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
					complete : function() {
						opener.parent.location.href = "${pageContext.request.contextPath}/trip/admin.do?tabMove=st2";
						window.close();
					}
				})
			}else if(type == "room"){
				let room_no = $("#room_no").val();
				let room_name = $("#room_name").val();
				let room_contents = $("#room_contents_val").val();
				let room_picture = $("#room_picture").val();
				let room_pay_day = $("#room_pay_day").val();
				let room_pay_night = $("#room_pay_night").val();
				let room_person = $("#room_person").val();
				
				$.ajax({
					url : "update_admin.do",
					type : "post",
					data : {
						type : type,
						room_no : room_no,
						room_name : room_name,
						room_contents : room_contents,
						room_picture : room_picture,
						room_pay_day : room_pay_day,
						room_pay_night : room_pay_night,
						room_person : room_person
					},
					complete : function() {
						opener.parent.location.href = "${pageContext.request.contextPath}/trip/admin.do?tabMove=st4";
						window.close();	}
				})
			}
		})

		$(".contents_bt").off("click").on("click", function() {
			$("#hidden_bg").addClass("contents_bg_on");
			$("#hidden_bg").removeClass("contents_bg_off");
			$("#hidden_contents").removeClass("nodisplay");
			let contents = $(this).val();
			contents = contents.replaceAll("\n","");
			contents = contents.replaceAll(",","\n");
			$("#contents_textarea").html(contents);
		});

		$("#hidden_bg").off("click").on("click", function() {
			$("#hidden_bg").addClass("contents_bg_off");
			$("#hidden_bg").removeClass("contents_bg_on");
			$("#hidden_contents").addClass("nodisplay");
		});

		$("#ct_inbt").off("click").on("click", function() {
			let contents = $("#contents_textarea").val();
			contents = contents.replaceAll("\n",",");
			$("#contents_textarea").html($("#contents_textarea").val());
			$("#contents_val").attr("value", $("#contents_textarea").val() );
			$("#hidden_bg").addClass("contents_bg_off");
			$("#hidden_bg").removeClass("contents_bg_on");
			$("#hidden_contents").addClass("nodisplay");
		});
		$("#ct_outbt").off("click").on("click", function() {
			$("#hidden_bg").addClass("contents_bg_off");
			$("#hidden_bg").removeClass("contents_bg_on");
			$("#hidden_contents").addClass("nodisplay");
		});
		$("#ct_outbt").off("click").on("click", function() {
			$("#hidden_bg").addClass("contents_bg_off");
			$("#hidden_bg").removeClass("contents_bg_on");
			$("#hidden_contents").addClass("nodisplay");
		});
		$("#ct_outbt").off("click").on("click", function() {
			$("#hidden_bg").addClass("contents_bg_off");
			$("#hidden_bg").removeClass("contents_bg_on");
			$("#hidden_contents").addClass("nodisplay");
		});
		
		$("#del_bt").off("click").on("click", function() {
			console.log(type);
			if(type=="mem"){
				let id = $("#id").val();
				$.ajax({
					url : "delete_admin.do",
					type : "post",
					data : {
						type : type,
						id : id,
					},
					complete : function() {
						if(id=="${sessionScope.id}"){
							alert("본인은 삭제할 수 없습니다. 회원탈퇴를 이용하세요");
							opener.parent.location.reload();
							window.close();
						}else{
						alert("삭제 되었습니다!");
						opener.parent.location.reload();
						window.close();
						}
					}
				})
			}else if(type=="dorm"){
					let dormno = $("#dormno").val();
				$.ajax({
					url : "delete_admin.do",
					type : "post",
					data : {
						type : type,
						dormno : dormno,
					},
					complete : function() {
						alert("삭제 되었습니다!");
						opener.parent.location.reload();
						window.close();
					}
				})
			} else if(type == "room"){
				let room_no = $("#room_no").val();
				$.ajax({
					url:"delete_admin.do",
					type : "POST",
					data : {
						type : type,
						room_no : room_no
					},
					complete : function(){
						alert("삭제 되었습니다!");
						opener.parent.location.reload();
						window.close();
					}
				})
			}
		});
		
	</script>
</body>
</html>