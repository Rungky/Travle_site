$(function(){
	init();
	bind();
})

function init(){
	
}

function bind(){
	
	let id_regex = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	let pw_regex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*\W).{8,20}$/g;
	let name_regex = /^[가-힣a-zA-Z0-9]{2,10}$/g;
	let tel_regex = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})?[0-9]{4,4}?[0-9]{4}$/;
	
	//엔터키를 누르면 비밀번호 칸에 focus주기, enter키는 13임
	$("#id").off("keydown").on("keydown", function(evt){
		if(evt.keyCode == 13){  //enter : 13일때
			$("#pw").focus();
			return false;
		}
	});
	$("#id").off("keyup").on("keyup", function(evt){
		if(!id_regex.test($("#id").val())){
			$(this).parent().find("div.red_1").removeClass("hide_1");
			$("id").focus();
			return false;
		}else {
			$(this).parent().find("div.red_1").addClass("hide_1");
		}
	});
	
	//비밀번호 칸에서 엔터 시 비밀번호 확인 칸에 focus
	$("#pw").off("keydown").on("keydown", function(evt){
		if(evt.keyCode == 13){  //enter : 13일때
			$("#pw_2").focus();
			return false;
		}
	});
	$("#pw").off("keyup").on("keyup", function(evt){
		if(!pw_regex.test($("#pw").val())){
			$(this).parent().find("div.red_1").removeClass("hide_1");
			$("#pw").focus();
			return false;
		}else {
			$(this).parent().find("div.red_1").addClass("hide_1");
		}
	});
	
	//비밀번호& 비밀번호 확인 칸에 입력한게 동일한지 확인하기
	$("#pw_2").off("keydown").on("keydown", function(evt){
		if(evt.keyCode == 13){
			$("#name_1").focus();
			return false;
		}
	});
	$("#pw_2").off("keyup").on("keyup", function(evt){
		if($("#pw").val() != $("#pw_2").val()){
          $(this).parent().find("div.red_1").removeClass("hide_1");
          $("#pw_2").focus();
			return false;
        } else{
          $(this).parent().find("div.red_1").addClass("hide_1");
        }
	});
	
	//닉네임 칸에서 엔터 시 전화번호 칸에 focus
	$("#name_1").off("keydown").on("keydown", function(evt){
		if(evt.keyCode == 13){  //enter : 13일때
			$("#tel_1").focus();
			return false;
		}
	});
	$("#name_1").off("keyup").on("keyup", function(evt){
		if(!name_regex.test($("#name_1").val())){
			$(this).parent().find("div.red_1").removeClass("hide_1");
			$("#name_1").focus();
			return false;
		}else {
			$(this).parent().find("div.red_1").addClass("hide_1");
		}
	});
	
	//전화번호 칸에서 엔터 시 로그인 버튼에 focus
	$("#tel_1").off("keydown").on("keydown", function(evt){
		if(evt.keyCode == 13){  //enter : 13일때
			$("#signup").focus();
			return false;
		}
	});
	$("#tel_1").off("keyup").on("keyup", function(evt){
		if(!tel_regex.test($("#tel_1").val())){
			$(this).parent().find("div.red_1").removeClass("hide_1");
			$("#tel_1").focus();
			return false;
		}else {
			$(this).parent().find("div.red_1").addClass("hide_1");
		}
	});

	$("#signup").off("click").on("click", function(evt){
			if($("#id").val() == '' || $("#pw").val() == '' 
			|| $("#pw_2").val() == '' || $("#name_1").val() == '' || $("#tel_1").val() == ''){
				var text = "빈칸이 있습니다";
				console.log("test");
				alert(text);
				console.log(!$("#no").attr("class").includes("hide_1"));
				evt.preventDefault();
			} else if(!$("#no").attr("class").includes("hide_1")){
				var text = "회원가입 실패";
				console.log(!$("#no").attr("class").includes("hide_1"));
				console.log($("#no").attr("class"));
				alert(text);
				evt.preventDefault();
			} else {
				var text = "회원가입 성공";
				alert(text);
			}
			
	});
	
	
	
	$("#id_check").off("click").on("click", function() {
		let member = $("#id").val();
		console.log(member);
		$.ajax({
			url : "idDuplCheck.do",
			type : "post",
			data : {
				member : member
			},
			success : function(data) {
				if(data == "0"){
					console.log(member);
					$("#idDuplCheck").attr("data-member", member);
					alert("사용 가능한 아이디입니다!");
				}else {
					console.log(data);
					alert("중복된 아이디입니다!");
				}
			}
		})
	});
	
}