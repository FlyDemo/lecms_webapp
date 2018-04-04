$("document").ready(function(){
	
	//init  禁用除了原因和数量之外的文本框编辑权限
	init();
	
	//borrowBtn
	$("#borrow").on("click",function(){
		var valid = $(".borrowMaterialForm").valid();
		if(valid){
			var material = $("#materialId").val();
			var borrowNum = $("#borrowNum").val();
			var borrowContent = $("#borrowContent").val();
			var borrower = $("#borrower").val();
			$.ajax({
				url:"/lecms_webapp/MaterialController/borrowMaterial",
				type:"POST",
				data:{"material.id":material,"num":borrowNum,"borrowContent":borrowContent,"borrower.id":borrower},
				success:function(data){
					if("true"==data){
						alert("申请成功！")
					}else{
						alert("器材数量不足，申请失败！");
					}
				}
			});
		}
		
	});
	
	//cansBtn
	$("#cans").on("click",function(){
		history.go(-1);
	});
/*
	$("input[type='text']").attr("readonly","readonly").addClass("readonly").css("background-color","C1C1C1");
	$("select").attr("readonly","readonly").addClass("readonly").css("background-color","C1C1C1");
	$("textarea").attr("readonly","readonly").addClass("readonly").css("background-color","C1C1C1");

	//borrow
	$("#borrow").on("click",function(){
		$.ajax({
			url:"/lecms_webapp/MaterialController/borrowMaterial",
			type:"POST",
			data:{"id":id},
			success:function(data){
				alert("申请成功，请耐心等待管理员审核！如有疑问，前往<a class='dh' hrefs='#'>我的申请</a>中查看。");
			}
		});
	});
	
	if(surPlus==0){
		$("#borrow").val("器材暂缺")
		$("#borrow").on("click",function(){
			alert("抱歉，该设备暂时空缺。");
		});
	}
	
	//cans
	$("#cans").on("click",function(){
		$(location).attr("href","/lecms_webapp/MaterialController/findMaterialDataByCategory?categoryId="+categoryId);
	});*/

	
});

var init = function(){
	//改变了所有的样式，borrowContent 和 borrowNum放开
	$("input[type='text']").attr("readonly","readonly").addClass("readonly").css("background-color","C1C1C1");
	$("input#borrowContent").removeAttr("readonly").removeClass("readonly").css("background-color","white");
	$("input#borrowNum").removeAttr("readonly").removeClass("readonly").css("background-color","white");
	
}
