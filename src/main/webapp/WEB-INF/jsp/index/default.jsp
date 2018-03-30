<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/base.jsp" %>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>朝阳实验室器材消耗管理系统</title>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/mobjs/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/mobjs/jsapi.js"></script>
<script type="text/javascript" src="${ctx}/js/mobjs/format+zh_CN,default,corechart.I.js"></script>		
<script type="text/javascript" src="${ctx}/js/mobjs/jquery.gvChart-1.0.1.min.js"></script>
<script type="text/javascript" src="${ctx}/js/mobjs/jquery.ba-resize.min.js"></script>

<script type="text/javascript">
		gvChartInit();
		jQuery(document).ready(function(){

		jQuery('#myTable5').gvChart({
				chartType: 'PieChart',
				gvSettings: {
					vAxis: {title: 'No of players'},
					hAxis: {title: 'Month'},
					width: 650,
					height: 250
					}
			});
		
		});
		</script>
</head>


<body>

	<div class="app-name" style="text-align:center;font-family:宋体;font-size:20px;margin-top:20px;">朝阳实验室器材消耗管理系统</div>
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
		    <li><a href="${ctx}/home">【首页】</a></li>
		    <li><a href="${ctx}/WorkController/workIndex">【工作台】</a></li>
	    </ul>
	    
	    <div style="position:fixed;right:80px;">
	    	<c:choose>
	    		<c:when test="${empty sessionScope.user }">
	    			<span><a href="${ctx}/UserController/loginPage">亲，请[登陆]</a></span>
	    		</c:when>
	    		<c:otherwise>
	    			<span>${sessionScope.user.name} 欢迎您</span>
	    			<span><a id="userExit" href="#">[退出]</a></span>
	    		</c:otherwise>
	    	</c:choose>
	    		
	    	
	    	
	    	
		</div>
    </div>
    
    
    <div class="mainbox">
    
    <div class="mainleft">
    
    
    <div class="leftinfo">
    <div class="listtitle"><a href="#" class="more1">更多</a>数据统计</div>
        
    <div class="maintj">  
    <table id='myTable5'>
				<caption>uimaker.com players count</caption>
				<thead>
					<tr>
						<th></th>
						<th>Jan</th>
						<th>Feb</th>
						<th>Mar</th>
						<th>Apr</th>
						<th>May</th>
						<th>Jun</th>
						<th>Jul</th>
						<th>Aug</th>
						<th>Sep</th>
						<th>Oct</th>
						<th>Nov</th>
						<th>Dec</th>
					</tr>
				</thead>
					<tbody>
					<tr>
						<th>2010</th>
						<td>125</td>
						<td>185</td>
						<td>327</td>
						<td>359</td>
						<td>376</td>
						<td>398</td>
						<td>22</td>
						<td>33</td>
						<td>44</td>
						<td>55</td>
						<td>66</td>
						<td>0</td>
					</tr>
				</tbody>
			</table>  
    </div>
    
    </div>
    <!--leftinfo end-->
    
    
    <div class="leftinfos">
    
   
    <div class="infoleft">
    
    <div class="listtitle"><a href="#" class="more1">更多</a>规章制度</div>    
    <ul class="newlist">
    <li><a href="#">上海自贸区今日正式挂牌成立</a><b>10-09</b></li>
    <li><a href="#">习近平将访东南亚两国 首次出席APEC峰会</a><b>10-08</b></li>
    <li><a href="#">最高法:谎称炸弹致航班备降者从重追刑责</a><b>10-09</b></li>
    <li><a href="#">华北大部遭遇雾霾天 北京全城陷重污染</a><b>10-06</b></li>
    <li><a href="#">"环保专家"董良杰涉嫌寻衅滋事被刑拘</a><b>10-05</b></li>
    <li><a href="#">中央巡视组：重庆对一把手监督不到位</a><b>10-04</b></li>
    <li><a href="#">囧!悍马没改好成华丽马车(图)</a><b>10-03</b></li>
    </ul>   
    
    </div>
    
    
    <div class="inforight">
    <div class="listtitle"><a href="#" class="more1">更多</a>热门器材</div>
    
    <ul class="tooli">
	    <li><span><img src="${ctx}/images/d01.png" /></span><p><a href="#">信息资料</a></p></li>
	    <li><span><img src="images/d02.png" /></span><p><a href="#">编辑</a></p></li>
	    <li><span><img src="images/d03.png" /></span><p><a href="#">记事本</a></p></li>
	    <li><span><img src="images/d04.png" /></span><p><a href="#">任务日历</a></p></li>
	    <li><span><img src="images/d05.png" /></span><p><a href="#">图片管理</a></p></li>
	    <li><span><img src="images/d06.png" /></span><p><a href="#">交易</a></p></li>
	    <li><span><img src="images/d07.png" /></span><p><a href="#">档案袋</a></p></li>   
	    <li><span><img src="${ctx}/images/d01.png" /></span><p><a href="#">信息资料</a></p></li>

    </ul>
    
    </div>
    
    
    </div>
    
    
    </div>
    <!--mainleft end-->
    
    
    <div class="mainright">
    
    
    <div class="dflist">
    <div class="listtitle"><a href="#" class="more1">更多</a>最新信息</div>    
    <ul class="newlist">
    <li><a href="#">上海自贸区今日正式挂牌成立</a></li>
    <li><a href="#">习近平将访东南亚两国 首次出席APEC峰会</a></li>
    <li><a href="#">最高法:谎称炸弹致航班备降者从重追刑责</a></li>
    <li><a href="#">华北大部遭遇雾霾天 北京全城陷重污染</a></li>
    <li><a href="#">"环保专家"董良杰涉嫌寻衅滋事被刑拘</a></li>
    <li><a href="#">中央巡视组：重庆对一把手监督不到位</a></li>
    <li><a href="#">囧!悍马没改好成华丽马车(图)</a></li>
    <li><a href="#">澳门黄金周加派稽查人员监察出租车违规行为</a></li>
    <li><a href="#">香港环境局长吁民众支持政府扩建堆填区</a></li> 
    </ul>        
    </div>
    
    
    <div class="dflist1">
    <div class="listtitle"><a href="#" class="more1">更多</a>信息统计</div>    
    <ul class="newlist">
    <li><i>会员数：</a></i>2535462</li>
    <li><i>文档数：</a></i>5546</li>
    <li><i>普通文章：</a></i>2315</li>
    <li><i>软件：</a></i>1585</li>
    <li><i>评论数：</a></i>5342</li>    
    </ul>        
    </div>
    
    

    
    
    </div>
    <!--mainright end-->
    
    
    </div>



</body>
<script type="text/javascript">
	setWidth();
	$(window).resize(function(){
		setWidth();	
	});
	function setWidth(){
		var width = ($('.leftinfos').width()-12)/2;
		$('.infoleft,.inforight').width(width);
	}
</script>
</html>