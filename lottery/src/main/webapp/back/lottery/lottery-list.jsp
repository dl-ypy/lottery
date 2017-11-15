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
	<title>彩票列表</title> 
	<link rel="stylesheet" type="text/css" href="back/static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="back/static/h-ui.admin/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="back/lib/Hui-iconfont/1.0.8/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="back/static/h-ui.admin/skin/default/skin.css" id="skin" />
	<link rel="stylesheet" type="text/css" href="back/static/h-ui.admin/css/style.css" />
	<script src="js/jquery.min.js"></script>
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
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 彩票管理 <span class="c-gray en">&gt;</span> 彩票列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="Hui-article">
		<div style="margin-left:150px;">
			<div class="pd-20">
				<div class="text-c">
				<form id="lotteryForm">
					彩种：
					<select name="lid" style="height: 25px;">
						<option value=0>-----请选择-----</option>
						<c:forEach items="${typeList}" var="type">
							<option value="${type.lid}" ${lid eq type.lid?'selected':''}>${type.lotypename}</option>
						</c:forEach>
					</select>
					期号：
					<select name="issue" style="height: 25px;">
						<option value="">-----请选择-----</option>
						<c:forEach items="${issueList}" var="Issue">
							<option value="${Issue.issue}" ${Issue.issue eq issue?'selected':''}>${Issue.issue}</option>
						</c:forEach>
					</select>
					<a class="btn btn-success" onclick="lotterySubmit('lottery/manager_query.do')"><i class="Hui-iconfont">&#xe665;</i>搜索</a>
				</form>
				</div>
				
				<div class="cl pd-5 bg-1 bk-gray mt-20"><span class="r">共有数据：<strong>${page.total}</strong> 条</span> </div>
				<div class="mt-20">
					<table class="table table-border table-bordered table-bg table-hover table-sort">
						<thead>
							<tr class="text-c">
								<th width="40"><input name="" type="checkbox" value=""></th>
								<th width="40">ID</th>
								<th width="40">彩种</th>
								<th width="40">期号</th>
								<th width="100">开奖时间</th>
								<th width="100">开奖号码</th>
								<th width="100">中奖人数</th>
								<th width="60">中奖金额</th>
								<th width="60">此期销售额</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${lotteryList}" var="lottery">
								<tr class="text-c va-m">
									<td><input name="" type="checkbox" value=""></td>
									<td>${lottery.lotterid}</td>
									<td>${lottery.lotypename}</td>
									<td class="text-l">${lottery.issue}</td>
									<td class="text-l">${lottery.starttime}</td>
									<td class="text-l">${lottery.drawno eq 0?'未开奖':lottery.drawno}</td>
									<td>${lottery.drawnum}</td>
									<td>${lottery.drawmoney}</td>
									<td>${lottery.totalsale}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<!-- 分页表单 -->
					<form id="pageForm">
						<input type="hidden" name="pageNum" id="pageNum"/>
						<input type="hidden" name="pageSize" id="pageSize"/>
						<input type="hidden" name="lid" value="${lid}"/>
						<input type="hidden" name="issue" value="${issue}"/>
					</form>
					<jsp:include page="/pageBar.jsp">
						<jsp:param value="lottery/manager_query.do" name="url"/>
					</jsp:include>
				</div>
			</div>
		</div>

	</div>
</section>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="back/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="back/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="back/static/h-ui/js/H-ui.js"></script>
<script type="text/javascript" src="back/static/h-ui.admin/js/H-ui.admin.page.js"></script>
<!--/_footer /作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="back/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="back/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="back/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">
var setting = {
	view: {
		dblClickExpand: false,
		showLine: false,
		selectedMulti: false
	},
	data: {
		simpleData: {
			enable:true,
			idKey: "id",
			pIdKey: "pId",
			rootPId: ""
		}
	},
	callback: {
		beforeClick: function(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("tree");
			if (treeNode.isParent) {
				zTree.expandNode(treeNode);
				return false;
			} else {
				demoIframe.attr("src",treeNode.file + ".html");
				return true;
			}
		}
	}
};

var zNodes =[
	{ id:1, pId:0, name:"一级分类", open:true},
	{ id:11, pId:1, name:"二级分类"},
	{ id:111, pId:11, name:"三级分类"},
	{ id:112, pId:11, name:"三级分类"},
	{ id:113, pId:11, name:"三级分类"},
	{ id:114, pId:11, name:"三级分类"},
	{ id:115, pId:11, name:"三级分类"},
	{ id:12, pId:1, name:"二级分类 1-2"},
	{ id:121, pId:12, name:"三级分类 1-2-1"},
	{ id:122, pId:12, name:"三级分类 1-2-2"},
];

var code;

function showCode(str) {
	if (!code) code = $("#code");
	code.empty();
	code.append("<li>"+str+"</li>");
}

$(document).ready(function(){
	var t = $("#treeDemo");
	t = $.fn.zTree.init(t, setting, zNodes);
	demoIframe = $("#testIframe");
	demoIframe.bind("load", loadReady);
	var zTree = $.fn.zTree.getZTreeObj("tree");
	zTree.selectNode(zTree.getNodeByParam("id",'11'));
});

$('.table-sort').dataTable({
	"aaSorting": [[ 1, "desc" ]],//默认第几个排序
	"bStateSave": true,//状态保存
	"aoColumnDefs": [
	  {"orderable":false,"aTargets":[0,7]}// 制定列不参与排序
	]
});
/*图片-添加*/
function product_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*图片-查看*/
function product_show(title,url,id){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*图片-审核*/
function product_shenhe(obj,id){
	layer.confirm('审核文章？', {
		btn: ['通过','不通过'],
		shade: false
	},
	function(){
		$(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="product_start(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
		$(obj).remove();
		layer.msg('已发布', {icon:6,time:1000});
	},
	function(){
		$(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="product_shenqing(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-danger radius">未通过</span>');
		$(obj).remove();
    	layer.msg('未通过', {icon:5,time:1000});
	});
}
/*图片-下架*/
function product_stop(obj,id){
	layer.confirm('确认要下架吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="product_start(this,id)" href="javascript:;" title="发布"><i class="Hui-iconfont">&#xe603;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已下架</span>');
		$(obj).remove();
		layer.msg('已下架!',{icon: 5,time:1000});
	});
}

/*图片-发布*/
function product_start(obj,id){
	layer.confirm('确认要发布吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="product_stop(this,id)" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
		$(obj).remove();
		layer.msg('已发布!',{icon: 6,time:1000});
	});
}
/*图片-申请上线*/
function product_shenqing(obj,id){
	$(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">待审核</span>');
	$(obj).parents("tr").find(".td-manage").html("");
	layer.msg('已提交申请，耐心等待审核!', {icon: 1,time:2000});
}

</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>