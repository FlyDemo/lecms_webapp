$("document").ready(function(){
	
	/*这儿需要一个登录名的异步校验*/
	
	
//	确认按钮
	$("#sub").on("click",function(){
		
		var valid = $(".userEditForm").valid();
		if(valid){
			var id=$("input[name='id']").val();
			var InsitutionName = $("#InstitutionName").val();
			var content = ue.getContent();
			
			$.ajax({
				url:"/lecms_webapp/WorkController/saveInstitution",
				type:"POST",
				data: {"id":id,"name":InsitutionName,"content":content},
				success:function(){
					$(location).attr("href","/lecms_webapp/WorkController/findInstitutionList");
				}
			})
		}
		
	});
	
//	取消按钮
	$("#cans").on("click",function(){
		$(location).attr("href","/lecms_webapp/UserController/UserList");
	});
});