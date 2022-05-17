$(function(){
	init();
	bind();
})

function init(){
	
}

function bind(){
	//엔터키를 누르면 비밀번호 칸에 focus주기, enter키는 13임
	$("#id").off("keydown").on("keydown", function(evt){
		console.log("evt.keyCode : " + evt.keyCode);
		if(evt.keyCode == 13){  //enter : 13일때
			$("#pw").focus();
			return false;
		}else if(! /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i.test($("#id").val())){
			$(this).parent().find("div.red_1").removeClass("hide_1");
		}else {
			$(this).parent().find("div.red").addClass("hide");
			$(this).parent().find("div.red_1").addClass("hide_1");
		}
	});
   
	//아이디 공백시 나타나는 경고 문구
	$("#id").off("blur").on("blur", function(evt){
		if($(this).val() == ""){
			$(this).parent().find("div.red").removeClass("hide");
			$(this).parent().find("div.red_1").addClass("hide_1");
		} else {
			$(this).parent().find("div.red").addClass("hide");
		}
	});
	
	//비밀번호 칸에서 엔터 시 비밀번호 확인 칸에 focus
	$("#pw").off("keydown").on("keydown", function(evt){
		console.log("evt.keyCode : " + evt.keyCode);
		if(evt.keyCode == 13){  //enter : 13일때
			$("#pw_2").focus();
			return false;
		}else if(!/^(?=.*[a-zA-Z])(?=.*\d)(?=.*\W).{8,20}$/g.test($("#pw").val())){
			$(this).parent().find("div.red").addClass("hide");
			$(this).parent().find("div.red_1").removeClass("hide_1");
		}else {
			$(this).parent().find("div.red").addClass("hide");
			$(this).parent().find("div.red_1").addClass("hide_1");
		}
	});
	
	//비밀번호 공백시 나타나는 경고 문구
	$("#pw").off("blur").on("blur", function(evt){
		if($(this).val() == ""){
			$(this).parent().find("div.red").removeClass("hide");
		} else {
			$(this).parent().find("div.red").addClass("hide");
		}
	});
	
	
	
	//비밀번호 확인 칸에서 엔터 시 닉네임 칸에 focus
	$("#pw_2").off("keydown").on("keydown", function(evt){
		if(evt.keyCode == 13){  //enter : 13일때
			$("#name_1").focus();
			return false;
		}else {
			$(this).parent().find("div.red").addClass("hide");
		}
	});
	
	//비밀번호& 비밀번호 확인 칸에 입력한게 동일한지 확인하기
	$("#pw_2").off("keydown").on("keydown", function(evt){
		if(evt.keyCode == 13){
			$("#name_1").focus();
			return false;
		}else if($("#pw").val() != $("#pw_2").val()){
          $("#pw_2").parent().find("div.red_1").removeClass("hide_1");
        } else{
          $("#pw_2").parent().find("div.red_1").addClass("hide_1");
        }
	});
	
	
	//비밀번호 확인 공백시 나타나는 경고 문구
	$("#pw_2").off("blur").on("blur", function(evt){
		if($(this).val() == ""){
			$(this).parent().find("div.red").removeClass("hide");
		}else {
			$(this).parent().find("div.red_1").addClass("hide_1");
		}
	});
	
	//닉네임 칸에서 엔터 시 전화번호 칸에 focus
	$("#name_1").off("keydown").on("keydown", function(evt){
		console.log("evt.keyCode : " + evt.keyCode);
		if(evt.keyCode == 13){  //enter : 13일때
			$("#tel_1").focus();
			return false;
		}else if(!/^[가-힣a-zA-Z0-9]{2,10}$/g.test($("#name_1").val())){
			$(this).parent().find("div.red").addClass("hide");
			$(this).parent().find("div.red_1").removeClass("hide_1");
		}else {
			$(this).parent().find("div.red").addClass("hide");
			$(this).parent().find("div.red_1").addClass("hide_1");
		}
	});
	
	//닉네임 칸 공백시 나타나는 경고 문구
	$("#name_1").off("blur").on("blur", function(evt){
		if($(this).val() == ""){
			$(this).parent().find("div.red").removeClass("hide");
		}else{
			$(this).parent().find("div.red_1").addClass("hide_1");
		}
	});
	
	//전화번호 칸에서 엔터 시 로그인 버튼에 focus
	$("#tel_1").off("keydown").on("keydown", function(evt){
		console.log("evt.keyCode : " + evt.keyCode);
		if(evt.keyCode == 13){  //enter : 13일때
			$("#signup").focus();
			return false;
		}else if(!/^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})?[0-9]{4,4}?[0-9]{4}$/.test($("#tel_1").val())){
			$(this).parent().find("div.red").addClass("hide");
			$(this).parent().find("div.red_1").removeClass("hide_1");
		}else {
			$(this).parent().find("div.red").addClass("hide");
			$(this).parent().find("div.red_1").addClass("hide_1");
		}
	});
	
	//전화번호 칸 공백시 나타나는 경고 문구
	$("#tel_1").off("blur").on("blur", function(evt){
		if($(this).val() == ""){
			$(this).parent().find("div.red").removeClass("hide");
		}else {
			$(this).parent().find("div.red_1").addClass("hide_1");
		}
	});
}