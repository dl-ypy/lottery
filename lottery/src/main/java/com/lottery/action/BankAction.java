package com.lottery.action;

import java.math.BigDecimal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lottery.common.Const;
import com.lottery.common.ResponseCode;
import com.lottery.common.ServerResponse;
import com.lottery.pojo.UserModel;
import com.lottery.service.IBankService;
import com.lottery.service.IUserService;

@Controller
@RequestMapping("/bank")
public class BankAction {
	@Autowired
	private IBankService iBankService;
	@Autowired
	private IUserService iUserService;
	
	@RequestMapping("/update.do")
	@ResponseBody
	public ServerResponse update(BigDecimal money, HttpSession session) {
		UserModel user = (UserModel)session.getAttribute(Const.CURRENT_USER);
		if (user != null) {
			System.out.println("--------------------"+money);
			iBankService.update(money, user.getUserid());
			UserModel u = new UserModel();
			u.setUserid(user.getUserid());
			u.setBalance(user.getBalance().add(money));   //更改余额
			iUserService.updateByPrimaryKeySelective(u);
			user.setBalance(user.getBalance().add(money));
			session.setAttribute(Const.CURRENT_USER, user);
			return ServerResponse.createBySuccessMessage("type/index");
		}
		return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
	}
}
