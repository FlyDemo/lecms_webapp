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
<script src="${ctx}/js/category/editCategory.js"></script>
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
	<form class="layui-form categoryEditForm" action="#" method="post">
		<input type="hidden" id="op" name="op" value="${op}"/>
		<input type="hidden" id="id" name="id" value="${categoryBean.id}"/>
		<div>
			<div class="layui-form-item">
				<label class="layui-form-label">分类编号</label>
				<div class="layui-input-block">
					<input type="text" name="categoryCode" class="layui-input required rangelength:[3,10]" value="${categoryBean.categoryCode}">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">分类名称</label>
				<div class="layui-input-block">
					<input type="text" name="categoryName" class="layui-input required rangelength:[3,10]" value="${categoryBean.categoryName }">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">分类排序</label>
				<div class="layui-input-block">
					<input type="text" name="categorySortNum" class="layui-input required digits" value="${categoryBean.categorySortNum }">
				</div>
			</div>

			<div style="text-align:center">
				<input type="button" name="sub" id="sub" class="btn" value="保存"/>	
				<input type="button" name="cans" id="cans" class="btn" value="取消"/>	
			</div>
		</div>
		
	</form>
</body>
</html>