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
   <title>投注记录  -- 彩票网</title>
	<!--css-->
	<link rel="stylesheet" href="css/common.css" />
	<link rel="stylesheet" href="css/order-list.css" />
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
				<a href="type/index" class="logo">
					彩票网
				</a>
				<div class="search-con">
					<input id="search-input" class="search-input" placeholder="请输入彩票名称" style="visibility: hidden;">
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
					<span class="link-text">投注记录</span>
				</div>
			</div>
		</div>
		
		<div class="page-wrap w">
			<!--左边菜单栏-->
			<ul class="nav-side">
				<li class="nav-item">
					<a href="javascript:submit('user/queryOne.do')" class="link">个人信息</a>
				</li>
				<li class="nav-item">
				    <a href="front/user/user-pass-update.jsp" class="link">修改密码</a>
				</li>
				<li class="nav-item">
				    <a href="front/user/user-binding.jsp" class="link">绑定支付宝</a>
				</li>
				<li class="nav-item"><a href="javascript:dealSubmit('deal/user_query.do')" class="link">交易记录</a>
				</li>
				<li class="nav-item active">
					<a href="javascript:buySubmit('buy/query.do')" class="link">投注记录</a>
				</li>
			</ul>
			
			<div class="content with-nav">
				<div class="panel">
					<div class="panel-title">投注记录</div>
					<form id="buyForm" style="margin-top: 30px;text-align: center;">
						彩种：
						<select name="lid">
							<option value=0>-----请选择-----</option>
							<c:forEach items="${typeList}" var="type">
								<option value="${type.lid}" ${lid eq type.lid?'selected':''}>${type.lotypename}</option>
							</c:forEach>
						</select>
						期号：
						<select name="issue">
							<option value="">-----请选择-----</option>
							<c:forEach items="${issueList}" var="Issue">
								<option value="${Issue.issue}" ${Issue.issue eq issue?'selected':''}>${Issue.issue}</option>
							</c:forEach>
						</select>
						是否中奖：
						<select name="isdraw">
							<option value="">-----请选择-----</option>
							<c:forEach items="${isDrawList}" var="isDraw">
								<option value="${isDraw.isdraw}" ${isDraw.isdraw eq isdraw?'selected':''}>${isDraw.isdraw}</option>
							</c:forEach>
						</select>
						<a class="btn btn-mini" id="search-btn" onclick="buySubmit('buy/query.do')">搜索</a>
					</form>
					<div class="panel-body">
						<div class="order-list-con">
							<table class="order-list-table header">
								<tr>
									<th class="cell cell-img">&nbsp;</th>
									<th class="cell cell-info">彩票信息</th>
									<th class="cell cell-price">购买金额总计：</th>
									<th><span class="order-total">${not empty buyTotalMoney?buyTotalMoney:'0'}元</span></th>
									<th class="cell cell-total">中奖金额总计：</th>
									<th><span class="order-total">${not empty drawTotalMoney?drawTotalMoney:'0'}元</span></th>
								</tr>
							</table>
							<table class="order-list-table order-item">
								<c:forEach items="${buyList}" var="buy">
									<tr>
										<td colspan="5" class="order-info">
											<span class="order-text">
												<span>编号：</span>
												<span>${buy.bid}</span>
											</span>
											
											<span class="order-text">
												<span>彩票类型：</span>
												<span>${buy.lotypename}</span>
											</span>
											<span class="order-text">
												<span>彩票期号：</span>
												<span>${buy.issue}期</span>
											</span>
												<input type="hidden" id="lotterid" value="${buy.lotterid}"/>
												<a class="link order-detail" href="javascript:detailJump(${buy.lotterid})" target="_blank">查看此期详情&gt;</a>
										</td>
									</tr>
									<tr>
										<td class="cell">
											<span class="order-text">
												<span>交易时间：</span>
												<span class="order-total">${buy.buydate}</span>
											</span>
										</td>
										<td class="cell">
											<span class="order-text">
												<span>交易金额：</span>
												<span class="order-total">${buy.buymoney}元</span>
											</span>
										</td>
										<td class="cell">
											<span class="order-text">
												<span>所选号码：</span>
												<span class="order-total">${buy.buyno}</span>
											</span>
										</td>
										<td class="cell">
											<span class="order-text">
												<span>中奖情况：</span>
												<span class="order-total">${buy.isdraw}</span>
											</span>
										</td>
									</tr>
								</c:forEach>
							</table>
							<p class="err-tip">${not empty buyList?'':'您暂时还没有订单'}</p>
						</div>
					</div>
					<jsp:include page="/pageBar.jsp">
						<jsp:param value="buy/query.do" name="url"/>
					</jsp:include>
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
		
		<form id="detailsForm">
			<input type="hidden" name="lotterid" id="lotterid2"/>
		</form>
		
		<script type="text/javascript">
			function detailJump(lotterid) {
				$('#lotterid2').val(lotterid);
				detailsSubmit('details/query.do');
			}
		</script>
		
		<form id="pageForm">
			<input type="hidden" name="pageNum" id="pageNum"/>
			<input type="hidden" name="pageSize" id="pageSize"/>
			<input type="hidden" name="lid" value="${lid}"/>
			<input type="hidden" name="issue" value="${issue}"/>
			<input type="hidden" name="isdraw" value="${isdraw}"/>
		</form>
  </body>
</html>
