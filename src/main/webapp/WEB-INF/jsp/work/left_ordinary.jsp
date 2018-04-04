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
	    <a class="" href="#">个人记录</a>
	    <dl class="layui-nav-child">
	      <dd><a class="dh" hrefs="${ctx}/UserController/myBorrow">我的申请</a></dd>
		  <dd><a class="dh" hrefs="${ctx}/UserController/noBack">未还器材</a></dd>
	      <dd><a class="dh" hrefs="${ctx}/UserController/myFlow">已还器材</a></dd>
	    </dl>
	  </li>
	  
	  
	  <li class="layui-nav-item">
	    <a class="" href="#">查看器材</a>
	    <dl class="layui-nav-child">
	      <c:forEach var="materialCategory" items="${applicationScope.materialCategory}">
	      	<dd><a class="dh" hrefs="${ctx}/MaterialController/findMaterialDataByCategory?categoryId=${materialCategory.id}">${materialCategory.categoryName}</a></dd>
	      </c:forEach>
	    </dl>
	  </li>
	  
	  <li class="layui-nav-item">
		<a href="#">器材归还</a>
		<dl class="layui-nav-child">
	      <dd><a href="">移动模块</a></dd>
	      <dd><a href="javascript:;">后台模版</a></dd>
	      <dd><a href="">电商平台</a></dd>
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