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
<!--     <link rel="stylesheet" href="css/bootstrap.min.css" /> -->
	<link href="css/common.css">
    <script src="js/pageAjax.js" type="text/javascript" charset="utf-8"></script>
    <style type="text/css">
    	.page_box{width:100%;height: auto;padding:10px 0;}
    	.page{width:100%;text-align: center;}
    	.page a{padding:4px 8px;border:1px solid #c60023;border-radius: 4px;color:#333;cursor: pointer;margin-right:5px;text-decoration: none;}
		.page a.page_active,.page a:hover{background: #C60023;color:#fff;}

    </style>
  </head>
  
  <body>
	<div class="page_box">
		<div class="page">
			<a href="javascript:funFirstPage()">首页</a>
			<a href="javascript:funBackPage()">上一页</a>
			
			<c:forEach var="i" begin="1" end="${page.pages}" step="1">
				<a ${page.pageNum eq i?'selected':''} id="a_page" onclick="funGoPage(${i})">${i}</a>
			</c:forEach>
			
			<a href="javascript:funNextPage()">下一页</a>
			<a href="javascript:funLastPage()">尾页</a>
		</div>
	</div>
	
	<script type="text/javascript">
		var url = "${param.url}";
		function funPageSize(value) {
			$("#pageNum").val(1);
			$("#pageSize").val(value);
			pageSubmit(url);
		}
		function funGoPage(value) {
			$("#pageNum").val(value);
			$("#pageSize").val(${page.pageSize});
			pageSubmit(url);
		}
		function funFirstPage() {
			$("#pageNum").val(1);
			$("#pageSize").val(${page.pageSize});
			pageSubmit(url);
		}
		function funBackPage() {
			$("#pageNum").val(${page.prePage eq 0?1:page.prePage});
			$("#pageSize").val(${page.pageSize});
			pageSubmit(url);
		}
		function funNextPage() {
			$("#pageNum").val(${page.nextPage eq 0?page.pages:page.nextPage});
			$("#pageSize").val(${page.pageSize});
			pageSubmit(url);
		}
		function funLastPage() {
			$("#pageNum").val(${page.pages});	
			$("#pageSize").val(${page.pageSize});
			pageSubmit(url);
		}
</script>
	
  </body>
</html>
