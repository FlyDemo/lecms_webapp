<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<ul class="layui-nav layui-nav-tree"  lay-filter="test" style="height:100%">
	  <li class="layui-nav-item">
	    <a class="" href="#">维修详细</a>
	    <dl class="layui-nav-child">
	      <dd><a class="dh" hrefs="${ctx}/WorkController/badMaterialDetailList">损坏器材列表</a></dd>
	    </dl>
	  </li>
	  
	  <li class="layui-nav-item">
		<a href="#">个人中心</a>
		<dl class="layui-nav-child">
	      <dd><a class="dh" hrefs="${ctx}/MessageController/messageList">消息列表</a></dd>
	      <dd><a class="dh" hrefs="${ctx}/UserController/personInfo">基本资料</a></dd>
	    </dl>
	  </li>
	</ul>

</body>
</html>