<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/base.jsp" %>
<html>
<head>
</head>
<body>
	<h1>用户基本信息</h1>
	
	<form action="">
		<span>登录名：</span><input type="text" name="loginName" value="${sessionScope.user.loginName}"/><br>
		<span>用户名：</span><input type="text" name="userName" value="${sessionScope.user.name }"/><br>
		<span>角色：</span><input type="text" name="leval"  value="${sessionScope.user.leval}"/><br>
		<span>电子邮箱</span><input type="text" name="mail" value="${sessionScope.user.mail}"/><br>
		
		<button style="width:5px" name="edit" value="编辑"/><br>
		<button name="rePass" value="修改密码"/><br>
	</form>
</body>
</html>