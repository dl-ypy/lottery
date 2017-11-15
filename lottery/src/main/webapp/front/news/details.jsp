<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
   	<title>彩票要闻 -- 彩票网</title>
	<!--css-->
	<link rel="stylesheet" href="css/common.css" />
	<link rel="stylesheet" href="css/news-detail.css" />
	<!--js-->
	<script src="js/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
	<link rel="icon" href="front/bo.ico" />

  </head>
  
  <body>
    <!--nav-->
		<jsp:include page="/front/top.jsp"></jsp:include>
		
		<!--header-->
		<div class="header">
			<div class="w">
				<a href="type/index" class="logo">彩票网</a>
				<div class="search-con">
					<form id="newsForm">
						<input name="title" class="search-input" placeholder="请输入新闻标题关键字" value="${newsTitle}"/>
						<input name="newsid" type="hidden" value="${news.newsid}"/>
						<a class=" btn search_btn" onclick="newsSubmit('news/query.do')" id="search-btn">搜索</a>
					</form>
				</div>
			</div>
		</div>
		
		<!--面包屑导航-->
		<div class="crumb">
			<div class="w">
				<div class="crumb-con">
					<a href="type/index" class="link">彩票网</a>
					<span>&gt;</span>
					<span class="link-text">彩票要闻</span>
				</div>
			</div>
		</div>
		
		<div class="page-wrap w">
			<!--新闻标题-->
			<h3 class="news-title">${news.title}</h3>
			<div class="news-origin">
				<span>来源：</span>
				<a href="type/index" class="link">彩票网</a>
				<span>编辑：彩票网</span>
			</div>
			
			<!--新闻内容-->
			<div class="news-con">
				${news.content}
			</div>
			
			<!--上一篇，下一篇-->
			<div class="news-footer">
				<span>上一篇：</span>
				<a href="javascript:newsSubmit('news/previous.do')" class="link">上一篇新闻</a>&nbsp;&nbsp;
				<span>下一篇：</span>
				<a href="javascript:newsSubmit('news/next.do')" class="link">下一篇新闻</a>
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
