$("document").ready(function(){
	//查看按钮
	$("#viewBtn").each(function(){
		$(this).on("click",function(){
			var id = $(this).parents("tr.userDataListTr").attr("id");
			$(location).attr("href","/lecms_webapp/UserController/editUser?op=view&id="+id);
		});
	});
	//编辑按钮
	$("#editBtn").each(function(){
		$(this).on("click",function(){
			var id = $(this).parents("tr.userDataListTr").attr("id");
			$(location).attr("href","/lecms_webapp/UserController/editUser?op=edit&id="+id);
		});
	});
	//删除按钮
	$("#deleteBtn").each(function(){
		$(this).on("click",function(){
			var text = $.trim($(this).text());
			var con = confirm("是否"+text+"此用户？");
			if(con){
				var id = $(this).parents("tr.userDataListTr").attr("id");
				$(location).attr("href","/lecms_webapp/UserController/deleteUser?role=ORDINARY&id="+id);
			}
		});
	});
});