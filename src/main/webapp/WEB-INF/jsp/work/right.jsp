<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/base.jsp" %>

<html>
<head>

</head>

<script>
	$(document).ready(function(){
		$("#userExit").on("click",function(){
			$.ajax({
				url:"/lecms_webapp/UserController/userExit",
				success:function(data){

					if(data=="true"||data){
						parent.location.href="/lecms_webapp/home";
					}
				}
			});
		});
	});

</script>

<body leftmargin="0" topmargin="0">
	<img alt="logo" src="${ctx}/images/logo.gif" width="200px" height="64px">
	<h1 style="text-align:center;font-family:宋体;margin-top:-64px">朝阳实验室实验耗材管理系统</h1>
	<div style="margin-top:-30px;position:fixed;right:100px;">
		<span>欢迎您： (<c:choose>
			<c:when test="${sessionScope.user.leval eq 'ORDINARY'}">会员</c:when>
			<c:when test="${sessionScope.user.leval eq 'REPAIR_MAN'}">维护人员</c:when>
			<c:when test="${sessionScope.user.leval eq 'SUPER_ADMIN'}">管理员</c:when>
			<c:otherwise></c:otherwise>
		</c:choose>) ${sessionScope.user.name}</span> &nbsp;&nbsp;&nbsp;
		<span><a id="userExit" href="#" target="_parent" style="text-decoration:none;color:red;">【安全退出】</a></span> 
	</div>
</body>
</html>
