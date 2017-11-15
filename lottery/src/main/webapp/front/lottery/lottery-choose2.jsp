<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>购买彩票 -- 彩票网</title>
<link href="css/common.css" rel="stylesheet" type="text/css">
<link href="css/double.css" rel="stylesheet" type="text/css">
<link href="http://at.alicdn.com/t/font_8d5l8fzk5b87iudi.css"
	rel="stylesheet" type="text/css">

<script src="js/jquery.min.js"></script>
<link rel="icon" href="front/bo.ico" />

</head>

<body>
	<jsp:include page="/front/top.jsp"></jsp:include>

	<!--header-->
	<div class="header">
		<div class="w">
			<a href="type/index" class="logo"> 彩票网 </a>
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
				<a href="type/index" class="link">彩票网</a> <span>&gt;</span> <span
					class="link-text">购买彩票</span>
			</div>
		</div>
	</div>

	<!--选球-->
	<div class="w page-wrap">
		<h3 class="news-title">大乐透</h3>
		<div class="ssq_area">
			<div class="select_ball_area">
				<div class="redball_area">
					<div  class="redball_rule"><strong>红球区</strong>至少选择5个球</div>
					<dl class="redball_selected_area" id="redBall">
						<dd><p>01</p></dd>
						<dd><p>02</p></dd> 
						<dd><p>03</p></dd> 
						<dd><p>04</p></dd>
						<dd><p>05</p></dd> 
						<dd><p>06</p></dd>
						<dd><p>07</p></dd> 
						<dd><p>08</p></dd>
						<dd><p>09</p></dd> 
						<dd><p>10</p></dd> 
						<dd><p>11</p></dd> 
						<dd><p>12</p></dd>
						<dd><p>13</p></dd>
						<dd><p>14</p></dd> 
						<dd><p>15</p></dd> 
						<dd><p>16</p></dd>
						<dd><p>17</p></dd> 
						<dd><p>18</p></dd> 
						<dd><p>19</p></dd> 
						<dd><p>20</p></dd>
						<dd><p>21</p></dd> 
						<dd><p>22</p></dd> 
						<dd><p>23</p></dd> 
						<dd><p>24</p></dd>
						<dd><p>25</p></dd> 
						<dd><p>26</p></dd> 
						<dd><p>27</p></dd> 
						<dd><p>28</p></dd>
						<dd><p>29</p></dd> 
						<dd><p>30</p></dd> 
						<dd><p>31</p></dd> 
						<dd><p>32</p></dd>
						<dd><p>33</p></dd>
						<dd><p>34</p></dd>
						<dd><p>35</p></dd>
					</dl>
					<div class="machine_redselected_area">
						<div class="btn" id="machineSelectedred">机选红球</div>
						<strong onClick="emptyRedballs()" class="link">清空</strong>
					</div>
				</div>
		
				<div class="blueball_area">
					<div  class="blueball_rule"><strong>蓝球区</strong>至少选择2个球</div>
					<ul id="blueBall">
						<li><p>01</p></li>  
						<li><p>02</p></li>  
						<li><p>03</p></li>  
						<li><p>04</p></li>
						<li><p>05</p></li>  
						<li><p>06</p></li>  
						<li><p>07</p></li>  
						<li><p>08</p></li>
						<li><p>09</p></li>  
						<li><p>10</p></li>  
						<li><p>11</p></li>  
						<li><p>12</p></li>
					</ul>
					<div class="machine_blueselected_area dlt_area">
						<div class="btn btn-blue" id="machineSelectedblue">机选蓝球</div>
						<strong onClick="emptyBlueballs()" class="link">清空</strong>
					</div>
				</div>
			</div>
			<div class="confirm_selected_area">
				<div class="confirm_ballnums" id="confirmBallnums">您选了0个红球，0个蓝球</div>
				<div class="confirm_area">
					<div class="confirm_box" id="confirmBox">确认选号</div>
					<div class="empty_box link" onClick="emptyBox()">清空选号</div>
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

<form id="jump" action="front/deal/payment.jsp" method="post">
	<input type="hidden" name="lid" value="${param.lid}"/>
	<input type="hidden" name="lotterid" value="${param.lotterid}"/>
	<input type="hidden" name="buymoney" value="${param.salemoney}"/>
	<input type="hidden" id="buyno" name="buyno"/>
</form>

<script src="js/dlt-detail.js" type="text/javascript"></script>
</body>
</html>
