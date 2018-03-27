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
	    <a class="" href="#">查看器材</a>
	    <dl class="layui-nav-child">
	      <dd><a href="${ctx}/WorkController/findNewsList" target="main">最新消息维护</a></dd>
	      <dd><a href="${ctx}/WorkController/findInstitutionList" target="main">规章制度维护</a></dd>
	      <dd><a href="${ctx}/WorkController/editIndexInfo" target="main">热门器材维护</a></dd>
	    </dl>
	  </li>
	  
	  <li class="layui-nav-item">
		<a href="#">器材借还</a>
		<dl class="layui-nav-child">
	      <dd><a href="">移动模块</a></dd>
	      <dd><a href="javascript:;">后台模版</a></dd>
	      <dd><a href="">电商平台</a></dd>
	    </dl>
	  </li>
	  
	  <li class="layui-nav-item">
		<a href="#">个人资料</a>
		<dl class="layui-nav-child">
	      <dd><a href="">移动模块</a></dd>
	      <dd><a href="javascript:;">后台模版</a></dd>
	      <dd><a href="">电商平台</a></dd>
	    </dl>
	  </li>
	</ul>

</body>
</html>