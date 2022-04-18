<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Web Socket Here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
	    let websocketAddress = "ws://localhost:8080/socket/hello"
		var ws = new WebSocket(websocketAddress);

		ws.onopen = function(e){ // 연결 시 실행
			console.log("info : connection opened.");
		}

		ws.onmessage = function(e){ // 서버로부터 메세지를 받았을 때 실행
			console.log(e.data); //전달 받은 메세지 = e
		}

		ws.onclose = function(e){ // 연결 종료 시 실행
			console.log("info : connection closed");
		};

		ws.onerror = function(e){
			console.log("error")
		};


		$("#btn").on("click",function(e){
			e.preventDefault();
			ws.send($("#testInput").val());
		});



	});
</script>
</head>
<body>
	<h1>Socket Test Page</h1>
	<input type="text" id="testInput">
	<button type="button" id="btn">전송</button>

</body>
</html>