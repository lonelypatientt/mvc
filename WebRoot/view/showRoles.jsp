<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showUsers.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
      <h3>角色列表</h3>
      <p style="color:#c00;">${msg}</p>
      <table border="1" width="100%" bordercolor="blue" cellspacing="0" cellpadding="0">
      	  <tr>
      	  	  <td>编号</td>
      	  	  <td>菜单名称</td>
      	  	  <td>操作</td>
      	  </tr>
      	  
      	  <c:forEach items="${roleList}" var="r">
      	  	<tr>
      	  		<td>${r.rid}</td>
      	  		<td>${r.rname}</td>
      	  		<td>
      	  			<a href="<%=basePath%>showRoleUser.do?methodName=showRoleUser&rid=${r.rid}">修改角色用户</a>
      	  			<a href="<%=basePath%>loadRoleMenu.do?methodName=loadRoleMenu&rid=${r.rid}">修改角色菜单</a>
      	  		</td>
      	  	</tr>
      	  </c:forEach>
      </table>
  </body>
</html>
