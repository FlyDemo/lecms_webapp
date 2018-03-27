<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/base.jsp" %>
<html>
<head>
<script type="text/javascript" src="${ctx}/js/index/login.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>用户登录</title>
<link href="${ctx}/css/login.css" rel="stylesheet" type="text/css" />
</head>

<style>
	.error{
		color:red;
	}
</style>
<body>
    <div id="login">
	
	     <div id="top">
		      <div id="top_left"><img src="${ctx}/images/login_03.gif" /></div>
			  <div id="top_center"></div>
		 </div>
		 
		 <form action="#" id="loginForm" method="post">
		 	<div id="center">
			      <div id="center_left"></div>
			      
				  <div id="center_middle">
				       <div id="user">用 户
				         <input type="text" name="loginName" class="required"/>
				       </div>
					   <div id="password">密   码
					     <input type="password" name="passWord" class="required"/>
					    	<br><br><span style="color:red;">${loginMessage }</span>
					   </div>
					   <div id="btn">
					   		<a id="loginBtn" href="javaScript:sub();">登录</a>
					   		<a id="indexBtn">回首页</a>
					   	</div>
					   	
				  </div>
				  
				  <div id="center_right"></div>		 
		 	</div>
		 </form>
		 
		 <div id="down">
		      <div id="down_left">
			      <div id="inf">
                       <span class="inf_text">版本信息</span>
					   <span class="copyright">管理信息系统 2018 v2.0</span>
			      </div>
			  </div>
			  <div id="down_center"></div>		 
		 </div>

	</div>
</body>
</html>
