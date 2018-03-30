$("document").ready(function(){
	
	var op = $("#op").val();
	var id = $("#id").val();
	
	if("view"==op){
		$("#sub").hide();
		$("input[type='text']").attr("readonly","readonly").addClass("readonly").css("background-color","C1C1C1");
	}
	
	if("edit"==op){
		//编号不能修改 
		$("input[name='categoryCode']").attr("readonly","readonly").addClass("readonly").css("background-color","C1C1C1");
	}
	
	/**
	 * 编号和名称增加 已存在校验
	 */
	$("input[name='categoryCode']").on("change",function(){
		var currentCategoryCode = $(this).val();
		$.ajax({
			url:"/lecms_webapp/MaterialCategoryController/checkCategoryCodeAndName",
			type:"POST",
			data: {"id":id,"currentCategoryCode":currentCategoryCode},
			success:function(data){
				console.info("code"+data);
				if("true"==data){
					$("input[name='categoryCode']").attr("placeholder",currentCategoryCode+"  该编号已被注册，请尝试换一个！").val("").focus();
				}
			}
		})
	});
	
	$("input[name='categoryName']").on("change",function(){
		var currentCategoryName = $(this).val();
		$.ajax({
			url:"/lecms_webapp/MaterialCategoryController/checkCategoryCodeAndName",
			type:"POST",
			data: {"id":id,"currentCategoryName":currentCategoryName},
			success:function(data){
				console.info("name"+data);
				if("true"==data){
					$("input[name='categoryName']").attr("placeholder",currentCategoryName+"  该名称已被注册，请尝试换一个！").val("").focus();
				}
			}
		})
	});
	
//	确认按钮
	$("#sub").on("click",function(){
		
		var valid = $(".categoryEditForm").valid();
		if(valid){
			var id=$("input[name='id']").val();
			var categoryCode=$("input[name='categoryCode']").val();
			var categoryName = $("input[name='categoryName']").val();
			var categorySortNum = $("input[name='categorySortNum']").val();
			var deleted = false;
			
			$.ajax({
				url:"/lecms_webapp/MaterialCategoryController/saveMaterialCategory",
				type:"POST",
				data: {"id":id,"categoryCode":categoryCode,"categoryName":categoryName,"categorySortNum":categorySortNum,"deleted":deleted},
				success:function(){
					//需要用js添加左侧导航。。
					$(location).attr("href","/lecms_webapp/MaterialCategoryController/findMaterialCategoryData");
				}
			})
		}
		
	});
	
//	取消按钮
	$("#cans").on("click",function(){
		$(location).attr("href","/lecms_webapp/MaterialCategoryController/findMaterialCategoryData");
	});
});