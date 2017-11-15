<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		<link rel="stylesheet" href="css/news.css" />
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
					<form id="newsForm">
						<div class="search-con">
							<input name="title" class="search-input" placeholder="请输入新闻标题关键字" value="${newsTitle}"/>
							<a class="btn search_btn" onclick="newsSubmit('news/query.do')" id="search-btn">搜索</a>
						</div>
					</form>
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
			<h3 class="news-title">彩票要闻</h3>
			<ul class="news_ul">
			    <c:forEach var="news" items="${news}">
				    <li>
				    	<a href="news/queryOne.do?newsid=${news.newsid}">${news.title}</a>
				    	<span class="news-time">${news.adddate}</span>
				    </li>
			    </c:forEach>
		    </ul>
			<jsp:include page="/pageBar.jsp">
				<jsp:param value="news/query.do" name="url"/>
			</jsp:include>
			<%-- <div class="page_box">
				<div class="page">
					<a>上一页</a>
					<a class="page_active">1</a>
					共<i class="blue">${page.pages}</i>页
					<a>下一页</a>
				</div>
			</div> --%>
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
		
		<!-- 分页表单 -->
		<form id="pageForm">
			<input type="hidden" name="pageNum" id="pageNum"/>
			<input type="hidden" name="pageSize" id="pageSize"/>
			<input type="hidden" name="title" value="${newsTitle}"/>
		</form>
  </body>
</html>
