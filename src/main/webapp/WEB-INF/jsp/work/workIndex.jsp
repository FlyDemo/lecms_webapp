<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/base.jsp" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>个人工作台</title>
<link rel="stylesheet" href="${ctx}/css/navigation/layui.css">
</head>

<script src="${ctx}/js/work/workIndex.js"></script>

<body class="layui-layout-body">
<!-- <div class="layui-layout layui-layout-admin">
  <!-- top部分 -->
  <c:import url="/top"></c:import>
  
  <!-- 左侧导航区域 请求后台获取当前的left -->
  <div class="layui-side layui-bg-black" name="left">
    <div class="layui-side-scroll">
      <c:import url="/left"></c:import>
    </div>
  </div>
  
  <!-- 内容主体区域 -->
  <div class="layui-body" id="content">
  	<c:import url="/right"></c:import>
  </div>
	
  <!-- 页脚 -->
  <c:import url="/bottom"></c:import>

</div>
<script src="${ctx}/js/navigation/layui.js"></script>
<script>
//JavaScript代码区域
layui.use('element', function(){
  var element = layui.element;
  
});

$("a.list1").Click  = function(){
	alert("a");
}

</script>
</body>
</html>
