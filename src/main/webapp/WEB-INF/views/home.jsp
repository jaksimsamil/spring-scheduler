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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <script src="http://code.jquery.com/jquery-3.1.1.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <style>
        #commentdiv{
            width:700px;
            margin:auto;
            margin-top:10px;
        }

        .commentPara{
            line-height:1;
        }
    </style>
</head>
<body>
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
    <div id="commentdiv">
        <c:forEach var="comment" items="${openDateList}" begin="0" end="${fn:length(openDateList)}" step = "1" varStatus="status">
            <p class = "commentPara">No : ${comment.timeSeq}</p>
            <p class = "commentPara">date : ${comment.openDate}</p>
            <br><br>
        </c:forEach>
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
	    		    location.reload();
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
	    		    location.reload();
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
                    location.reload();
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