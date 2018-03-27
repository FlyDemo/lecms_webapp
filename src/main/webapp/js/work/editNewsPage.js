$(document).ready(function(){
	
	
	//如果是查看页面，所有的组件都变为readonly
	var op = $("#op").val();
	if("view"==op){
		$("input[name!='cans']").attr("readonly","readonly").addClass("readonly").css("background-color","C1C1C1");
		
		$("select").attr("disabled","disabled");
		
		/*初始化uedit*/
		var ue = UE.getEditor('editor',{
			readonly:true
		});
		
		$("#sub").hide();
		
	}else{
		
		/*初始化uedit*/
		var ue = UE.getEditor('editor',{
			readonly:false
		});
		
		
		/**
		 * 初始化日历控件
		 */
		laydate({
	        elem: '#newsTime'
	    });
	}
	
	$("#sub").on("click",function(){
		
		var valid = $("#newsForm").valid();
		if(valid){
			var id=$("input[name='id']").val();
			var title = $("#newsTitle").val();
			var newsTime = $("#newsTime").val();
			var topShow = $("select[name='topShow']").find("option:checked").val();
			var content = ue.getContent();
			
			$.ajax({
				url:"/lecms_webapp/WorkController/saveNews",
				type:"POST",
				data: {"id":id,"newsTitle":title,"newsTime":newsTime,"topShow":topShow,"newsContent":content},
				success:function(){
					$(location).attr("href","/lecms_webapp/WorkController/findNewsList");
				}
			})
		}
		
	});
	
	$("#cans").on("click",function(){
		$(location).attr("href","/lecms_webapp/WorkController/findNewsList");
	});
	
});
