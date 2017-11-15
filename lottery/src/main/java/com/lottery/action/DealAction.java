package com.lottery.action;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lottery.common.ServerResponse;
import com.github.pagehelper.PageInfo;
import com.lottery.common.Const;
import com.lottery.common.ResponseCode;


import com.lottery.pojo.DealModel;


import com.lottery.pojo.UserModel;

import com.lottery.service.IDealService;

@Controller
@RequestMapping("/deal")
public class DealAction {
	@Autowired
	private IDealService iDealService;
	
	/**
	 * 用户分页查询自己的交易记录
	 * @param session
	 * @return
	 */
	@RequestMapping("/user_query.do")
	@ResponseBody
	public ServerResponse userQuery(HttpSession session, @RequestParam(value="pageNum",defaultValue="1") int pageNum, @RequestParam(value="pageSize",defaultValue="5") int pageSize) {
		UserModel user = (UserModel)session.getAttribute(Const.CURRENT_USER);
		if (user != null) {
			DealModel deal = new DealModel();
			deal.setUserid(user.getUserid());
			PageInfo<DealModel> pageList = (PageInfo<DealModel>) iDealService.queryAll(deal, pageNum, pageSize).getData();
			List<DealModel> dealList = pageList.getList();
			if (dealList.isEmpty()) {
				return ServerResponse.createByErrorMessage("暂无交易记录！");
			}
			session.setAttribute("page", pageList);
			session.setAttribute("dealList", dealList);
			return ServerResponse.createBySuccessMessage("front/deal/bill.jsp");
		}
		return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());

	}

	@RequestMapping("/query.do")
	@ResponseBody
	public ServerResponse query(HttpSession session, DealModel deal,@RequestParam(value="pageNum",defaultValue="1") int pageNum, @RequestParam(value="pageSize",defaultValue="5") int pageSize) {
		DealModel d= (DealModel) session.getAttribute("list");
		if(d !=null){
			return iDealService.queryAll(deal,pageNum,pageSize);
		}
		return ServerResponse.createByErrorMessage("查找失败");
	}

	/**
	 * 添加交易信息
	 * @param session
	 * @param deal
	 * @return
	 */
	@RequestMapping("/user_save.do")
	@ResponseBody
	public ServerResponse save(HttpSession session, DealModel deal, int flag) {
		UserModel user = (UserModel)session.getAttribute(Const.CURRENT_USER);
		if (user != null) {
			deal.setUserid(user.getUserid());
			return iDealService.insertSelective(user, deal, flag);
		}
		return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
	}
	
	@RequestMapping("/save.do")
	@ResponseBody
	public ServerResponse save(Short id, DealModel model){
		System.out.println("save"+"   "+model);
		System.out.println(id);
		iDealService.insert(model);
		return ServerResponse.createBySuccess();
	}
	
	@RequestMapping("/queryById")
	public String queryById(Short id,Map map){
		DealModel deal = iDealService.selectByPrimaryKey(id);
		map.put("deal", deal);
		return "/updateDeal.jsp";
	}

	@RequestMapping("/update.do")
	@ResponseBody
	public ServerResponse update(DealModel model){
		iDealService.updateByPrimaryKeySelective(model);
		return ServerResponse.createBySuccess();
	}
	
	@RequestMapping("/delete.do")
	@ResponseBody
	public ServerResponse delete(Short id){
		iDealService.deleteByPrimaryKey(id);
		return ServerResponse.createBySuccess();
	}
	

	/*@RequestMapping("/query.do")
	public String query(Map<String, Object> map) {
		List<DealModel> list = iDealService.queryAll(null);
		map.put("list", list);
		return "/dealList.jsp";
		}
		
	
/*	@RequestMapping("/update.do")
	public String update(DealModel model){
		iDealService.updateByPrimaryKeySelective(model);
		return "redirect:query.do";
	}*/
	
	/*@RequestMapping("/delete.do")
	public String delete(Short id){
		iDealService.deleteByPrimaryKey(id);
		return "redirect:query.do";
	}*/
	
	/*@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), true));
	}*/

}
