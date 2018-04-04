$("document").ready(function(){

	var categoryId = $("#categoryId").val();
	
	listPageData("/lecms_webapp/MaterialController/findMaterialDataByCategory?categoryId="+categoryId);
	
	bind();
	
});

var bind = function(){
	
	//viewBtn
	$("tr").find("a#viewBtn").on("click",function(){
		var id = $(this).parents("tr.materialListTr").attr("id");
		$(location).attr("href","/lecms_webapp/MaterialController/ordinaryViewMaterial?id="+id);
	});
	
	//借出按钮
	$("tr").find("a#borrowBtn").on("click",function(){
		var materialId = $(this).parents("tr.materialListTr").attr("id");
		$(location).attr("href","/lecms_webapp/MaterialController/borrowMaterialPage?id="+materialId);
		
	});

	//borrowBtn
	/*$("tr").find("a#borrowBtn").on("click",function(){
		
		var surPlus = $(this).parents("tr.materialListTr").find("div#surPlus").text();
		if(surPlus>0){
			var id = $(this).parents("tr.materialListTr").attr("id");
			$.ajax({
				url:"/lecms_webapp/MaterialController/borrowMaterial",
				type:"POST",
				data:{"id":id},
				success:function(data){
					if("true"==data){
						alert("申请成功，请耐心等待管理员审核！如有疑问，前往<a class='dh' hrefs='#'>我的申请</a>中查看。");
					}
				}
			});
		}else{
			alert("抱歉，该设备暂时空缺。");
		}
	});*/
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