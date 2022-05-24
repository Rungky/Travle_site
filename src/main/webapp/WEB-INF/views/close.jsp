<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사적모임 | Insert title here</title>
</head>
<body>
</body>
<script>
	let tmpString = "tabMove=st3";
	if( window.opener.location.href.indexOf("?") != -1 ){
		window.opener.location.href += "&"+tmpString;
	} else {
		window.opener.location.href += "?"+tmpString;
	}
// 	window.opener.location.reload();
	window.close();
</script>
</html>