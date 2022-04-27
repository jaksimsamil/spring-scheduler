<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>스케줄러 테스트</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <script src="http://code.jquery.com/jquery-3.1.1.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <script type="text/javascript">
        $(function(){
            let websocketAddress = "ws://localhost:8080/socket/hello"
            var ws = new WebSocket(websocketAddress);

            ws.onopen = function(e){ // 연결 시 실행
                console.log("info : connection opened.");
            }

            ws.onmessage = function(e){ // 서버로부터 메세지를 받았을 때 실행
                $("#board").append($('<span class="text-white small"></span><br>').text(e.data));
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
<div class="container">
    <h3>삼성 스케줄러 동작 시간 변경 테스트</h3>
    <input type="text" id="samsungCronValue">
    <br>
    <a href="javascript:void(0);" class="update" name="SAMSUNG">스케줄러 동작 시간 변경</a>
    <a href="javascript:void(0);" class="stop" name="SAMSUNG">스케줄러 중지</a>
    <a href="javascript:void(0);" class="restart" name="SAMSUNG">스케줄러 시작</a>

    <br><br>
    <h3>애플 스케줄러 동작 시간 변경 테스트</h3>
    <input type="text" id="appleCronValue">
    <br>
    <a href="javascript:void(0);" class="update" name="APPLE" >스케줄러 동작 시간 변경</a>
    <a href="javascript:void(0);" class="stop" name="APPLE">스케줄러 중지</a>
    <a href="javascript:void(0);" class="restart" name="APPLE">스케줄러 시작</a>
    <br>
    <br>

    <div id="board" class="bg-secondary">
    </div>
</div>
</body>
<script>
$(function(){
    const map = new Map();
    map.set("SAMSUNG", "#samsungCronValue");
    map.set("APPLE", "#appleCronValue");

    $(document)
    .on("click", ".update", function(){
		$.ajax({
	        url: 'updateScheduler',
	        type: 'POST',
	        data: {cron: $(map.get($(this).attr('name'))).val(), sampleType: $(this).attr('name')},
	        success: function (data) {
	    		if(data.res == "success"){
	    		    alert("스케줄러 동작 시간이 변경되었습니다.");
	    		}
	        },
	        error: function (error) {
	    		console.log(error);
	        }
	    });
    })
    .on("click", ".stop", function(){
		$.ajax({
	        url: 'stopScheduler',
	        type: 'POST',
	        data: {sampleType: $(this).attr('name')},
	        success: function (data) {
	    		if(data.res == "success"){
	    		    alert("스케줄러가 멈췄습니다.");
	    		}
	        },
	        error: function (error) {
	    		console.log(error);
	        }
	    });
    })
    .on("click", ".restart", function(){
        $.ajax({
            url: 'restartScheduler',
            type: 'POST',
            data: {sampleType: $(this).attr('name')},
            success: function (data) {
                if(data.res == "success"){
                    alert("스케줄러가 재시작되었습니다.");
                }
            },
            error: function (error) {
                console.log(error);
      	    }
        });
    });
});
</script>
</html>