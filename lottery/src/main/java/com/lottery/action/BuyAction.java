package com.lottery.action;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.lottery.common.Const;
import com.lottery.common.ResponseCode;
import com.lottery.common.ServerResponse;
import com.lottery.pojo.BuyModel;
import com.lottery.pojo.DealModel;
import com.lottery.pojo.LotteryModel;
import com.lottery.pojo.TypeModel;
import com.lottery.pojo.UserModel;
import com.lottery.service.IBuyService;
import com.lottery.service.IDealService;
import com.lottery.service.ILotteryService;
import com.lottery.service.ITypeService;
import com.lottery.vo.BuyVo;

@Controller
@RequestMapping("/buy")
public class BuyAction {
	@Autowired
	private IBuyService iBuyService;
	@Autowired
	private ITypeService iTypeService;
	@Autowired
	private ILotteryService iLotteryService;
	@Autowired
	private IDealService iDealService;
	
	@RequestMapping("/query.do")
	@ResponseBody
	public ServerResponse query(BuyVo buy, @RequestParam(value="pageNum",defaultValue="1") int pageNum, 
			@RequestParam(value="pageSize",defaultValue="5") int pageSize, HttpSession session) {
		UserModel user = (UserModel)session.getAttribute(Const.CURRENT_USER);
		if (user != null) {
			buy.setUserid(user.getUserid());
			System.out.println("-----------------------------"+buy);
			PageInfo<BuyVo> pageList = iBuyService.queryAll(buy, pageNum, pageSize);
			session.setAttribute("page", pageList);
			List<BuyVo> buyList = pageList.getList();
			session.setAttribute("buyList", buyList);
			PageInfo<TypeModel> page = (PageInfo<TypeModel>) iTypeService.queryAll(null, 1, 100).getData();
			List<TypeModel> typeList = page.getList();  //得到所有彩种
			session.setAttribute("typeList", typeList);
			session.setAttribute("lid", buy.getLid());
			List<LotteryModel> issueList = iLotteryService.selectIssueByGroup();  //得到期号
			session.setAttribute("issueList", issueList);
			session.setAttribute("issue", buy.getIssue());
			List<BuyModel> isDrawList = iBuyService.selectIsDraw();  //得到是否获奖
			session.setAttribute("isDrawList", isDrawList);
			session.setAttribute("isdraw", buy.getIsdraw());
			BigDecimal buyTotalMoney = iBuyService.getTotalMoney(buy.getUserid());  //获取买彩票的总金额
			session.setAttribute("buyTotalMoney", buyTotalMoney);
			BigDecimal drawTotalMoney = iDealService.getTotalMoney(buy.getUserid());  //获取中奖的总金额
			session.setAttribute("drawTotalMoney", drawTotalMoney);
			return ServerResponse.createBySuccessMessage("front/buy/order-list.jsp");
		}
		return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
	}
	
	/**
	 * 添加购票信息,同时添加交易信息
	 * @param session
	 * @param buy
	 * @return
	 */
	@RequestMapping("/save.do")
	@ResponseBody
	public ServerResponse save(HttpSession session, BuyModel buy, DealModel deal, UserModel user) {   //不用session的原因是防止用户不输入支付密码和账号就能进行操作
		UserModel u = (UserModel)session.getAttribute(Const.CURRENT_USER);
		if (u != null) {
			user.setUserid(u.getUserid());
			user.setBalance(u.getBalance());   //设置余额
			buy.setUserid(u.getUserid());
			deal.setUserid(u.getUserid());
			return iBuyService.save(buy,user,deal);
		}
		return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
	}
}
