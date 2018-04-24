<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/base.jsp" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>朝阳实验室实验耗材使用管理系统</title>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/mobjs/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/mobjs/jsapi.js"></script>
<script type="text/javascript" src="${ctx}/js/mobjs/format+zh_CN,default,corechart.I.js"></script>		
<script type="text/javascript" src="${ctx}/js/mobjs/jquery.gvChart-1.0.1.min.js"></script>
<script type="text/javascript" src="${ctx}/js/mobjs/jquery.ba-resize.min.js"></script>
<script type="text/javascript" src="${ctx}/js/front/default.js"></script>

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

	<div class="app-name" style="text-align:center;font-family:宋体;font-size:30px;margin:10px;">朝阳实验室实验耗材使用管理系统</div>
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
	    			<span><a id="userExit" href="${ctx}/UserController/userExit">[退出]</a></span>
	    		</c:otherwise>
	    	</c:choose>
	    		
	    	
	    	
	    	
		</div>
    </div>
    
    
    <div class="mainbox">
    
    <div class="mainleft">
    
    
    <div class="leftinfo">
    <div class="listtitle"><a href="#" class="more1"></a>数据统计</div>
        
    <div class="maintj">  
    <table id='myTable5'>
				<thead>
				<div style="margin-top:-17px;">
					<select id="materialConsume">
						<c:forEach var="material" items="${materialAll}">
							<option value="${material.id}">${material.materialName}</option>
						</c:forEach>
					</select>
					<lable>耗材率如图所示：</lable>
				</div>
				
					<tr>
						<th>
						</th>
						<th>已使用次数</th>
						<th>理论剩余次数</th>
					</tr>
				</thead>
					<tbody>
					<tr>
						<th></th>
						<td id="tused">0</td>
						<td id="tcanUse">0</td>	
					</tr>
				</tbody>
		</table>  
    </div>
    
    </div>
    <!--leftinfo end-->
    
    
    <div class="leftinfos">
    
   
    <div class="infoleft">
    
    <div class="listtitle"><a href="#" class="more1"></a>规章制度</div>    
    <ul class="newlist">
    	<c:forEach var="institution" items="${institutionList}">
    		<li><a href="${ctx}/indexInstitution?id=${institution.id}" target="_black">${institution.name}</a><b><fmt:formatDate value="${institution.createTime}" pattern="yyyy-MM-dd"/></b></li>
    	</c:forEach>
    </ul>   
    
    </div>
    
    
    <div class="inforight">
    <div class="listtitle"><a href="#" class="more1"></a>最新器材</div>
    
    <ul class="tooli">
    	<c:forEach var="materialBean" items="${material}">
	    	<li><span><img width="50px" height="50px" src="${ctx}/${materialBean.materialImgPath}" /></span><p>${materialBean.materialName}</p></li>
	    </c:forEach>
    </ul>
    
    </div>
    
    
    </div>
    
    
    </div>
    <!--mainleft end-->
    
    
    <div class="mainright">
    
    
    <div class="dflist">
    <div class="listtitle"><a href="#" class="more1"></a>最新信息</div>    
    <ul class="newlist">
    	<c:forEach var="newsBean" items="${newsList}">
		    <li><a <c:if test="${newsBean.topShow}">style="color:red"</c:if> href="${ctx}/indexNews?id=${newsBean.id}" target="_black">${newsBean.newsTitle.length()>12?newsBean.newsTitle.substring(0,12):newsBean.newsTitle}<c:if test="${newsBean.newsTitle.length()>12 }">..</c:if><b>${newsBean.newsTime}</b></a></li> 
    	</c:forEach>
    </ul>        
    </div>
    
    
    <div class="dflist1">
    <div class="listtitle"><a href="#" class="more1"></a>信息统计</div>    
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