$("document").ready(function(){
	listPageData("/lecms_webapp/UserController/myBorrow");
	
	//绑定取消申请按钮
	$("tr").find("a#deleteBtn").on("click",function(){
		var con = confirm("是否确定取消此申请？");
		if(confirm){
			var flowId = $(this).parents("tr.borrowFlowListTr").attr("id");
			$(location).attr("href","/lecms_webapp/UserController/cansMyBorrow?id="+flowId);
		}
	});
	
});
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