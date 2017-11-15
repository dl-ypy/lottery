<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <script src="js/userAjax.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/buyAjax.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/lotteryAjax.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/newsAjax.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/dealAjax.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/detailsAjax.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/bankAjax.js" type="text/javascript" charset="utf-8"></script>
  </head>
  
  <body>
    <div class="nav">
			<div class="w">
				<div class="user-info">
					<span class="user not-login">
						${not empty current_user?'<a class="link" href="user/logout">注销</a>':'<a class="link" href="front/user/user-login.jsp">登录</a>'}
						<a class="link" href="front/user/user-register.jsp">注册</a>
					</span>
					<!-- <span class="user login">
						<span class="link-text">
							欢迎，
							<span class="username"></span>
						</span>
						<a class="link" href="user-login.jsp">退出</a>
					</span> -->
				</div>
				<ul class="nav-list">
					<li class="nav-item">
						<a href="javascript:buySubmit('buy/query.do')" class="link">投注记录</a>
					</li>
					<li class="nav-item">
						<a href="javascript:submit('user/queryOne.do')" class="link">个人信息</a>
					</li>
					<li class="nav-item">
						<a href="front/lottery/about.jsp" class="link">关于彩票</a>
					</li>
				</ul>
			</div>
		</div>
  </body>
</html>
