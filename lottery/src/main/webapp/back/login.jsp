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
		<link rel="stylesheet" href="css/admin-common.css" />
		<link rel="stylesheet" href="css/admin-login.css" />
		<!--js-->
		<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="static/h-ui/js/H-ui.js"></script>
		<script src="js/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/user-login.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/userAjax.js" type="text/javascript" charset="utf-8"></script>
		<link rel="icon" href="front/bo.ico" />
  </head>
  
  <body>
			<div class="page-wrap" style="background:url(img/timg.jpg) 50% 43% no-repeat">
			<div class="w">
				<div class="user-con">
					<div class="user-title">管理员登录</div>
					<div class="user-box">
						<div class="error-item">
							<img src="img/error.png" class="error-icon"/>
							<p class="err-msg">Error</p>
						</div>
						<form method="post" >
							<div class="user-item">
								<label for="" class="user-label">
									<img src="img/username.png"/>
								</label>
								<input class="user-content" id="username" name="username" placeholder="请输入管理员名字" autocomplete="off"/>
							</div>
							<div class="user-item">
								<label for="password" class="user-label">
									<img src="img/password.png"/>
								</label>
								<input type="password" id="password" class="user-content" name="password" placeholder="请输入密码" autocomplete="off"/>
							</div>
					<div class="state">
						<label for="online">
						<input type="checkbox" name="online" id="online" value="">
						使我保持登录状态</label>
					</div>
		
					<div class="formControls">
						<a class="btn btn-success " onclick="submit('user/manager_login.do')">&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;</a>
						<input name="" type="reset" class="btn btn-default " value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
					</div>
						
					</form>		
					</div>
				</div>
			</div>
	
		<div class="footer">
		
			<p class="copyright">
				CopyRight &copy; 2017 lottery.com All Right Reserved
			</p>
		</div>
			
	</div>
</body>
</html>
