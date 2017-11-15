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
    
   <title>彩票网 -- 实训</title>
		<!--css-->
		<link rel="stylesheet" href="css/index.css" />
		<link rel="stylesheet" href="css/common.css" />
		<link rel="stylesheet" href="css/tab.css" />
		<!--js-->
		<script src="js/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="https://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
		<script src="js/unslider.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/tab.js" type="text/javascript" charset="utf-8"></script>
		<link rel="icon" href="front/bo.ico" />
  </head>
  
  <script type="text/javascript">
		function jump(lotypename,drawno,lid,lotterid,salemoney) {
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
		
		function changeStr(str) {
			if (str < 10) {
				return '0'+str;
			}
			return str;
		}
		
		function showTime() {
			var date = new Date();
			var year = date.getFullYear();
	        var month = changeStr(date.getMonth() + 1);
	        var day = changeStr(date.getDate());
	        var hour = changeStr(date.getHours());
	        var minute = changeStr(date.getMinutes());
	        var second = changeStr(date.getSeconds());
	        var time = year+'-'+month+'-'+day+' '+hour+':'+minute+':'+second;
	        $(".starttimespan").each(function(){
			    var starttime = $(this).text().substring(0,$(this).text().length-2);
			    if (starttime == time) {
			    	alert('一期彩票已开奖！');
			    	location.href="type/index";
			    }
			});
	        $('#time').val(year+'-'+month+'-'+day+' '+hour+':'+minute+':'+second);
	        setTimeout(showTime, 1000);
		}
		
		showTime();
		
		function lotteryJump(lid,lotypename,salemoney) {
			$('#lid').val(lid);
			$('#lotypename').val(lotypename);
			$('#salemoney').val(salemoney);
			lotterySubmit('lottery/query.do');
		}
	</script>
  
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
					<input id="search-input" class="search-input" placeholder="请输入彩票期号"/>
					<button class=" btn search_btn" id="search-btn">搜索</button>
				</div>
			</div>
		</div>
		
		<!--轮播图-->
		<div class="w">
			<div class="banner" id="b04">
			    <ul>
			        <li><img src="img/banner01.jpg" alt="" width="1080" height="370"></li>
			        <li><img src="img/banner02.jpg" alt="" width="1080" height="370"></li>
			        <li><img src="img/banner03.png" alt="" width="1080" height="370"></li>
			    </ul>
			    <a href="javascript:void(0);" class="unslider-arrow04 prev"><img class="arrow" id="al" src="img/arrowl.png" alt="prev" width="20" height="35"></a>
			    <a href="javascript:void(0);" class="unslider-arrow04 next"><img class="arrow" id="ar" src="img/arrowr.png" alt="next" width="20" height="37"></a>
			</div>
		</div>
		
		<!--选项卡-->
		<div class="w">
			<h1 class="titleH1">
				<span class="left">彩票列表 </span><!-- <input type="text" id="time"/> -->
			</h1>
			<div class="jyTable">
				<!--标题-->
				<ul class="title">
					<c:forEach items="${type}" var="type" varStatus="status">
						<li style="width: 20%" class=${status.index eq 0?'cur':''} onclick="lotteryJump(${type.lid},'${type.lotypename}',${type.salemoney})">${type.lotypename}</li>
					</c:forEach>
				</ul>
				<div class='zong'>
					<div class="list list1">
						<c:forEach items="${type}" var="type">
						<div class="tabCon">
							<ul>
								<li class="tabth">
									<span style="width: 7%">期号</span>
									<span style="width: 10%">开奖时间</span>
									<span style="width: 32%">开奖号码</span>
									<span style="width: 16%">滚存</span>
									<span style="width: 10%">开奖日</span>
									<span style="width: 10%">开奖详情</span>
									<span style="width: 15%">购买彩票</span>
								</li>
							</ul>
							<div id="s1">
								<ul class="tabUl">
									<c:forEach items="${lottery}" var="lottery">
										<c:if test="${lottery.lid eq type.lid}"> 
											<li class="tabtr">
												<span style="width: 7%; color: #F7001E">${lottery.issue}期</span>
												<span class="starttimespan" style="width: 10%;color: #AB9C08">${lottery.starttime}</span>
												<span id="drawnospan" style="width: 32%;color: #067D14">${lottery.drawno}</span>
												<span style="width: 16%">${lottery.accumulated}元</span>
												<span style="width: 10%">${type.lotterdate}</span>
												<a href="javascript:detailJump(${lottery.lotterid})" style="width: 10%">详情</a>
												<a style="width: 15%;color: #F7001E" href="javascript:jump('${type.lotypename}',${lottery.drawno},${type.lid},${lottery.lotterid},${type.salemoney})"><em class="clickbtn hot">购买</em></a>
											</li>
										</c:if>
									</c:forEach>
									</ul>
								</div>
							</div>
						</c:forEach>
					</div><!--list结束-->
				</div>
			</div>
		</div>

		<form id="lotteryForm">
			<input type="hidden" id="lid" name="lid"/>
			<input type="hidden" id="lotypename" name="lotypename"/>
			<input type="hidden" id="salemoney" name="salemoney"/>
		</form>
		
		<form id="detailsForm">
			<input type="hidden" name="lotterid" id="lotterid"/>
		</form>

		

		<!--彩票要闻  向上无间隙滚动-->
		<div class="w">
			<h1 class="titleH1">
				<span class="left">彩票要闻</span>
				<a class="link" href="javascript:newsSubmit('news/query.do')">更多&gt;&gt;</a>
			</h1>
			<div id="scrollDiv">
			  <ul class="news_ul">
			  	<c:forEach var="news" items="${news}">
				    <li>
				    	<a href="news/queryOne.do?newsid=${news.newsid}">${news.title}</a>
				    	<span class="news-time">${news.adddate}</span>
				    </li>
			    </c:forEach>
			  </ul>
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
