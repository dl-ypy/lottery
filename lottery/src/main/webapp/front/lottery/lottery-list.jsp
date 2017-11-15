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
    <title>彩票信息  -- 彩票网</title>
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
				<form id="lotteryForm">
					<input type="hidden" name="lid" value="${lid}"/>
					期号：
					<select name="issue">
						<option value="">-----请选择-----</option>
						<c:forEach items="${issueList}" var="Issue">
							<option value="${Issue.issue}" ${Issue.issue eq issue?'selected':''}>${Issue.issue}</option>
						</c:forEach>
					</select>
				</form>
				<button class=" btn search_btn" id="search-btn" onclick="lotterySubmit('lottery/query.do')">搜索</button>
				<a class="btn btn-mini" id="search-btn" onclick="lotterySubmit('lottery/query.do')">搜索</a>
				<button class=" btn search_btn" id="search-btn" style="visibility: hidden;">搜索</button>
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
				<span class="link-text">${lotypename}</span>
			</div>
		</div>
	</div>

	<!--开奖信息-->
	<div class="w page-wrap">
		<div class="tabCon">
			<ul>
				<li class="tabth">
					<span style="width: 7%">期号</span>
					<span style="width: 10%">开奖时间</span>
					<span style="width: 32%">开奖号码</span>
					<span style="width: 16%">滚存</span>
					<span style="width: 10%">开奖详情</span>
					<span style="width: 15%">购买彩票</span>
				</li>
			</ul>
			<div id="s1_1">
				<ul class="tabUl">
					<c:forEach items="${lotteryList}" var="lottery">
						<li class="tabtr">
							<span style="width: 7%; color: #F7001E">${lottery.issue}期</span>
							<span class="starttimespan" style="width: 10%;color: #AB9C08">${lottery.starttime}</span>
							<span id="drawnospan" style="width: 32%;color: #067D14">${lottery.drawno}</span>
							<span style="width: 16%">${lottery.accumulated}元</span>
							<span style="width: 10%">${type.lotterdate}</span>
							<span style="width: 10%"><a href="javascript:detailJump(${lottery.lotterid})">详情</a></span>
							<a style="width: 15%;color: #F7001E" href="javascript:jump('${lotypename}',${lottery.drawno},${lottery.lid},${lottery.lotterid},${salemoney})"><em class="clickbtn hot">购买</em></a>
						</li>
					</c:forEach>
				</ul>
			</div>
			<jsp:include page="/pageBar.jsp">
				<jsp:param value="lottery/query.do" name="url"/>
			</jsp:include>
			
			<form id="pageForm">
				<input type="hidden" name="pageNum" id="pageNum"/>
				<input type="hidden" name="pageSize" id="pageSize"/>
				<input type="hidden" name="lid" value="${lid}"/>
				<input type="hidden" name="issue" value="${issue}"/>
			</form>
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
	
	<form id="detailsForm">
		<input type="hidden" name="lotterid" id="lotterid"/>
	</form>
	
	<script type="text/javascript">
		function jump(lotypename,drawno,lid,lotterid,salemoney) {
			alert(lotypename);
			if (drawno == 0) {
				if (lotypename == '双色球') {
					location.href="front/lottery/lottery-choose.jsp?lid="+lid+"&lotterid="+lotterid+"&salemoney="+salemoney;
				}
				if (lotypename == '大乐透') {
					location.href="front/lottery/lottery-choose2.jsp?lid="+lid+"&lotterid="+lotterid+"&salemoney="+salemoney;
				}
			} else {
				alert('此彩票已开奖完毕，请购买最新一期！');
			}
		}
		
		function detailJump(lotterid) {
			$('#lotterid').val(lotterid);
			detailsSubmit('details/query.do');
		}
	</script>

<script src="js/detail.js" type="text/javascript"></script>
  </body>
</html>
