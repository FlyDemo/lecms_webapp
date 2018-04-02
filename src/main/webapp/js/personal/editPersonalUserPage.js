$("document").ready(function(){
	
	var op = $("#op").val();
	
	$(".userEditForm").validate({
		 rules:{
			 rePassWord:{
				 equalTo: "#passWord" 
			 }
		 },
		 messages:{
			 rePassWord:{
				 equalTo: "两次密码不一致！"
			 }
		 }
	});
	
	if("view"==op){
		$("input[type='text']").attr("readonly","readonly").addClass("readonly").css("background-color","C1C1C1");
		$("div[name='ps']").hide();
		$("#sub").hide();
		$("#cans").hide();
		$("#subPass").hide();
		
		$("#edit").on("click",function(){
			$("input[type='text']").removeAttr("readonly").css("background-color","white");
			$("input[name='leval']").attr("readonly","readonly").addClass("readonly").css("background-color","C1C1C1");
			$("input[name='loginName']").attr("readonly","readonly").addClass("readonly").css("background-color","C1C1C1");
			$(this).hide();
			$("#sub").show();	
			$("#cans").show();
		});
		
		$("#rePass").on("click",function(){
			$("div.layui-form-item").hide();
			$("div[name='ps']").show();
			$("#edit").hide();
			$("#sub").hide();
			$("#subPass").show();
			$("input[type='text']").removeAttr("readonly").css("background-color","white");
			$(this).hide();
			$("#cans").show();
		});
	}
	
	$("#sub").on("click",function(){//保存基本资料    获取id 姓名和电子邮箱，  要校验表单
		var valid = $(".userEditForm").valid();
		if(valid){
			var id=$("#id").val();
			var name=$("input[name='name']").val();
			var mail=$("input[name='mail']").val();
			
			$.ajax({
				url:"/lecms_webapp/UserController/saveUser",
				type:"POST",
				data:{"id":id,"name":name,"mail":mail,"op":"base"},
				success:function(){
					$(location).attr("href","/lecms_webapp/UserController/personInfo")
				}
			});
		}
	});
	
	$("#cans").on("click",function(){//取消按钮  返回到开始
		$(location).attr("href","/lecms_webapp/UserController/personInfo")
	});
	
	$("#subPass").on("click",function(){ //保存密码，获取密码和id   要校验表单
		var valid = $(".userEditForm").valid();
		if(valid){
			var id=$("#id").val();
			var passWord=$("#passWord").val();
			$.ajax({
				url:"/lecms_webapp/UserController/saveUser",
				type:"POST",
				data:{"id":id,"passWord":passWord},
				success:function(){
					$(location).attr("href","/lecms_webapp/UserController/personInfo")
				}
			});
			
		}
	});
	
});