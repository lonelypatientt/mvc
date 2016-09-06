<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<script src="view/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript"><!--
		/*function test(){
			//1.创建xmlHttpRequest对象
			var xmlHttpRequest;
			//IE
			if(window.ActiveXObject){
				xmlHttpRequest = new window.ActiveXObject("Microsoft.XMLHTTP");
			}
			//DOM
			else if(window.XMLHttpRequest){
				xmlHttpRequest = new XMLHttpRequest();
			}
			//2.打开链接
			xmlHttpRequest.open("post","testAJAX.do?methodName=testAJAX",true);
			//3.创建回调函数
			xmlHttpRequest.onreadystatechange = function(){
				if(xmlHttpRequest.readyState ==4 ){
					if(xmlHttpRequest.status == 200){
						alert("AJAX测试！");
					}
				}
			}
			//4.发送请求
			xmlHttpRequest.send(null);
		}*/
		
		$(function(){
			$(".bt").click(function(){
				/*$.ajax({
					type    : 'POST',
					url     : 'testAJAX.do',
					data    : {methodName:'testAJAX'},
					success : function(data){
						alert(data);
					}
				});
			
				$.get('testAJAX.do',{methodName:'testAJAX'},function(data){
					alert(data);
				},"text");
				
				$.getJSON('testAJAX.do',{methodName:'testAJAX'},function(data){
					alert(data.address +"---"+ data.name);
				});*/
				
				$.post('testAJAX.do',{methodName:'testAJAX'},function(data){
					//将json格式的字符串转换为json对象
					var o = eval("(" + data +")");
					alert(o.address);
					//alert(data.name);
				},"text");
			});
		});
		
	</script>


  </head>
  
  <body>
  
  <input type="button" class="bt" value="jQuery.ajax测试" />
  
  
  
  </body>
</html>
