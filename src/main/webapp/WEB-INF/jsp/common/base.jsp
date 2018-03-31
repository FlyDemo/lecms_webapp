<!-- 引入c标签库 -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 定义一个项目变量ctx -->
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<style>
	.error{
		font-size:3px;
		color:red;
	}
</style>

<script type="text/javascript" src="${ctx}/js/jQuery/jquery.js"> </script>
<script type="text/javascript" src="${ctx}/js/jQuery/jquery-3.1.1.js"> </script>
<script type="text/javascript" src="${ctx}/js/jQuery/jquery.validate.js"> </script>
<script type="text/javascript" src="${ctx}/js/jQuery/jquery.metadata.js"> </script>
<script type="text/javascript" src="${ctx}/js/myjs/message_cn.js"> </script>
<script type="text/javascript" src="${ctx}/js/datePlugins/laydate.dev.js"> </script>
<script type="text/javascript" src="${ctx}/js/jQuery/jquery.form.js"> </script>
<script type="text/javascript" src="${ctx}/js/base/base.js"> </script>