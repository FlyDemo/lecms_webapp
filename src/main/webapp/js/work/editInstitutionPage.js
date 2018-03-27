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
	}
	
	$("#sub").on("click",function(){
		
		var valid = $("#InstitutionForm").valid();
		if(valid){
			var id=$("input[name='id']").val();
			var InsitutionName = $("#InstitutionName").val();
			var content = ue.getContent();
			
			$.ajax({
				url:"/lecms_webapp/WorkController/saveInstitution",
				type:"POST",
				data: {"id":id,"name":InsitutionName,"content":content},
				success:function(){
					$(location).attr("href","/lecms_webapp/WorkController/findInstitutionList");
				}
			})
		}
		
	});
	
	$("#cans").on("click",function(){
		$(location).attr("href","/lecms_webapp/WorkController/findInstitutionList");
	});
	
});
