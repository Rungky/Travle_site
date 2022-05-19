$(function(){
	init();
	bind();
})

function init(){
	
}

function bind(){
	
	//비밀번호& 비밀번호 확인 칸에 입력한게 동일한지 확인하기
	$("#pw_2").off("keydown").on("keydown", function(evt){
		if($("#pw").val() != $("#pw_2").val()){
          $("#pw_2").parent().find("div.red_1").removeClass("hide_1");
		  
        } else if($("#pw").val() == $("#pw_2").val()){
          $("#pw_2").parent().find("div.red_1").addClass("hide_1");
        }
	});
}