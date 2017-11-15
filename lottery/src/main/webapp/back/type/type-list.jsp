<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
	<base href="<%=basePath%>">
	<title>彩种列表</title> 
	<link rel="stylesheet" type="text/css" href="back/static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="back/static/h-ui.admin/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="back/lib/Hui-iconfont/1.0.8/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="back/static/h-ui.admin/skin/default/skin.css" id="skin" />
	<link rel="stylesheet" type="text/css" href="back/static/h-ui.admin/css/style.css" />
	<script src="js/typeAjax.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/newsAjax.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/lotteryAjax.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/userAjax.js" type="text/javascript" charset="utf-8"></script>
  </head>
  
  <body>
    <!--_header 作为公共模版分离出去-->
<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top">
		<div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs" href="/aboutHui.shtml">H-ui.admin</a> <a class="logo navbar-logo-m f-l mr-10 visible-xs" href="/aboutHui.shtml">H-ui</a> 
			<span class="logo navbar-slogan f-l mr-10 hidden-xs">v3.0</span> 
			<a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
			<nav class="nav navbar-nav">
				<ul class="cl">
					<li class="dropDown dropDown_hover"><a href="javascript:;" class="dropDown_A"><i class="Hui-iconfont">&#xe600;</i> 新增 <i class="Hui-iconfont">&#xe6d5;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
							<li><a href="javascript:;" onclick="article_add('添加资讯','article-add.html')"><i class="Hui-iconfont">&#xe616;</i> 资讯</a></li>
							<li><a href="javascript:;" onclick="picture_add('添加资讯','picture-add.html')"><i class="Hui-iconfont">&#xe613;</i> 图片</a></li>
							<li><a href="javascript:;" onclick="product_add('添加资讯','product-add.html')"><i class="Hui-iconfont">&#xe620;</i> 产品</a></li>
							<li><a href="javascript:;" onclick="member_add('添加用户','member-add.html','','510')"><i class="Hui-iconfont">&#xe60d;</i> 用户</a></li>
				</ul>
			</li>
		</ul>
	</nav>
			<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
				<ul class="cl">
					<li>超级管理员</li>
					<li class="dropDown dropDown_hover"> <a href="#" class="dropDown_A">${current_manager.username}<i class="Hui-iconfont">&#xe6d5;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
							<li><a href="javascript:;" onClick="myselfinfo()">个人信息</a></li>
							<li><a href="#">切换账户</a></li>
							<li><a href="user/manager_logout">退出</a></li>
				</ul>
			</li>
					<li id="Hui-msg"> <a href="#" title="消息"><span class="badge badge-danger">1</span><i class="Hui-iconfont" style="font-size:18px">&#xe68a;</i></a> </li>
					<li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
							<li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
							<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
							<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
							<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
							<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
							<li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
				</ul>
			</li>
		</ul>
	</nav>
</div>
</div>
</header>
<!--/_header 作为公共模版分离出去-->

<!--_menu 作为公共模版分离出去-->
<aside class="Hui-aside">
	
	<div class="menu_dropdown bk_2">
		<dl id="menu-product">
			<dt><i class="Hui-iconfont">&#xe620;</i> 彩票管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a href="javascript:typeSubmit('type/query.do')" title="彩种管理">彩种管理</a></li>
					<li><a href="javascript:lotterySubmit('lottery/manager_query.do')" title="彩票管理">彩票管理</a></li>
		</ul>
	</dd>
</dl>
		<dl id="menu-comments">
			<dt><i class="Hui-iconfont">&#xe622;</i>用户管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a href="javascript:submit('user/query.do')" title="用户列表">用户列表</a></li>
		</ul>
	</dd>
</dl>
		<dl id="menu-member">
			<dt><i class="Hui-iconfont">&#xe60d;</i> 新闻管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a href="javascript:newsSubmit('news/queryback.do')" title="会员列表">新闻列表</a></li>
		</ul>
	</dd>
</dl>
</div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<!--/_menu 作为公共模版分离出去-->

