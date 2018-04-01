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
<script src="${ctx}/js/category/editMaterial.js"></script>
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
	<form class="layui-form categoryEditForm" action="#" method="post" enctype="multipart/form-data">
		<input type="hidden" id="op" name="op" value="${op}"/>
		<input type="hidden" id="id" name="id" value="${materialBean.id}"/>
		<div>
			<div class="layui-form-item">
				<label class="layui-form-label">器材图片</label>
				<div class="layui-input-block">
					<c:if test="${empty materialBean.materialImgPath}">
						<div style="width:80px;height:80px;border:1px solid gray;" class="serverImg" id="preview" name="imgName"></div>
					</c:if>
					<c:if test="${ ! empty materialBean.materialImgPath}">
						<div style="width:80px;height:80px;border:1px solid gray;">
							<img alt="${materialBean.materialName}" src="${ctx}/${materialBean.materialImgPath}" width="80px" height="80px"/>
						</div>
					</c:if>
					<input type="hidden" name="imgSrc" id="imgSrc" value="${materialBean.materialImgPath}"/>
					
					<input id="img" name="img" type="file" class="required" name="materialImgPath" onchange="previewImage(this);" value="上传图片"/>
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">器材分类</label>
				<div class="layui-input-inline">
					<select name="materialCategory.categoryCode" lay-filter="interest-search" lay-search lay-write>
						<c:forEach var="materialCategory" items="${applicationScope.materialCategory}">
							<option value="${materialCategory.id}" zname="${materialCategory.categoryName}" <c:if test="${materialBean.materialCategory.categoryCode eq materialCategory.categoryCode}">selected</c:if>>${materialCategory.categoryName}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">器材名称</label>
				<div class="layui-input-block">
					<input type="text" id="materialName" name="materialName" class="layui-input required minlength:20" value="${materialBean.materialName}">
				</div>
			</div>
			
			<div class="layui-form-item layui-form-text">
			  <label class="layui-form-label">器材说明</label>
			  <div class="layui-input-block">
			    <textarea id="materialDesc" name="materialDesc" placeholder="请输入内容" class="layui-textarea minlength:300">${materialBean.materialDesc}</textarea>
			  </div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">器材单价</label>
				<div class="layui-input-block">
					<input type="text" id="price" name="price" class="layui-input required number" value="${materialBean.price}">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">保修期截止</label>
				<div class="layui-input-block">
					<input type="text" id="materialRepairTime" name="materialRepairTime" class="layui-input required dateISO" value="${materialBean.materialRepairTime}">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label" style="width:95px;text-align:left;padding:0 0;">可借用时间(天)</label>
				<div class="layui-input-block">
					<input type="text" id="freeUseTime" name="freeUseTime" class="layui-input required digist" value="${materialBean.freeUseTime}">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">生产厂商</label>
				<div class="layui-input-block">
					<input type="text" id="materialCreator" name="materialCreator" class="layui-input required minlength:30" value="${materialBean.materialCreator}">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">使用注意</label>
				<div class="layui-input-block">
					<input type="text" id="tip" name="tip" class="layui-input required minlength:100" value="${materialBean.tip}">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">器材总数量</label>
				<div class="layui-input-block">
					<input type="text" id="total" name="total" class="layui-input required digits" value="${materialBean.total}">
				</div>
			</div>
			
			<div class="layui-form-item" id="surPlus">
				<label class="layui-form-label">实验室剩余</label>
				<div class="layui-input-block">
					<input type="text" name="surPlus" class="layui-input digits" value="${materialBean.surPlus}">
				</div>
			</div>
			
			<div class="layui-form-item" id="badNum">
				<label class="layui-form-label">已损坏数量</label>
				<div class="layui-input-block">
					<input type="text" name="badNum" class="layui-input digits" value="${materialBean.badNum}">
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