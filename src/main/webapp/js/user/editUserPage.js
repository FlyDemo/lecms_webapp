$("document").ready(function(){
	
	var op = $("#op").val();
	if("view"==op){
		$("#sub").hide();
		$("input[type='text']").attr("readonly","readonly").addClass("readonly").css("background-color","C1C1C1");
		$("input[type='checkbox']").attr("readonly","readonly").addClass("readonly").css("background-color","C1C1C1");
		$("select").attr("readonly","readonly").addClass("readonly").css("background-color","C1C1C1");
		$("input[name='passWord']").val("用户密码已经被加密！");
	}
	
	/*这儿需要一个登录名的异步校验*/
	
	
//	确认按钮
	$("#sub").on("click",function(){
		
		var valid = $(".userEditForm").valid();
		if(valid){
			var id=$("input[name='id']").val();
			var name=$("input[name='name']").val();
			var loginName = $("input[name='loginName']").val();
			var passWord = $("input[name='passWord']").val();
			var mail = $("input[name='mail']").val();
			var leval = $("option:selected").val();
			var deleted = $("em").text();
			if("启用"==deleted){
				deleted = false;
			}else{
				deleted = true;
			}
			
			console.info(id+"-"+name+"-"+loginName+"-"+passWord+"-"+mail+"-"+leval+"-"+deleted);
			
			$.ajax({
				url:"/lecms_webapp/UserController/saveUser",
				type:"POST",
				data: {"id":id,"name":name,"loginName":loginName,"passWord":passWord,"mail":mail,"leval":leval,"deleted":deleted},
				success:function(){
					$(location).attr("href","/lecms_webapp/UserController/UserList?role=ORDINARY");
				}
			})
		}
		
	});
	
//	取消按钮
	$("#cans").on("click",function(){
		$(location).attr("href","/lecms_webapp/UserController/UserList?role=ORDINARY");
	});
});