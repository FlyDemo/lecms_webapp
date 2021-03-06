$("document").ready(function(){
	
	listPageData("/lecms_webapp/MaterialController/findMaterialDataByName");
	
	bind();
	
});

var bind = function(){
	
	//查看按钮
	$("tr").find("a#viewBtn").on("click",function(){
		var id = $(this).parents("tr.materialListTr").attr("id");
		$(location).attr("href","/lecms_webapp/MaterialController/editMaterial?op=view&id="+id);
	});
	
	//编辑按钮
	$("tr").find("a#editBtn").on("click",function(){
		var id = $(this).parents("tr.materialListTr").attr("id");
		$(location).attr("href","/lecms_webapp/MaterialController/editMaterial?op=edit&id="+id);
	});
	
	//详情按钮
	$("tr").find("a#detailBtn").on("click",function(){
		var id = $(this).parents("tr.materialListTr").attr("id");
		$(location).attr("href","/lecms_webapp/MaterialController/editMaterial?op=detail&id="+id);
		/*window.open("/lecms_webapp/MaterialController/editMaterial?op=detail&id="+id)*/
	});
	
	//删除按钮
	$("tr").find("a#deleteBtn").on("click",function(){
		var total=$(this).parent().parent().parent().find("#total").text();
		var tip = "";
		if(total>0){
			tip = "当前名称下存在"+total+"个器材，此操作将会全部，是否进行此操作？";
		}else{
			tip = "确认删除吗？";
		}
		var con = confirm(tip);
		if(con){
			var id = $(this).parents("tr.materialListTr").attr("id");
			$(location).attr("href","/lecms_webapp/MaterialController/editMaterial?op=delete&id="+id);
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