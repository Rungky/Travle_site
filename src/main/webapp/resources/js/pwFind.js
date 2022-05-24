$(function(){
	init();
	bind();
})

function init(){
	
}

function bind(){
	
	
	$("#pw").off("keydown").on("keydown", function(evt){
		if(evt.keyCode == 13){  //enter : 13일때
			$("#pw_2").focus();
			return false;
		}
	});

	$("#pw_2").off("keydown").on("keydown", function(evt){
		if(evt.keyCode == 13){  //enter : 13일때
			$("#btn").focus();
			return false;
		}
	});
	
	
	//비밀번호& 비밀번호 확인 칸에 입력한게 동일한지 확인하기
	$("#pw_2").off("keyup").on("keyup", function(evt){
		if($("#pw").val() != $("#pw_2").val()){
			console.log($("#pw_2").parent().find("div.red_1").removeClass("hide_1"));
          $("#pw_2").parent().find("div.red_1").removeClass("hide_1");
		  
        } else if($("#pw").val() == $("#pw_2").val()){
			console.log($("#pw_2").parent().find("div.red_1").addClass("hide_1"));
          $("#pw_2").parent().find("div.red_1").addClass("hide_1");
        }
	});
	
	$("#btn").off("click").on("click", function(evt){
			if($("#pw").val() == '' || $("#pw_2").val() == ''){
				var text = "빈칸이 있습니다";
				alert(text);
				evt.preventDefault();
			} else if(!$("#no").attr("class").includes("hide_1")){
				var text = "비밀번호 변경 실패";
				console.log(!$("#no").attr("class").includes("hide_1"));
				console.log($("#no").attr("class"));
				alert(text);
				evt.preventDefault();
			} else {
				var text = "비밀번호 변경 성공";
				alert(text);
			}
			
	});
}