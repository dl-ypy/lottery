<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登陆  -- 彩票网</title>
		<!--css-->
		<link rel="stylesheet" href="css/common.css" />
		<link rel="stylesheet" href="css/user-login.css" />
		<!--js-->
		<script src="js/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/user-login.js" type="text/javascript" charset="utf-8"></script>
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
					<div class="user-title">用户登录</div>
					<div class="user-box">
						<div class="error-item">
							<img src="img/error.png" class="error-icon"/>
							<p class="err-msg">Error</p>
						</div>
							<div class="user-item">
								<label for="" class="user-label">
									<img src="img/username.png"/>
								</label>
								<input class="user-content" id="username" name="username" placeholder="请输入用户名或电话号码" autocomplete="off"/>
							</div>
							<div class="user-item">
								<label for="password" class="user-label">
									<img src="img/password.png"/>
								</label>
								<input type="password" id="password" class="user-content" name="password" placeholder="请输入密码" autocomplete="off"/>
							</div>
							<a class="btn btn-submit" href="javascript:submit('user/login.do')">登录</a>
						<div class="link-item">
							<input type="checkbox" id="isPhone" onclick="isPhone()"/><span class="checkboxSpan" style="color:blue;front-size:10px;padding-right: 130px;">通过手机号登录</span>
							<a href="front/user/user-pass-reset.jsp" class="link" target="_blank">忘记密码</a>
							<a href="front/user/user-register.jsp" class="link" target="_blank">免费注册</a>
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