<section class="Hui-article-box">
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 彩票管理 <span class="c-gray en">&gt;</span> 彩种管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
		
	<div class="Hui-article">
		<article class="cl pd-20">
		
		
			<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"><a href="back/type/type-add.jsp" class="btn btn-success radius"><i class="Hui-iconfont">&#xe6e2;</i>添加</a></span>
			<span class="r">共有数据：<strong>${page.total}</strong> 条</span> </div>
			<div class="mt-10">
				<table class="table table-border table-bordered table-bg table-sort">
					<thead>
						<tr class="text-c">
							<th width="25"><input type="checkbox" name="" value=""></th>
							<th width="70">ID</th>
							<th width="80">名称</th>
							<th width="200">开奖日</th>
							<th width="120">每注价格</th>
							<th width="80">开奖时间</th>
							<th>彩种描述</th>
							<th width="80">彩票状态</th>
							<th width="100">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${typeList}" var="type">
							<tr class="text-c">
								<td><input name="" type="checkbox" value=""></td>
								<td>${type.lid}</td>
								<td>${type.lotypename}</td>
								<td>${type.lotterdate}</td>
								<td>${type.salemoney}</td>
								<td>${type.lottertime}</td>
								<td>${type.lotypedesc}</td>
								<td>${type.status eq 0?'正常':'已下架'}</td>
								<td class="f-14 product-brand-manage">
									<a style="text-decoration:none" onClick="type_out(${type.lid})" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>
									<a style="text-decoration:none" onClick="type_on(${type.lid})" href="javascript:;" title="上线"><i class="Hui-iconfont">&#xe6de;</i></a>
									<a style="text-decoration:none" class="ml-5" onClick="picture_edit('彩种编辑','back/type/type-update.jsp?lid=${type.lid}&lotypename=${type.lotypename}&lotterdate=${type.lotterdate}&lottertime=${type.lottertime}&lotypedesc=${type.lotypedesc}&salemoney=${type.salemoney}',${type.lid})" href="javascript:;" title="编辑"><i class="Hui-iconfont">编辑</i></a>
									<a style="text-decoration:none" class="ml-5" onClick="type_del(${type.lid})" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
									<!-- <a style="text-decoration:none" onClick="product_brand_edit('品牌编辑','codeing.jsp','1')" 
											href="javascript:;" title="编辑">
										<i class="Hui-iconfont">&#xe6df;</i>
									</a> 
									<a style="text-decoration:none" class="ml-5" onClick="active_del(this,'10001')" 
											href="javascript:;" title="删除">
										<i class="Hui-iconfont">&#xe6e2;</i>
									</a> -->
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- 分页表单 -->
				<form id="pageForm">
					<input type="hidden" name="pageNum" id="pageNum"/>
					<input type="hidden" name="pageSize" id="pageSize"/>
				</form>
				<jsp:include page="/pageBar.jsp">
					<jsp:param value="type/query.do" name="url"/>
				</jsp:include>
			</div>
		</article>
	</div>
</section>

<form id="typeForm">
	<input type="hidden" id="lid" name="lid"/>
</form>

<!--_footer 作为公共模版分离出去--> 
<script type="text/javascript" src="back/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="back/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="back/static/h-ui/js/H-ui.js"></script> 
<script type="text/javascript" src="back/static/h-ui.admin/js/H-ui.admin.page.js"></script> 
<!--/_footer /作为公共模版分离出去--> 

<!--请在下方写此页面业务相关的脚本--> 
<script type="text/javascript" src="back/lib/My97DatePicker/4.8/WdatePicker.js"></script>  
<script type="text/javascript" src="back/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
	$('.table-sort').dataTable({
		"aaSorting" : [ [ 1, "desc" ] ], //默认第几个排序
		"bStateSave" : true, //状态保存
		"aoColumnDefs" : [
			//{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
			{
				"orderable" : false,
				"aTargets" : [ 0, 6 ]
			} // 制定列不参与排序
		]
	});
	
	/*图片-编辑*/
function picture_edit(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*删除*/
function type_del(lid){
alert(lid);
	$('#lid').val(lid);
	typeSubmit('type/delete.do');
}

/*下架*/
function type_out(lid){
	$('#lid').val(lid);
	typeSubmit('type/undercarriage.do');
}

/*上线*/
function type_on(lid){
	$('#lid').val(lid);
	typeSubmit('type/live.do');
}
</script>
  </body>
</html>
