<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>开奖详情 -- 彩票网</title>
<link href="css/common.css" rel="stylesheet" type="text/css">
<link href="css/tab.css" rel="stylesheet" type="text/css">
<script src="js/jquery.min.js"></script>
<link rel="icon" href="front/bo.ico" />

</head>

<body>
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
				<span class="link-text">开奖信息</span>
				<span>&gt;</span>
				<span class="link-text">双色球</span>
			</div>
		</div>
	</div>

	<!--开奖信息-->
	<div class="w page-wrap">
		<div class="tabCon">
			<ul>
				<li class="tabth">
					<span style="width: 25%">奖项名称</span>
					<span style="width: 25%">单项中奖人数</span>
					<span style="width: 25%">单项单注中奖金额</span>
				</li>
			</ul>
			<div id="s1_1">
				<ul class="tabUl">
				<c:forEach items="${detailsList}" var="details">
					<li class="tabtr">
						<span style="width: 25%;">${details.prize}</span>
						<span style="width: 25%;">${details.drawnum}人</span>
						<c:set var="val1" value="占高等奖的${details.drawmoney}"></c:set>
						<c:set var="val2" value="${details.drawmoney}元"></c:set>
						<span style="width: 25%">${details.drawmoney lt 1?val1:val2}</span>
					</li>
				</c:forEach>
					
				</ul>
			</div>
			<div class="page_box">
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

<script src="js/detail.js" type="text/javascript"></script>

  </body>
</html>
