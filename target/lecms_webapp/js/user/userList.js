$("document").ready(function(){
	var role = $("#role").val();
	
	listPageData("/lecms_webapp/UserController/UserList?role="+role);
	
	bind(role);
	
});

var bind = function(role){
	
	//查看按钮
	$("tr").find("a#viewBtn").on("click",function(){
		var id = $(this).parents("tr.userDataListTr").attr("id");
		$(location).attr("href","/lecms_webapp/UserController/editUser?op=view&id="+id);
	});
	
	//编辑按钮
	$("tr").find("a#editBtn").on("click",function(){
		var id = $(this).parents("tr.userDataListTr").attr("id");
		$(location).attr("href","/lecms_webapp/UserController/editUser?op=edit&id="+id);
	});
	
	//启用按钮
	$("tr").find("a#deleteBtn").on("click",function(){
		var text = $.trim($(this).text());
		var con = confirm("是否"+text+"此用户？");
		if(con){
			var id = $(this).parents("tr.userDataListTr").attr("id");
			$(location).attr("href","/lecms_webapp/UserController/deleteUser?role="+role+"&id="+id);
		}
	});
}

//获取列表分页数据  刷新页面
this.listPageData = function(url){
	var index = url.indexOf("?");
	if(index!=-1){
		url+="&";
	}else{
		url+="?";
	}
	
	$("#firstPage").on("click",function(){
		/*直接ajax跳转到首页*/
		$(location).attr("href",url+"currentPage=1");
	});
	
	$("#proidPage").on("click",function(){
		var currentPage = $("#currentPage").val();
		if(currentPage>1){
			$(location).attr("href",url+"currentPage="+(currentPage*1-1));
		}else{
			alert("没有前一页了！")
		}
	});
	
	$("#nextPage").on("click",function(){
		var currentPage = $("#currentPage").val();
		var totalPage = $("#totalPage").val();
		if(currentPage<totalPage){
			$(location).attr("href",url+"currentPage="+(currentPage*1+1));
		}else{
			alert("没有下一页了！")
		}
	});
	
	$("#endPage").on("click",function(){
		var totalPage = $("#totalPage").val();
		$(location).attr("href",url+"currentPage="+totalPage);
	});
	
	
	$("#goto").on("click",function(){
		var gotoPage = $("#gotoPage").val();
		var totalPage = $("#totalPage").val();
		if(gotoPage>0&&gotoPage<totalPage*1+1){
			$(location).attr("href",url+"currentPage="+gotoPage);
		}
	});
}