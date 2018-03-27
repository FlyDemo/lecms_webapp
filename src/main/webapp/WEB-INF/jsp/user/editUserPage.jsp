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
<script src="${ctx}/js/user/editUserPage.js"></script>
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
	<form class="layui-form userEditForm" action="#" method="post">
		<input type="hidden" id="op" name="op" value="${op}"/>
		<input type="hidden" id="id" name="id" value="${userBean.id}"/>
		<input type="hidden" id="role" name="role" value="${userBean.leval}"/>
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
			
			<div class="layui-form-item">
				<label class="layui-form-label">密码</label>
				<div class="layui-input-block">
					<input type="text" name="passWord" class="layui-input required">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">电子邮箱</label>
				<div class="layui-input-block">
					<input type="text" name="mail" class="layui-input email" value="${userBean.mail}">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">用户角色</label>
				<div class="layui-input-inline">
					<select name="leval" lay-filter="interest-search" lay-search lay-write>
						<option value="ORDINARY" <c:if test="${userBean.leval eq 'ORDINARY' }">selected</c:if>>普通会员</option>
						<option value="REPAIR_MAN" <c:if test="${userBean.leval eq 'REPAIR_MAN' }">selected</c:if>>维修人员</option>
					</select>
				</div>
			</div>
			
			<div class="layui-form-item" pane>
				<label class="layui-form-label">是否启用</label>
				<div class="layui-input-block" name="deleted">
					<input type="checkbox" <c:if test="${userBean.deleted eq false }">checked</c:if> name="deleted" lay-skin="switch" lay-filter="switchTest" lay-text="启用|禁用">
				</div>
			</div>
			
			<div style="text-align:center">
				<input type="button" name="sub" id="sub" class="btn" value="保存"/>	
				<input type="button" name="cans" id="cans" class="btn" value="取消"/>	
			</div>
		</div>
		
	</form>
</body>

<script>

layui.use('form', function(){
  var form = layui.form;
  

  
  //事件监听
  form.on('select', function(data){
    console.log('select: ', this, data);
  });
  
  form.on('select(quiz)', function(data){
    console.log('select.quiz：', this, data);
  });
  
  form.on('select(interest)', function(data){
    console.log('select.interest: ', this, data);
  });
  
  
  
  form.on('checkbox', function(data){
    console.log(this.checked, data.elem.checked);
  });
  
  form.on('switch', function(data){
    console.log(data);
  });
  
  
});

</script>
</html>