$(function(){
	init();
	bind();
})

function init(){
	
}

function bind(){
	$("#id").off("keydown").on("keydown", function(evt){
		if(evt.keyCode == 13){  //enter : 13일때
			$("#pw").focus();
			return false;
		}
	});

    $("#pw").off("keydown").on("keydown", function(evt){
		if(evt.keyCode == 13){  //enter : 13일때
			$("#btn").focus();
			return false;
		}
	});
}