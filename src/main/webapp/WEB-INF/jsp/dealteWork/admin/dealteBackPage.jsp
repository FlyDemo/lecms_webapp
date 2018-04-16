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
<script src="${ctx}/js/dealteWork/admin/dealteBackPage.js"></script>
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
	<form class="layui-form dealteForm" action="#" method="post" enctype="multipart/form-data">
		<input type="hidden" name="flowId" id="flowId" value="${borrow.id}"
		<div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">器材图片</label>
				<div class="layui-input-block">
					<div id="preview" style="width:80px;height:80px;border:1px solid gray;">
						<img alt="${borrow.material.materialName}" src="${ctx}/${borrow.material.materialImgPath}" width="80px" height="80px"/>
					</div>
				</div>
			</div>	
			
			<c:forEach var="materialDetailFlow" items="${borrow.details}" varStatus="i">
				<div class="layui-form-item">
					<label class="layui-form-label">器材编号${i.index+1}</label>
					<div class="layui-input-block">
						<input type="text" id="materialCode${i.index+1}" name="materialCode${i.index+1}" class="layui-input" value="${materialDetailFlow.opMaterial.materialCode}">
					</div>
				</div>
			</c:forEach>
			
			<div class="layui-form-item">
				<label class="layui-form-label">申请人</label>
				<div class="layui-input-block">
					<input type="text" id="borrower" name="borrower" class="layui-input" value="${borrow.borrower.name}">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">器材数量</label>
				<div class="layui-input-block">
					<input type="text" id="num" name="num" class="layui-input" value="${borrow.num}">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">器材名称</label>
				<div class="layui-input-block">
					<input type="text" id="materialName" name="materialName" class="layui-input" value="${borrow.material.materialName}">
				</div>
			</div>
			
			
			<div class="layui-form-item">
				<label class="layui-form-label">器材单价</label>
				<div class="layui-input-block">
					<input type="text" id="price" name="price" class="layui-input number" value="${borrow.material.price}">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">保修期截止</label>
				<div class="layui-input-block">
					<input type="text" id="materialRepairTime" name="materialRepairTime" class="layui-input" value="${borrow.material.materialRepairTime}">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">生产厂商</label>
				<div class="layui-input-block">
					<input type="text" id="materialCreator" name="materialCreator" class="layui-input" value="${borrow.material.materialCreator}">
				</div>
			</div>
			
			<div id="apen">
				<div class="layui-form-item">
					<label class="layui-form-label">损坏数量</label>
					<div class="layui-input-block">
						<input type="text" id="badNum" name="badNum" class="layui-input required" value="0">
					</div>
				</div>
			</div>
			
			<div style="text-align:center">
				<input type="button" name="sub" id="sub" class="btn" value="确认归还"/>	
				<input type="button" name="cans" id="cans" class="btn" value="返回"/>	
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