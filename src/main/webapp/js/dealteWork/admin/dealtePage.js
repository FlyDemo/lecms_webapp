$("document").ready(function(){
	

	$("input[type='text']").attr("readonly","readonly").addClass("readonly").css("background-color","C1C1C1");
	$("select").attr("readonly","readonly").addClass("readonly").css("background-color","C1C1C1");
	$("textarea").attr("readonly","readonly").addClass("readonly").css("background-color","C1C1C1");
	
	var op = $("#op").val();
	if(op=='agree'){
		$("input[id^='materialCode']").css("background-color","white").removeAttr("readonly");
		$("input[id^='materialCode']").on("change",function(){
			console.info("校验没写！");
		})
	}else{
		$("#reviewContent").css("background-color","white").removeAttr("readonly");
	}
	
	//保存
	$("#sub").on("click",function(){
		var valid = $(".dealteForm").valid();
		if(valid){
			var flowId = $("#flowId").val();
			var ajaxUrl = "/lecms_webapp/WorkController/dealte?id="+flowId;
			if(op=='agree'){
				//同意，获取flowId，materials然后处理数据
				
				var materials = "";
				$("input[id^='materialCode']").each(function(){
					materials = materials + $(this).val() + ",";
				});
				
				
				ajaxUrl = ajaxUrl+"&op=agree&materials="+materials;
			}else{
				//拒绝，获取flowId和reviewContent 去后台处理数据
				var reviewContent = $("#reviewContent").val();
				ajaxUrl=ajaxUrl+"&op=refuse&reviewContent="+reviewContent;
			}
			
			$(location).attr("href",ajaxUrl);
		}
	});
	
	//取消
	$("#cans").on("click",function(){
		history.go(-1);
	});
});
