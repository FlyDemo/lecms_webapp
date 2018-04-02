$("document").ready(function(){
	
	$("textarea").attr("readonly","readonly").addClass("readonly");


	//返回
	$("#cans").on("click",function(){
		$(location).attr("href","/lecms_webapp/MaterialController/findMaterialDataByName");
	});
})	