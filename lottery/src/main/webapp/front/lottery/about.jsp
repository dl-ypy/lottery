<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>关于彩票  -- 彩票网</title>
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
				<a href="front/index.jsp" class="logo">
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
					<a href="front/index.jsp" class="link">彩票网</a>
					<span>&gt;</span>
					<span class="link-text">修改密码</span>
				</div>
			</div>
		</div>
		
		<div class="page-wrap w">
			<!--左边菜单栏-->
			<ul class="nav-side">
				<li class="nav-item ">
					<a href="front/user/user-center.jsp" class="link">个人中心</a>
				</li>
				<li class="nav-item">
				    <a href="front/lottery/order-list.jsp" class="link">投注记录</a>
				</li>
				<li class="nav-item">
				    <a href="front/user/user-pass-update.jsp" class="link">修改密码</a>
				</li>
				<li class="nav-item active">
				    <a href="front/lottery/about.jsp" class="link">关于彩票</a>
				</li>
			</ul>
			
			<!--右侧内容区-->
			<div class="content with-nav">
				<div class="panel">
					<div class="panel-title">关于彩票</div>
					<div class="panel-body">
						<p>中国福利彩票双色球游戏规则</p>
							<p>第一章　总则</p>
							<p>第一条  根据《彩票管理条例》、《彩票管理条例实施细则》、《彩票发行销售管理办法》（财综[2012]102号）等有关规定，制定本规则。</p>
							<p>第二条  中国福利彩票双色球游戏（以下简称双色球）由中国福利彩票发行管理中心（以下称中福彩中心）发行和组织销售，由各省、自治区、直辖市福利彩票销售机构（以下称各省福彩机构）在所辖区域内销售。</p>
							<p>第三条  双色球采用计算机网络系统发行，在各省福彩机构设置的销售网点销售，定期开奖。</p>
							<p>第四条  双色球实行自愿购买，凡购买者均被视为同意并遵守本规则。</p>
							<p>第五条  不得向未成年人销售彩票或兑付奖金。</p>
							<p>第二章  投注</p>
							<p>第六条  双色球投注区分为红色球号码区和蓝色球号码区，红色球号码区由1-33共三十三个号码组成，蓝色球号码区由1-16共十六个号码组成。投注时选择6个红色球号码和1个蓝色球号码组成一注进行单式投注，每注金额人民币2元。</p>
							<p>第七条  购买者可在各省福彩机构设置的销售网点投注。投注号码经投注机打印出对奖凭证，交购买者保存，此对奖凭证即为双色球彩票。</p>
							<p>第八条  购买者可选择机选号码投注、自选号码投注。机选号码投注是指由投注机随机产生投注号码进行投注，自选号码投注是指将购买者选定的号码输入投注机进行投注。</p>
							<p>第九条  购买者可选择复式投注。复式投注是指所选号码个数超过单式投注的号码个数，所选号码可组合为每一种单式投注方式的多注彩票的投注。具体规定如下：</p>
							<p>（一）红色球号码复式：是指从红色球号码中选择7个号码以上（含7个号码），从蓝色球号码中选择1个号码，组合为多注单式投注号码的投注；</p>
							<p>（二）蓝色球号码复式：是指从红色球号码中选择6个号码,从蓝色球号码中选择2个号码以上（含2个号码），组合为多注单式投注号码的投注；</p>
							<p>（三）全复式：是指从红色球号码中选择7个号码以上（含7个号码），从蓝色球号码中选择2个号码以上（含2个号码），组合为多注单式投注号码的投注。</p>
							<p>第十条  购买者可对其选定的投注号码进行多倍投注，投注倍数范围为2-99倍。单张彩票的投注金额最高不得超过20000元。</p>
							<p>第十一条  双色球按期销售，每周销售三期，期号以开奖日界定，按日历年度编排。</p>
							<p>第十二条  若因销售终端故障、通讯线路故障和投注站信用额度受限等原因造成投注不成功，应退还购买者投注金额。</p>
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
