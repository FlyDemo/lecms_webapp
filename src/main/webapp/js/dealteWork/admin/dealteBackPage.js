$("document").ready(function(){
	

	$("input[type='text']").attr("readonly","readonly").addClass("readonly").css("background-color","C1C1C1");
	$("select").attr("readonly","readonly").addClass("readonly").css("background-color","C1C1C1");
	$("textarea").attr("readonly","readonly").addClass("readonly").css("background-color","C1C1C1");
	
	//损坏数量可编辑
	$("#badNum").removeAttr("readonly").css("background-color","white");
	
	$("#badNum").on("change",function(){
		var num = $(this).val();
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
