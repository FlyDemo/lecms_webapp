$("document").ready(function(){
	dh();
});

/**
 * 导航
 */
var dh = function(){
	$("a.dh").each(function(){
		$(this).on("click",function(){
			$("#content").html('<iframe src="'+$(this).attr("hrefs")+'"width=100% height=100%></iframe>');
		})
	});
}