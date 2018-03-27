$(document).ready(function(){
	/**
	 * 重置按钮
	 */
	$("#indexBtn").on('click',function(){
		$(location).attr("href","/lecms_webapp");
	});
});


function sub(){
	var valid =  $("#loginForm").valid();
	if("true"==valid||valid){
		$.ajax({
			url:"/lecms_webapp/UserController/login",
			async:false,
			data:$("#loginForm").serialize(),
			success:function(data){
				var her = '/lecms_webapp/home';
				if(data=="true"){
					var referrerLink = document.referrer;
					if(!$.trim(referrerLink)==''){
						if(referrerLink.indexOf("/lecms_webapp")!='-1'){
							her = referrerLink;
						}
					}
					$(location).attr("href",her);
				}else{
					alert("用户名或密码错误，登陆失败！");
				}
			}
		});
	}
};