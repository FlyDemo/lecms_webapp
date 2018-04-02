$("document").ready(function(){
	
	
	listPageData("/lecms_webapp//MessageController/messageList");
	
	bind();
	
});

var bind = function(){
	
	//控制每一行显示置顶或者取消置顶
	$("tr").each(function(){
		var isTop = $(this).find("input#isTop").val();
		if("true"==isTop){
			$(this).find("a#isTopBtn").text("取消置顶 |").attr("isTop","false");
		}
		if("false"==isTop){
			$(this).find("a#isTopBtn").text("置顶 |").attr("isTop","true");
		}
	});
	
	//置顶或者取消置顶按钮
	$("tr").find("a#isTopBtn").on("click",function(){
		var id = $(this).parents("tr.messageListTr").attr("id");
		var topShow = $(this).attr("isTop");
		$(location).attr("href","/lecms_webapp/MessageController/editMessage?op=topShow&id="+id+"&topShow="+topShow);
	});
	
	
	//查看按钮
	$("tr").find("a#viewBtn").on("click",function(){
		var id = $(this).parents("tr.messageListTr").attr("id");
		$(location).attr("href","/lecms_webapp/MessageController/showMessage?id="+id);
	});
	
	//删除按钮
	$("tr").find("a#deleteBtn").on("click",function(){
		var con = confirm("确认删除吗?");
		if(con){
			var id = $(this).parents("tr.messageListTr").attr("id");
			$(location).attr("href","/lecms_webapp/MessageController/editMessage?id="+id+"&op=delete")
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