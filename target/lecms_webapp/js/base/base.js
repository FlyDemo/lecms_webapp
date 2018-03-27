$(document).ready(function(){
	readOnlyStyle();
});

this.readOnlyStyle = function(){
	$("input[readonly]").each(function(){
		$(this).css("background-color","C1C1C1");
	});
}