$("document").ready(function(){
	
	listPageData("/lecms_webapp/UserController/noBack");
	
	
	//绑定页面申请归还
	$("tr").find("a#goBackBtn").on("click",function(){
		var id = $(this).parents("tr.borrowFlowListTr").attr("id");
		$(location).attr("href","/lecms_webapp/UserController/back?id="+id);
	});
	
	$("tr").find("a#cansGoBackBtn").on("click",function(){
		var id = $(this).parents("tr.borrowFlowListTr").attr("id");
		$(location).attr("href","/lecms_webapp/UserController/cansBack?id="+id);
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