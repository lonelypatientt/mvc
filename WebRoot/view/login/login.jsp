<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html lang="en" class="no-js">

<head>

<meta charset="utf-8">
<title>登录或注册</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- CSS -->

<link rel="stylesheet" href="<%=basePath%>view/login/css/supersized.css">
<link rel="stylesheet" href="<%=basePath%>view/login/css/login.css">
<link rel="stylesheet" href="<%=basePath%>view/login/css/bootstrap.min.css">
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
	<script src="view/js/html5.js"></script>
<![endif]-->
<script type="text/javascript" src="<%=basePath%>view/login/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>view/login/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=basePath%>view/login/js/tooltips.js"></script>
<script type="text/javascript" src="<%=basePath%>view/login/js/login.js"></script>
<script type="text/javascript">
	//$(function(){
		function login(){
			$.post("login.do",{
				methodName  :'login',
				userName    :$("#name").val(),
				userPassword:$("#password").val()
			},function(data){
				if(data == 1){
					window.location.href = "view/Welcome.jsp";
				}else if(data == 2){
					$("#loginError").html("密码错误！");
				}else if(data == 3){
					$("#loginError").html("用户名不存在！");
				}
			},"text");
		};
	//});
</script>
</head>

<body>

<div class="page-container">
	<div class="main_box">
		<div class="login_box">
			<div class="login_logo">
				<h2>欢迎登录</h2>
				<p style="font-size:18px;color:red" id="loginError">${loginError }</p>
			</div>
		
			<div class="login_form">
				<form action="login.do" id="login_form" method="post">
					<input type="hidden" name="methodName" value="login"/>
					<div class="form-group">
						<label for="j_username" class="t">帐　号：</label> 
						<input id="name" value="" name="userName" type="text" class="form-control x319 in" 
						autocomplete="off">
					</div>
					<div class="form-group">
						<label for="j_password" class="t">密　码：</label> 
						<input id="password" value="" name="userPassword" type="password" 
						class="password form-control x319 in">
					</div>
					
					<div class="form-group">
						<label class="t"></label>
						<label for="j_remember" class="m">
						<input id="j_remember" type="checkbox" value="true">&nbsp;记住登陆账号!</label>
					</div>
					<div class="form-group space">
						<label class="t"></label>　　　
						<input type="button"  id="submit_btn" onclick = "login();" class="btn btn-primary btn-lg" value="&nbsp;登&nbsp;录&nbsp;"/> 
						<input type="reset" value="&nbsp;重&nbsp;置&nbsp;" class="btn btn-default btn-lg">
						<a href="view/regisiter.jsp">没有帐号？立即注册</a>
					</div>
				</form>
			</div>
		</div>
		<div class="bottom">Copyright &copy; 2014 - 2017 </div>
	</div>
</div>

<!-- Javascript -->

<script src="<%=basePath%>view/login/js/supersized.3.2.7.min.js"></script>
<script src="<%=basePath%>view/login/js/supersized-init.js"></script>
<script src="<%=basePath%>view/login/js/scripts.js"></script>
<div style="text-align:center;">
</div>
</body>
</html>