package com.lottery.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lottery.common.ServerResponse;
import com.lottery.pojo.DealModel;
import com.lottery.pojo.DrawDetailsModel;
import com.lottery.service.IDealService;
import com.lottery.service.IDrawDetailsService;

@Controller
@RequestMapping("/details")
public class DrawDetailsAction {
	@Autowired
	private IDrawDetailsService iDrawDetailsService;
	
	@RequestMapping("/query.do")
	@ResponseBody
	public ServerResponse query(HttpSession session, DrawDetailsModel details) {
		List<DrawDetailsModel> list = iDrawDetailsService.queryAll(details);
		session.setAttribute("detailsList", list);
		return ServerResponse.createBySuccessMessage("front/lottery/lottery-details.jsp");
	}
}
