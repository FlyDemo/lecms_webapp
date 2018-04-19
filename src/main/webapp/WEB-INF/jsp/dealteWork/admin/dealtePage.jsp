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
<script src="${ctx}/js/dealteWork/admin/dealtePage.js"></script>
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
		<input type="hidden" name="flowId" id="flowId" value="${borrow.id}">
		<input type="hidden" name="materialId" id="materialId" value="${borrow.material.id}">
		<div>
			<div class="layui-form-item">
				<label class="layui-form-label">器材图片</label>
				<div class="layui-input-block">
					<c:if test="${empty borrow.material.materialImgPath}">
						<div style="width:80px;height:80px;border:1px solid gray;" class="serverImg" id="preview" name="imgName"></div>
					</c:if>
					<c:if test="${ ! empty borrow.material.materialImgPath}">
						<div id="preview" style="width:80px;height:80px;border:1px solid gray;">
							<img alt="${borrow.material.materialName}" src="${ctx}/${borrow.material.materialImgPath}" width="80px" height="80px"/>
						</div>
					</c:if>
				</div>
			</div>	
			
			<div class="layui-form-item">
				<label class="layui-form-label">申请人</label>
				<div class="layui-input-block">
					<input type="text" id="borrower" name="borrower" class="layui-input" value="${borrow.borrower.name}">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">申请数量</label>
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
				<label class="layui-form-label" style="width:95px;text-align:left;padding:0 0;">可借用时间(天)</label>
				<div class="layui-input-block">
					<input type="text" id="freeUseTime" name="freeUseTime" class="layui-input" value="${borrow.material.freeUseTime}">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">生产厂商</label>
				<div class="layui-input-block">
					<input type="text" id="materialCreator" name="materialCreator" class="layui-input" value="${borrow.material.materialCreator}">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">备注说明</label>
				<div class="layui-input-block">
					<textArea type="text" id="borrowContent" name="borrowContent" resize="none" class="layui-textArea">${borrow.borrowContent}</textArea>
				</div>
			</div>
			
			<!-- 拒绝 -->
			<c:if test="${op eq 'refuse'}">
				<div class="layui-form-item">
					<label class="layui-form-label">拒绝理由</label>
					<div class="layui-input-block">
						<textArea type="text" id="reviewContent" name="reviewContent" resize="none" class="layui-textArea required"></textArea>
					</div>
				</div>
			</c:if>
			
			<input type="hidden" name="op" id="op" value="${op}"/>
			
			<!-- 同意 -->
			<c:if test="${op eq 'agree'}">
				<c:forEach var="num" begin="1" step="1" end="${borrow.num}" varStatus="num">
					<div class="layui-form-item">
						<label class="layui-form-label">器材编号${num.index}</label>
						<div class="layui-input-block">
							<input type="text" id="materialCode${num.index}" name="materialCode${num.index}" class="layui-input required">
						</div>
					</div>
				</c:forEach>
			</c:if>
			
			<div style="text-align:center">
				<input type="button" name="sub" id="sub" class="btn" value="确定"/>	
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