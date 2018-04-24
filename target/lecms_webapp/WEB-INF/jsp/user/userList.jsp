<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/WEB-INF/jsp/common/base.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<style type="text/css">
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
}

table tr{
	border:1px solid red;
	height:40px;
}

.STYLE1 {
	color: #e1e2e3;
	font-size: 15px;
}
.STYLE6 {color: #000000; font-size: 15; }
.STYLE10 {color: #000000; font-size: 15px; }

.STYLE19 {
	color: #344b50;
	font-size: 15px;
}
.STYLE21 {
	font-size: 15px;
	color: #3b6375;
}
.STYLE22 {
	font-size: 15px;
	color: #295568;
}

</style>


<script>

var  highlightcolor='#d5f4fe';
//此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
var  clickcolor='#51b2f6';
function  changeto(){
	source=event.srcElement;
	if  (source.tagName=="TR"||source.tagName=="TABLE")
	return;
	while(source.tagName!="TD")
	source=source.parentElement;
	source=source.parentElement;
	cs  =  source.children;
	//alert(cs.length);
	if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
	for(i=0;i<cs.length;i++){
		cs[i].style.backgroundColor=highlightcolor;
	}
}

function  changeback(){
	if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
	return
	if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
	//source.style.backgroundColor=originalcolor
	for(i=0;i<cs.length;i++){
		cs[i].style.backgroundColor="";
	}
}

function  clickto(){
	source=event.srcElement;
	if  (source.tagName=="TR"||source.tagName=="TABLE")
	return;
	while(source.tagName!="TD")
	source=source.parentElement;
	source=source.parentElement;
	cs  =  source.children;
	//alert(cs.length);
	if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
	for(i=0;i<cs.length;i++){
		cs[i].style.backgroundColor=clickcolor;
	}
	else
	for(i=0;i<cs.length;i++){
		cs[i].style.backgroundColor="";
	}
}
</script>

<script src="${ctx}/js/user/userList.js"></script>

</head>

<body>
<input type="hidden" name="role" id="role" value="${role}"/>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td  style="background-color:white;" height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" cellspacing="0" cellpadding="0">
              <tr>
                <td width="94%" align="center"><span><c:if test="${role eq 'ORDINARY'}">普通会员信息</c:if><c:if test="${role eq 'REPAIR_MAN'}">维修人员信息</c:if> </span></td>
              </tr>
            </table></td>
            <td><div align="right">
              <span>
	              <img src="${ctx}/images/add.gif" width="10" height="10" /> <a href="${ctx}/UserController/editUser?op=create&leval=${role}" style="text-decoration:none;">添加</a>  &nbsp;&nbsp;&nbsp;
              </span></div></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" onmouseover="changeto()"  onmouseout="changeback()">
      <tr>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">用户名</span></div></td>
        <td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">登陆名</span></div></td>
        <td width="27%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">电子邮箱</span></div></td>
        <td width="16%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">用户角色</span></div></td>
        <td width="14%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">是否启用</span></div></td>
        <td width="14%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">基本操作</span></div></td>
      </tr>
      <c:forEach var="userBean" items="${pageBean.rowDatas}">
	      <tr  id="${userBean.id}" class="userDataListTr">
	        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><span class="STYLE19">${userBean.name}</span></div></td>
	        <td height="20" bgcolor="#FFFFFF" class="STYLE19"<div align="center">${userBean.loginName}</div></td>
	        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${userBean.mail}</div></td>
	        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">
		        <c:choose>
					<c:when test="${userBean.leval eq 'ORDINARY'}">会员</c:when>
					<c:when test="${userBean.leval eq 'REPAIR_MAN'}">维护人员</c:when>
					<c:when test="${userBean.leval eq 'SUPER_ADMIN'}">管理员</c:when>
					<c:otherwise></c:otherwise>
				</c:choose></div></td>
	        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${userBean.deleted=="true"?"否":"是"}</div></td>
	        <td height="20" bgcolor="#FFFFFF"><div align="center" class="STYLE21"><a id="viewBtn" href="#">查看</a> |<a id="editBtn" href="#"> 编辑</a>|<a id="deleteBtn" href="#"><c:if test="${userBean.deleted=='false'}">禁用</c:if> <c:if test="${userBean.deleted=='true'}">启用</c:if></a></div></td>
	      </tr>
      </c:forEach>
    </table></td>
  </tr>
  <input type="hidden" id="currentPage" value="${pageBean.currentPage }"/>
  <input type="hidden" id="totalPage" value="${pageBean.totalPage}"/>
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="33%"><div align="left"><span class="STYLE22">&nbsp;&nbsp;&nbsp;&nbsp;共有<strong>${pageBean.totalCount}</strong> 条记录，当前第<strong> ${pageBean.currentPage }</strong> 页，共 <strong>${pageBean.totalPage }</strong> 页</span></div></td>
        <td width="67%"><table width="312" border="0" align="right" cellpadding="0" cellspacing="0">
          <tr>
            <td width="49"><div align="center" id="firstPage"><img src="${ctx}/images/main_54.gif" width="40" height="15" /></div></td>
            <td width="49"><div align="center" id="proidPage"><img src="${ctx}/images/main_56.gif" width="45" height="15" /></div></td>
            <td width="49"><div align="center" id="nextPage"><img src="${ctx}/images/main_58.gif" width="45" height="15" /></div></td>
            <td width="49"><div align="center" id="endPage"><img src="${ctx}/images/main_60.gif" width="40" height="15" /></div></td>
            <td width="37" class="STYLE22"><div align="center">转到</div></td>
            <td width="22"><div align="center">
              <input type="text" name="gotoPage" id="gotoPage"  style="width:20px; height:12px; font-size:12px; border:solid 1px #7aaebd;"/>
            </div></td>
            <td width="22" class="STYLE22"><div align="center">页</div></td>
            <td width="35"><img id="goto" src="${ctx}/images/main_62.gif" width="26" height="15" /></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
