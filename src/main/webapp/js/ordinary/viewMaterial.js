$("document").ready(function(){
	
	var categoryId = $("#categoryId").val();
	
	var materialId = $("#id").val();
	
	var surPlus = $("input[name='surPlus']").val();
	

	$("input[type='text']").attr("readonly","readonly").addClass("readonly").css("background-color","C1C1C1");
	$("select").attr("readonly","readonly").addClass("readonly").css("background-color","C1C1C1");
	$("textarea").attr("readonly","readonly").addClass("readonly").css("background-color","C1C1C1");

	$("#borrow").on("click",function(){
		$(location).attr("href","/lecms_webapp/MaterialController/borrowMaterialPage?id="+materialId);
	});
	//borrow
	/*$("#borrow").on("click",function(){
		$.ajax({
			url:"/lecms_webapp/MaterialController/borrowMaterial",
			type:"POST",
			data:{"id":id},
			success:function(data){
				alert("申请成功，请耐心等待管理员审核！如有疑问，前往<a class='dh' hrefs='#'>我的申请</a>中查看。");
			}
		});
	});*/
	
	if(surPlus==0){
		$("#borrow").val("器材暂缺")
		$("#borrow").on("click",function(){
			alert("抱歉，该设备暂时空缺。");
		});
	}
	
	//cans
	$("#cans").on("click",function(){
		$(location).attr("href","/lecms_webapp/MaterialController/findMaterialDataByCategory?categoryId="+categoryId);
	});

	
});
