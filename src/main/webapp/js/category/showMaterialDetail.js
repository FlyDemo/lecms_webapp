$("document").ready(function(){
	

	$("#img").hide();
	$("input[type='text']").attr("readonly","readonly").addClass("readonly").css("background-color","C1C1C1");
	$("select").attr("readonly","readonly").addClass("readonly").css("background-color","C1C1C1");
	$("textarea").attr("readonly","readonly").addClass("readonly").css("background-color","C1C1C1");
	
	

	var materialId = $("#materialId").val();
	
	var op = $("#op").val();
	
	if("view"==op){
		$("#sub").hide();
	}

	
	/**
	 * 保存和取消按钮事件绑定
	 */
	
	//保存
	$("#sub").on("click",function(){

				var materialCode = $("#materialCode").val();
				
				$.ajax({
					url:"/lecms_webapp/MaterialDetailController/saveMaterialDetail",
					type:"POST",
					data:{"materialCode":materialCode,"materialId":materialId},
					success:function(data){
						if("true"!=data){
							alert("保存失败！请联系系统管理员！");
						}
						$(location).attr("href","/lecms_webapp/MaterialDetailController/materialDetailDataList?materialId="+materialId);
					}
				})
		});
	
	//取消
	$("#cans").on("click",function(){
		$(location).attr("href","/lecms_webapp/MaterialDetailController/materialDetailDataList?materialId="+materialId);
	});
});
