<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
   <title>注册 -- 彩票网</title>
		<!--css-->
		<link rel="stylesheet" href="css/common.css" />
		<link rel="stylesheet" href="css/user-register.css" />
		<!--js-->
		<script src="js/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/user-register.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/userAjax.js" type="text/javascript" charset="utf-8"></script>
		<link rel="icon" href="front/bo.ico" />
  </head>
  
  <body>
    <div class="nav-simple">
			<div class="w">
				<a class="logo" href="type/index">彩票网</a>
			</div>
		</div>
		<div class="page-wrap">
			<div class="w">
				<div class="user-con">
					<div class="user-title">用户注册</div>
					<div class="user-box">
						<div class="error-item">
							<img src="img/error.png" class="error-icon"/>
							<p class="err-msg">Error</p>
						</div>
							<!--用户名-->
							<div class="user-item">
								<label for="username" class="user-label">
									<img src="img/username.png"/>
								</label>
								<input class="user-content" id="username" placeholder="请输入用户名" autocomplete="off"/>
							</div>
							<!--真实姓名-->
							<div class="user-item">
								<label for="truename" class="user-label">
									<img src="img/username.png"/>
								</label>
								<input class="user-content" id="truename" placeholder="请输入真实姓名" autocomplete="off"/>
							</div>
							<!--密码-->
							<div class="user-item">
								<label for="password" class="user-label">
									<img src="img/password.png"/>
								</label>
								<input type="password" class="user-content" id="password" placeholder="请输入密码" autocomplete="off"/>
							</div>
							<!--确认密码-->
							<div class="user-item">
								<label for="password-confirm" class="user-label">
									<img src="img/password.png"/>
								</label>
								<input type="password" class="user-content" id="password-confirm" placeholder="请再次输入密码" autocomplete="off"/>
							</div>
							<!--手机号-->
							<div class="user-item">
								<label for="phone" class="user-label">
									<img src="img/phone.png"/>
								</label>
								<input class="user-content" id="phone" placeholder="请输入手机号" autocomplete="off"/>
							</div>
							<a class="btn btn-submit" id="submit" href="javascript:submit('user/register.do')">立即注册</a>
						<div class="link-item">
							<a href="front/user/user-login.jsp" class="link">已有账号，去登录&gt;&gt;</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="footer">
			<div class="w">
				<div class="links">
					<a href="http://www.baidu.com" class="link" target="_blank">百度</a> |
					<a href="http://www.imooc.com" class="link" target="_blank">慕课网</a> |
					<a href="http://www.taobao.com" class="link" target="_blank">淘宝</a> |
					<a href="http://www.zhihu.com" class="link" target="_blank">知乎</a>
				</div>
			</div>
			<p class="copyright">
				CopyRight &copy; 2017 lottery.com All Right Reserved
			</p>
		</div>
  </body>
</html>
