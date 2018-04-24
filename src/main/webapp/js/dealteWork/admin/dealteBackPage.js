$("document").ready(function(){
	

	$("input[type='text']").attr("readonly","readonly").addClass("readonly").css("background-color","C1C1C1");
	$("select").attr("readonly","readonly").addClass("readonly").css("background-color","C1C1C1");
	$("textarea").attr("readonly","readonly").addClass("readonly").css("background-color","C1C1C1");
	
	//损坏数量可编辑
	$("#sbadNum").removeAttr("readonly").css("background-color","white");
	
	$("#sbadNum").on("change",function(){
		var num = $(this).val();
		var snum = $("#num").val();
		if(num>snum){
			$(this).attr("placeholder","损坏数量不得大于归还数量！").val("").focus();
			return;
		}
		var $ap = $("#apen");
		if(num>=0){
			//先把上一次添加的移除，然后再添加
			//移除
			var $div = $ap.children("div");
			if($div.length>1){
				for(var i=1;i<$div.length;i++){
					$div[i].remove();
				}
			}
			//添加
			for(var i=0;i<num;i++){
				var x = i+1;
				var body="<div class='layui-form-item'><label class='layui-form-label'>损坏编号"+x+"</label><div class='layui-input-block'><input type='text' id='badNum"+x+"' class='layui-input required'></div></div><div class='layui-form-item'><label class='layui-form-label'>损坏说明"+x+"</label><div class='layui-input-block'><input type='text' id='badContext"+x+"' class='layui-input required'></div></div>";
				$ap.append(body);	
			}
		}
		
		$("input[id^='badNum']").each(function(){
			$(this).on("change",function(){
				var materialDetails = "";
				$("input[id^='badNum']").each(function(){
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
	});
	
	
	//保存
	$("#sub").on("click",function(){
		var valid = $(".dealteForm").valid();
		if(valid){
			var id = $("#flowId").val();
			//获取所有的损坏编号
			var badCode = "";
			$("input[id^='badNum']").each(function(){
				badCode = badCode+$(this).val()+",";
			});
			
			var badContext = "";
			$("input[id^='badContext']").each(function(){
				badContext = badContext+$(this).val()+",";
			});
			
			$.ajax({
				url:"/lecms_webapp/WorkController/dealteBack",
				type:"POST",
				data:{"id":id,"badCode":badCode,"badContext":badContext},
				success:function(){
					$(location).attr("href","/lecms_webapp/WorkController/dealteBackList");
				}
			});
			
			/*$(location).attr("href","/lecms_webapp/WorkController/dealteBack?id="+id+"&badCode="+badCode+"&badContext="+badContext);*/
		}
	});
	
	//取消
	$("#cans").on("click",function(){
		history.go(-1);
	});
});
