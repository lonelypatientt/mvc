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
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/metro/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
	<script type="text/javascript" src="jquery-easyui-1.3.3/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
		$(function($) {
 			$('#dg').datagrid({    
   		    url:'showMenus.do?methodName=showMenus&pageNo=1&pageSize=10', 
   		     frozenColumns:[[{field:'ahnds',checkbox:true}]], 
   		     rownumbers:true,  
	    	columns:[[    
	        {field:'mid',title:'编号',width:100,align:'center',hidden:true},    
	        {field:'mname',title:'菜单名称',width:100,align:'center'},    
	        {field:'url',title:'菜单url',width:300,align:'center'},  
	        {field:'isshow',title:'是否展示',width:100,align:'center'},
	        {field:'level',title:'等级',width:100,align:'center'},   
	        {field:'parentName',title:'父级菜单',width:100,align:'center'} 
	    ]],
			toolbar: [{
				text:'添加菜单',
				iconCls: 'icon-edit',
				handler: function(){alert('添加菜单')}
			},'-',{
				text:'删除菜单',
				iconCls: 'icon-delete',
				handler: function(){alert('删除菜单')}
			},'-',{
				text:'刷新菜单',
				iconCls: 'icon-refresh',
				handler: function(){alert('刷新菜单')}
			}]
			    	    
		});
		var pager = $('#dg').datagrid("getPager");
		pager.pagination({
			onSelectPage:function(pageNo,pageSize){
				$('#dg').datagrid('loading');
				$.post("showMenus.do",{
					methodName:'showMenus',
					pageNo:pageNo,
					pageSize:pageSize
				},function(data){
					$("#dg").datagrid("loadData",{
						rows:data.rows,
						total:data.total
					});	
				},"json");
				$('#dg').datagrid('loaded');
			}
		});
 
		});
	</script>


  </head>
  
  <body>
      <table id="dg"></table>
  </body>
</html>
