<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <div class="layui-layout layui-layout-admin"/>
  <div class="layui-header">
    <div class="layui-logo">ZhaoYang   朝阳实验室</div>
    <!-- 头部区域  -->
    <ul class="layui-nav layui-layout-left" style="width:100%;font-family:宋体;float:top;"> 
    	<li style="font-size:30px;margin-left:25%;">朝阳实验室实验耗材管理系统</li>
    </ul>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
         	${sessionScope.user.name}
        </a>
        <dl class="layui-nav-child">
          <dd><a href="">基本资料</a></dd>
          <dd><a href="">安全设置</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="${ctx}/UserController/userExit">安全退出</a></li>
    </ul>
  </div>
</body>
</html>