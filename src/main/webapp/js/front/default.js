$("document").ready(function(){

	/*加载统计信息*/
	var id = $("#materialConsume").find("option:selected").val();
	$.ajax({
		url:"/lecms_webapp/indexConsume",
		type:"POST",
		data:{"id":id},
		dataType:"JSON",
		success:function(data){
			var obj = $.parseJSON($.parseJSON(data));
			var a1 = obj.used;
			var a2 = obj.canUse;
			console.info(a1+"000"+a2);
			$("td#tused").text(a1);
			$("td#tcanUse").text(a2);

			gvChartInit();
			jQuery('#myTable5').gvChart({
					chartType: 'PieChart',
					gvSettings: {
						vAxis: {title: 'No of players'},
						hAxis: {title: 'Month'},
						width: 650,
						height: 250
						}
			});
		}
	});
	
	$("#materialConsume").live("change",function(){
		var id = $(this).find("option:selected").val();
		$.ajax({
			url:"/lecms_webapp/indexConsume",
			type:"POST",
			data:{"id":id},
			dataType:"JSON",
			success:function(data){
				var obj = $.parseJSON($.parseJSON(data));
				$("#tused").text(obj.used);
				$("#tcanUse").text(obj.canUse);
				gvChartInit();
				jQuery('#myTable5').gvChart({
						chartType: 'PieChart',
						gvSettings: {
							vAxis: {title: 'No of players'},
							hAxis: {title: 'Month'},
							width: 650,
							height: 250
							}
				});
			}
		});
	});
	
	/*加载比例信息*/
})