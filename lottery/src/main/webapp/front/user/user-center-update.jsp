<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>个人中心  -- 彩票网</title>
	<!--css-->
	<link rel="stylesheet" href="css/common.css" />
	<link rel="stylesheet" href="css/user-center.css" />
	<!--js-->
	<script src="js/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="https://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<link rel="icon" href="front/bo.ico" />

  </head>
  
  <body>
    <!--nav-->
		<jsp:include page="/front/top.jsp"></jsp:include>
		
		<!--header-->
		<div class="header">
			<div class="w">
				<a href="type/index" class="logo">
					彩票网
				</a>
				<div class="search-con">
					<input id="search-input" class="search-input" placeholder="请输入彩票名称" />
					<button class=" btn search_btn" id="search-btn">搜索</button>
				</div>
			</div>
		</div>
		
		<!--面包屑导航-->
		<div class="crumb">
			<div class="w">
				<div class="crumb-con">
					<a href="type/index" class="link">彩票网</a>
					<span>&gt;</span>
					<span class="link-text">个人中心</span>
				</div>
			</div>
		</div>
		
		<div class="page-wrap w">
			<!--左边菜单栏-->
			<ul class="nav-side">
				<li class="nav-item active">
					<a href="javascript:submit('user/queryOne.do')" class="link">个人信息</a>
				</li>
				<li class="nav-item">
				    <a href="front/user/user-pass-update.jsp" class="link">修改密码</a>
				</li>
				<li class="nav-item">
				    <a href="front/user/user-binding.jsp" class="link">绑定支付宝</a>
				</li>
				<li class="nav-item"><a href="javascript:dealSubmit('deal/user_query.do')" class="link">交易记录</a>
				</li>
				<li class="nav-item">
					<a href="javascript:dealSubmit('deal/user_query.do')" class="link">投注记录</a>
				</li>
			</ul>
			
			<!--右侧内容区-->
			<div class="content with-nav">
				<div class="panel">
					<div class="panel-title">个人中心</div>
					<div class="panel-body">
						<div class="user-info">
							<div class="form-line">
								<input type="hidden" id="userid" value="${user.userid}"/>
								<span class="label">用 户 名:</span>
								<input class="input" type="text" readonly="readonly" value="${user.username}"/>
							</div>
							<div class="form-line">
								<span class="label">真实姓名:</span>
								<input class="input" id="truename" autocomplete="off" value="${user.truename}" />
							</div>
							<div class="form-line">
								<span class="label">手 机 号:</span>
								<input class="input" id="phone" autocomplete="off" value="${user.phone}" />
							</div>
							<a class="btn btn-submit" href="javascript:submit('user/updatePwd.do')">提交</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<!--footer-->
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
