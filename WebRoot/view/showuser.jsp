<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//List<Object[]> list = (List<Object[]>)request.getAttribute("show");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		#tab{
			border: 1px;
		}
	</style>

  </head>
  <body>
  	<form action="showuser.do?methodName=showUser">
    <table class="tab">
    	<p><h1>用户列表</h1></p>
    	<p>
    		<fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH:mm:ss"/>
    	</p>
    	<tr>
    		<td>编号</td>
    		<td>用户名</td>
    		<td>用户密码</td>
    		<td>操作</td>
    	</tr>
    	<c:forEach items="${show}" var="u">
			<tr>
				<td>${u.uid}</td>
				<td>${u.userName}</td>
				<td>${u.userPassword}</td>
				<td>
    				<a href='deleteUser.do?uid=${u.uid}'>删除</a>
				</td>
			</tr>    	
    	</c:forEach>
    	
    </table>
    </form>
  </body>
