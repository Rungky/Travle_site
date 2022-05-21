<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
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
			<table id="mod">
			</table>
		</div>
		<div class="bts">
			<button id="del_bt" type="button">삭제</button>
			<button type="button" onclick="window.close()"id="bt2">닫기</button>
		</div>
	<script>
		document.getElementById("mod").innerHTML = opener.$("#nextpage").attr(
				"value");
		$(".bts").prepend($("#mod tr td:last-child").html());
		$("#mod tr th:last-child").remove();
		$("#mod tr td:last-child").remove();
		$("input:checkbox").removeAttr("onClick");
		$("input").removeAttr("readonly");
		let type = $(".bt").attr("value");
		console.log("type");

		$(".bt").off("click").on("click", function() {
			if (type == "mem") {
				let id = $("#id").val();
				let pw = $("#pw").val();
				let name = $("#name").val();
				let tel = $("#tel").val();
				$.ajax({
					url : "update_admin.do",
					type : "post",
					data : {
						type : type,
						id : id,
						pw : pw,
						name : name,
						tel : tel
					},
					complete : function() {
						opener.parent.location.reload();
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
						port : port
					},
					complete : function() {
						opener.parent.location.reload();
						window.close();
					}
				})
			}

		})

		$(".contents_bt").off("click").on("click", function() {
			$("#hidden_bg").addClass("contents_bg_on");
			$("#hidden_bg").removeClass("contents_bg_off");
			$("#hidden_contents").removeClass("nodisplay");
			let contents = $(this).val();
			$("#contents_textarea").html(contents);
		});

		$("#hidden_bg").off("click").on("click", function() {
			$("#hidden_bg").addClass("contents_bg_off");
			$("#hidden_bg").removeClass("contents_bg_on");
			$("#hidden_contents").addClass("nodisplay");
		});

		$("#ct_inbt").off("click").on("click", function() {
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
						opener.parent.location.reload();
						window.close();
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
						opener.parent.location.reload();
						window.close();
					}
				})
			}
			
		});
	</script>
</body>
</html>