<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<style>
.layui-footer{
	text-align:center;
	font-size:1px;
	padding: 0 15px;
}

a{
	text-decoration:none;
}

a.myCard {
    position: relative;
}

.myCard img {
  position: absolute;
  z-index: 999;
  top: -135px;
  right: -28px;
  width: 7.5rem;
  max-width: none;
  height: 7.5rem;
  transform: scale(0);
  transform-origin: top right;
  opacity: 0;
  border: .3125rem solid rgb(57,61,73);
  border-radius: .25rem;
  -webkit-transition: all .4s ease-in-out;
  -o-transition: all .4s ease-in-out;
  transition: all .4s ease-in-out;

}

.myCard:hover img {
    transform: scale(1);
    opacity: 1;
}
</style>

<body>
	<div class="layui-footer" style="line-height:20px">
		
	    <!-- 底部固定区域 -->
	    © <a href="${ctx}/home" target="_parent">朝阳实验室</a>	<br>
	    © <a href="http://www.xawl.org/" target="_black">西安文理学院</a> 2018届毕业生
	    
	    <a class="social myCard" href="https://oneseekers.github.io/Blog/"  target="_black">【刘宏飞】 
			<img class="qrcode" src="${ctx}/images/myCard.jpg" alt="我的二维码">
		</a>毕业设计  版权仅归开发者所有 ，没有允许，请勿使用
    </div>
</body>
</html>