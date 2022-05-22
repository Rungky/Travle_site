$(function(){
	init();
	bind();
})

function init(){
	
}

function bind(){
	$("#names").off("keydown").on("keydown", function(evt){
		if(evt.keyCode == 13){  //enter : 13일때
			$("#tel").focus();
			return false;
		}
	});
	
	$("#tel").off("keydown").on("keydown", function(evt){
		if(evt.keyCode == 13){  //enter : 13일때
			$("#btn").focus();
			return false;
		}
	});
}