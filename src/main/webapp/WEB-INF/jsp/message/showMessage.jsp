<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${ctx}/css/navigation/layui.css">
<script src="${ctx}/js/navigation/layui.js"></script>
<script src="${ctx}/js/message/showMessage.js"></script>
<style>
.layui-form-item{
	padding:20px 0px 10px 30px;
}	

.layui-input-block{
	width:60%;
}

.btn {
    background-color: gray; 
    border: none;
    color: white;
    padding: 10px 25px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 15px;
    margin: 14px 2px;
    cursor: pointer;
}
.btn:hover{
	background-color: #555555;
}

.layui-form-item{
	margin-left:20%;
}
</style>
</head>
<body style="background-color:rgba(128,128,128,0.2);">
<h1 align="center">我的消息</h1>
<div class="layui-collapse" lay-filter="test" lay-accordion style="width:60%;margin:20 auto;">	
	<div align="right">
		<a href="${ctx}/MessageController/messageList" style="font-size:16;background-color:rgba(128,128,128,0.4);">返回</a>	
	</div>	
	<div class="layui-colla-item">
	  <h2 class="layui-colla-title">${message.from.name} —— ${message.to.name }</h2>
	  <div class="layui-colla-content layui-show"  style="border:1px solid gray">
	    <div>
	    	<span>发件人：</span>${message.from.name}  &lt;${message.from.loginName}&gt;</br>
	    	<span>收件人：</span>${message.to.name}  &lt;${message.to.loginName}&gt;</br>
	    	<span>时间：</span>${message.createTime }</br>
	    </div>
	  </div>
	</div>
	
	<div>
		<div>
		    <textarea id="messageContent" name="messageContent" style="resize:none;width:100%;height:300px;">${message.messageContent }</textarea>
		</div>
	</div>
</div>
</body>
<script>
layui.use(['element', 'form'], function(){
  var element = layui.element;
  
  element.on('tab(test)', function(data){
    console.log(this, data);
  });
  
  element.on('nav(test)', function(elem){
    console.log(elem)
  });
  
  element.on('collapse(test)', function(data){
    console.log(data);
  });
});
</script>
</html>