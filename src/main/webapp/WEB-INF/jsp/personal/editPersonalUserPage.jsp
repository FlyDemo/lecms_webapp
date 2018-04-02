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
<script src="${ctx}/js/personal/editPersonalUserPage.js"></script>
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
<body>
	<h1 align="center">用户基本信息</h1>
	<form class="layui-form userEditForm" action="#" method="post">
		<input type="hidden" id="id" name="id" value="${userBean.id}"/>
		<input type="hidden" id="op" name="op" value="${op}"/>
		<div>
			<div class="layui-form-item">
				<label class="layui-form-label">姓名</label>
				<div class="layui-input-block">
					<input type="text" name="name" class="layui-input required" value="${userBean.name}">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">登陆名</label>
				<div class="layui-input-block">
					<input type="text" name="loginName" class="layui-input required" value="${userBean.loginName }">
				</div>
			</div>
			
			<div class="layui-form-item" name="ps">
				<label class="layui-form-label">密码</label>
				<div class="layui-input-block">
					<input type="text" id="passWord" name="passWord" class="layui-input required">
				</div>
			</div>
			
			<div class="layui-form-item" name="ps">
				<label class="layui-form-label">确认密码</label>
				<div class="layui-input-block">
					<input type="text" name="rePassWord" class="layui-input required equalTo:'#passWord'"/>
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">电子邮箱</label>
				<div class="layui-input-block">
					<input type="text" name="mail" class="layui-input email" value="${userBean.mail}">
				</div>
			</div>
			
			
			<div class="layui-form-item">
				<label class="layui-form-label">用户身份</label>
				<div class="layui-input-block">
					<input type="text" name="leval" class="layui-input" value="<c:if test='${userBean.leval eq "ORDINARY"}'>普通会员</c:if><c:if test='${userBean.leval eq "REPAIR_MAN"}'>维修人员</c:if><c:if test='${userBean.leval eq "SUPER_ADMIN"}'>管理员</c:if>">
				</div>
			</div>
			
			<div style="text-align:center">
				<input type="button" name="sub" id="sub" class="btn" value="保存基本信息"/>
				<input type="button" name="edit" id="edit" class="btn" value="修改基本信息"/>
				<input type="button" name="rePass" id="rePass" class="btn" value="修改密码"/>
				<input type="button" name="subPass" id="subPass" class="btn" value="保存密码"/>
				<input type="button" name="cans" id="cans" class="btn" value="取消"/>	
			</div>
		</div>
		
	</form>
</body>
</html>