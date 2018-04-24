$("document").ready(function(){
	

	$("input[type='text']").attr("readonly","readonly").addClass("readonly").css("background-color","C1C1C1");
	$("select").attr("readonly","readonly").addClass("readonly").css("background-color","C1C1C1");
	$("textarea").attr("readonly","readonly").addClass("readonly").css("background-color","C1C1C1");
	
	var op = $("#op").val();
	if(op=='agree'){
		$("input[id^='materialCode']").css("background-color","white").removeAttr("readonly");
		$("input[id^='materialCode']").each(function(){
			$(this).on("change",function(){
				var materialDetails = "";
				$("input[id^='materialCode']").each(function(){
					materialDetails = materialDetails+"|"+$(this).val();
				});
				/**
				 * 获取器材编号，看后台是否存在，如果存在且状态正确，允许确认，其他情况，不予许
				 */
				var $thisMaterial = $(this);
				var materialCode = $thisMaterial.val();
				if(materialDetails.indexOf(materialCode)!=materialDetails.lastIndexOf(materialCode)){
					$thisMaterial.attr("placeholder","编号:"+materialCode+"重复！").val("").focus();
					return ;
				}
				var materialId = $("#materialId").val();
				console.info("====materianId"+materialId);
				$.ajax({
					url:"/lecms_webapp/WorkController/materialValid",
					type:"POST",
					data:{"materialCode":materialCode,"materialId":materialId},
					success:function(data){
						if(eval(data)=='false'){
							$thisMaterial.attr("placeholder","编号:"+materialCode+"无效！如有疑问，请联系系统管理员。").val("").focus();
						}else{
							materialDetails = materialDetails+materialCode+"|";
						}
					}
				});
			});
		});
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
