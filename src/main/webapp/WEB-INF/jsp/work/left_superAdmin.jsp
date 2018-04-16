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
	    <a class="">我的待办</a>
	    <dl class="layui-nav-child">
	      <dd><a class="dh" hrefs="${ctx}/WorkController/dealteBorrowList">申请待办</a></dd>
	      <dd><a class="dh" hrefs="${ctx}/WorkController/dealteBackList">归还待办</a></dd>
	    </dl>
	  </li>
	
	  <li class="layui-nav-item">
	    <a class="">首页信息维护</a>
	    <dl class="layui-nav-child">
	      <dd><a class="dh" hrefs="${ctx}/WorkController/findNewsList">最新消息维护</a></dd>
	      <dd><a class="dh" hrefs="${ctx}/WorkController/findInstitutionList">规章制度维护</a></dd>
	    </dl>
	  </li>
	  
	  <li class="layui-nav-item">
		<a href="#">人员管理</a>
		<dl class="layui-nav-child">
	      <dd><a class="dh" hrefs="${ctx}/UserController/UserList?role=ORDINARY">普通会员管理</a></dd>
	      <dd><a class="dh" hrefs="${ctx}/UserController/UserList?role=REPAIR_MAN">维修人员管理</a></dd>
	    </dl>
	  </li>
	  
	 <li class="layui-nav-item">
		<a href="#">器材管理</a>
		<dl class="layui-nav-child">
	      <dd><a class="dh" hrefs="${ctx}/MaterialCategoryController/findMaterialCategoryData">器材分类管理</a></dd>
	      <dd><a class="dh" hrefs="${ctx}/MaterialController/findMaterialDataByName">器材管理</a></dd>
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