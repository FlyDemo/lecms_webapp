<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>

.no_read{
	display:block;
	background:#f00;
	border-radius:50%;
	width:13px;
	height:13px;
	top:5px;
	right:0px;
	position:absolute;
	text:2;
}

</style>


<script>
	$(document).ready(function(){
		$("span.no_read").hide();
		setInterval(hasMessage,60*1000);
		
		
	});
	
	function hasMessage(){
		//判断是否隐藏
		var display = $("span.no_read").css('display');
		if(display=="none"){//隐藏了
			$.ajax({
				url:"/lecms_webapp/MessageController/hasNewMessage",
				type:"POST",
				success:function(data){
					console.info(data);
					if(data=="true"){
						console.info("show");
						$("span.no_read").show();
					}
				}
			});
		}
	};
	
	function hideTip(){
		console.info("点击hide");
		$("span.no_read").hide();
	}
	
</script>

</head>


<body>
  <div class="layui-layout layui-layout-admin"/>
  <div class="layui-header">
    <div class="layui-logo">ZhaoYang   朝阳实验室</div>
    <!-- 头部区域  -->
    <ul class="layui-nav layui-layout-left" style="width:100%;font-family:宋体;float:top;"> 
    	<li style="font-size:30px;margin-left:25%;">朝阳实验室实验耗材管理系统</li>
    </ul>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
         	<span class="no_read"></span>${sessionScope.user.name}
        </a>
        <dl class="layui-nav-child">
          <dd><a class="dh" hrefs="${ctx}/MessageController/messageList" onClick="hideTip()" >我的消息<span class="no_read"></span></a></dd>
          <dd><a class="dh" hrefs="${ctx}/UserController/personInfo">基本资料</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="${ctx}/home">首页动态</a></li>
      <li class="layui-nav-item"><a href="${ctx}/UserController/userExit">安全退出</a></li>
    </ul>
  </div>
</body>
</html>