$("document").ready(function(){
	
	var op = $("#op").val();
	if("create"==op){//创建器材
		
		/**
		 * 初始化日历控件
		 */
		laydate({
	        elem: '#materialRepairTime'
	    });
		
		laydate({
	        elem: '#freeUseTime'
	    });
		
	}

	//器材名称变化时，应该根据器材类型+器材名称去查重   如果重复，不允许在此添加，只能点进详情进行添加
	$("#materialName").on("change",function(){
		//获取当前器材分类编号
		var materialId = $("#id").val();
		var materialCategory = $("option:selected").val();
		var materialName = $("#materialName").val();
		
		if($.trim(materialName)!=''){
			$.ajax({
				url:"/lecms_webapp/MaterialController/checkMaterialName",
				type:"POST",
				data:{"id":materialId,"materialCategory":materialCategory,"materialName":materialName},
				success:function(data){
					if(data=="true"){
						//表示重复
						$("#materialName").attr("placeholder",materialCategory+"   分类下的   "+materialName+"  名称已被创建，如需添加器材数量，请前往对应的器材详情中进行添加！").val("").focus();
					}
				}
			});
		}
		
	});
	
	/**
	 * 保存和取消按钮事件绑定
	 */
	
	//保存
	$("#sub").on("click",function(){
			
			var valid = $(".categoryEditForm").valid();
			if(valid){
				var id = $("input[name='id']").val();
				var img = $("#img").val();
				var categoryCode = $("option:selected").val();
				var materialName = $("#materialName").val();
				var materialDesc = $("#materialDesc").val();
				var price = $("#price").val();
				var materialRepairTime = $("#materialRepairTime").val();
				var freeUseTime = $("#freeUseTime").val();
				var materialCreator = $("#materialCreator").val();
				var tip = $("#tip").val();
				var total = $("#total").val();
				var deleted = false;
				
				$.ajax({
					url:"/lecms_webapp/MaterialController/saveMaterial",
					type:"POST",
					data:$(".categoryEditForm").serialize(),
					success:function(){
						$(location).attr("href","/lecms_webapp/MaterialController/findMaterialDataByName");
					}
				})
			}
			
		});
	
	//取消
	$("#cans").on("click",function(){
		$(location).attr("href","/lecms_webapp/MaterialController/findMaterialDataByName");
	});
	
	
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
	
});


function previewImage(file)  
{  
  fileName=$("#img").val();  
  $("#imgName").attr("value",fileName);  
      
  var MAXWIDTH  = 80;     //用来显示上传图片的宽度  
  var MAXHEIGHT = 80;     //用来显示上传图片的高度  
  var div = document.getElementById('preview');  
  if (file.files && file.files[0])  
  {  
    div.innerHTML = '<img id=imghead>';  
    var img = document.getElementById('imghead');  
    img.onload = function(){  
      var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);  
      img.width = rect.width;  
      img.height = rect.height;  
      img.style.marginLeft = rect.left+'px';  
      img.style.marginTop = rect.top+'px';  
    };  
    var reader = new FileReader();  
    reader.onload = function(evt){img.src = evt.target.result;};  
    reader.readAsDataURL(file.files[0]);  
  }  
  else  
  {  
    var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';  
    file.select();  
    var src = document.selection.createRange().text;  
    div.innerHTML = '<img id=imghead>';  
    var img = document.getElementById('imghead');  
    img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;  
    var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);  
    status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);  
    div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;margin-left:"+rect.left+"px;"+sFilter+src+"\"'></div>";  
  } 
  
  uploadImg(file);
}  
function clacImgZoomParam( maxWidth, maxHeight, width, height ){  
    var param = {top:0, left:0, width:width, height:height};  
    if( width>maxWidth || height>maxHeight )  
    {  
        rateWidth = width / maxWidth;  
        rateHeight = height / maxHeight;  
        if( rateWidth > rateHeight )  
        {  
            param.width =  maxWidth;  
            param.height = Math.round(height / rateWidth);  
        }else  
        {  
            param.width = Math.round(width / rateHeight);  
            param.height = maxHeight;  
        }  
    }  
    param.left = Math.round((maxWidth - param.width) / 2);  
    param.top = Math.round((maxHeight - param.height) / 2);  
    return param;  
} 

var uploadImg = function(file){
/*	$.ajax({
		"url":"/lecms_webapp/MaterialController/uploadImg",
		"type":"POST",
		"data":{"file":file},
		success:function(data){
			console.info(data);
		}
	})*/
	

	
	// 上传设置  
    var options = {  
            // 规定把请求发送到那个URL  
            url: "/lecms_webapp/MaterialController/uploadImg",  
            // 请求方式  
            type: "post",  
            // 服务器响应的数据类型  
            dataType: "json",  
            // 请求成功时执行的回调函数  
            success: function(data, status, xhr) {  
                // 图片显示地址  
            	console.info(data);
               /* $("#allUrl").attr("src", data.path);  */
            }  
    };  
      
    $(".categoryEditForm").ajaxSubmit(options);  
}