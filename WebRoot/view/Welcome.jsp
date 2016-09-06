<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/metro/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
	<script type="text/javascript" src="jquery-easyui-1.3.3/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
	<script type="text/javascript">
		function addTab(title,url){
			$('#tabs').tabs('add',{
				title   :title,
				selected:true,
				closable:true,
				content :"<iframe style='height:100%;width:100%' scrolling='auto' frameborder='0' src='"+ url +"'></iframe>"
			});
		};
	</script>
  </head>
  <body class="easyui-layout">
  		<div data-options="region:'north',title:'North Title',split:true" style="height:100px;"></div>
		<div data-options="region:'west',title:'系统菜单',split:true"style="width: 200px;">
			<!--
			<div id="aa" class="easyui-accordion"style="width: 300px;" data-options="fit:true">
				<div title="Title1" data-options="iconCls:'icon-save'" style="overflow: auto; padding: 10px;">
					<h3 style="color: #0099FF;">
						Accordion for jQuery
					</h3>
					<p>
						Accordion is a part of easyui framework for jQuery. It lets you
						define your accordion component on web page more easily.
					</p>
				</div>
				<div title="Title2" data-options="iconCls:'icon-reload',selected:true" style="padding: 10px;">
					
				</div>
			</div>-->
			<ul id="tt" class="easyui-tree">
				<c:forEach items="${menulist}" var="m2">
					<c:if test="${m2.level == 2}">
						<li>
							<span>${m2.mname}</span>
							<ul>
								<c:forEach items="${menulist}" var="m3">
									<c:if test="${m3.parentid==m2.mid}">
										<li class="m3">
											<a href="javascript:void(0)" onclick="addTab('${m3.mname}','<%=basePath%>${m3.url}');"">${m3.mname}</a>
										</li>
									</c:if>
								</c:forEach>
							</ul>
						</li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
		<div data-options="region:'center'" style="padding:3px;background-color: #eee;">
			<div id="tabs" class="easyui-tabs" data-options="fit:true">
				<div title="欢迎">
				</div>
			</div>
		</div>
	</body>
</html>
