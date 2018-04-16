<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>朝阳实验室</title>
<style type="text/css">

.title{
	width:70%;
	margin:30 auto;
	text-align:center;
	font-size:20px;
}

.content{
	width:60%;
	margin:60 auto;
}

hr{
	width:70%;
}

</style>

</head>


<body>
	<canvas style="display:none;" id="can" width="350" height="350"></canvas> 

	<c:if test="${flag eq 'news'}">
		<div class="title">
			${bean.newsTitle}
		</div>
		
		<hr>
		<div class="content">
			${bean.newsContent}
		</div>
	</c:if>
	
	<c:if test="${flag eq 'it'}">
		<div class="title">
			${bean.name}
		</div>
		
		<hr>
		<div class="content">
			${bean.content}
		</div>
	</c:if>
	

	<div id="WaterMark"></div> 
	<script>  
	(function() {  
	    $('#WaterMark').css({  
	        "position": "absolute",  
	        "left": "0",  
	        "top": "50px",  
	        "height": $(document).height()-50,  
	        "opacity": "1",  
	        "width": "100%",  
	        "z-index": "900"  
	    });  
	    var canvas = document.getElementById('can');  
	    if(!canvas.getContext) {  
	        //你的浏览器不支持canvas!  
	        return;  
	    }  
	    ctx = canvas.getContext('2d');  
	    ctx.font="30px Microsoft YaHei";  
	    ctx.rotate(-45*Math.PI/180);  
	    ctx.fillStyle = 'rgba(0, 0, 0, 0.3)';  
	    ctx.fillText("MorningSun朝阳实验室",-130,220);  
	    document.getElementById('WaterMark').style.backgroundImage = 'url("' + ctx.canvas.toDataURL() + '")';  
	})();  
</script>  
</body>
</html>