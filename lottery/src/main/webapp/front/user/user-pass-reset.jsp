<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>找回密码 -- 彩票网</title>
	<!--css-->
	<link rel="stylesheet" href="css/common.css" />
	<link rel="stylesheet" href="css/user-pass-reset.css" />
	<!--js-->
	<script src="js/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/user-pass-reset.js" type="text/javascript" charset="utf-8"></script>
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
					<div class="user-title">找回密码</div>
					<div class="user-box">
						<div class="error-item">
							<i class="fa fa-minus-circle error-icon"></i>
							<p class="err-msg">Error</p>
						</div>
						<!--第一步-->
						<div class="step-phone">
							<p class="user-item-text">请输入手机号码：</p>
							<div class="user-item">
								<label class="user-label">
									<img src="img/phone.png"/>
								</label>
								<input class="user-content" id="phone" placeholder="请输入手机号码" autocomplete="off"/>
							</div>
							<div class="user-item">
								<input class="phone-code" id="phone-code" placeholder="请输入验证码" autocomplete="off"/>
								<button class="btn-code" id="send">获取验证码</button>
							</div>
							<a class="btn btn-submit" id="submit-phone">下一步</a>
						</div>
						<!--第二步-->
						<div class="step-con step-password">
							<p class="user-item-text">请输入新密码：</p>
							<div class="user-item">
							<label class="user-label">
								<img src="img/password.png"/>
							</label>
							<input type="password" class="user-content" id="password" placeholder="请输入新密码" autocomplete="off"/>
							</div>
							<a class="btn btn-submit" id="submit-password">下一步</a>
						</div>
						<div class="link-item">
							<a href="front/user/user-login.jsp" class="link" target="_blank" id="">返回登录&gt;&gt;</a>
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
				CopyRight &copy; 2017 happymmall.com All Right Reserved
			</p>
		</div>
		<script src="https://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
  </body>
</html>
