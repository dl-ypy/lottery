package com.lottery.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.lottery.common.ServerResponse;
import com.lottery.pojo.LotteryModel;
import com.lottery.pojo.TypeModel;
import com.lottery.service.ILotteryService;
import com.lottery.service.ITypeService;
import com.lottery.vo.LotteryVo;

@Controller
@RequestMapping("/lottery")
public class LotteryAction {
	@Autowired
	private ILotteryService iLotteryService;
	@Autowired
	private ITypeService iTypeService;
	
	/**
	 * 按条件分页查询彩票信息
	 * @param lottery
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/query.do")
	@ResponseBody
	public ServerResponse query(TypeModel type, HttpSession session,LotteryModel lottery, @RequestParam(value="pageNum",defaultValue="1") int pageNum, @RequestParam(value="pageSize",defaultValue="5") int pageSize) {
//																	为参数设置默认值
		System.out.println("++++++++++++++++++++"+lottery);
		PageInfo<LotteryVo> pageList = iLotteryService.queryAll(lottery, pageNum, pageSize);
		List<LotteryVo> lotteryList = pageList.getList();
		System.out.println("----------------------"+lotteryList);
		session.setAttribute("page", pageList);
		session.setAttribute("lotteryList", lotteryList);
		session.setAttribute("lid", lottery.getLid());
		session.setAttribute("lotypename", type.getLotypename());
		session.setAttribute("salemoney", type.getSalemoney());
		List<LotteryModel> issueList = iLotteryService.selectIssueByGroup();  //得到期号
		session.setAttribute("issueList", issueList);
		session.setAttribute("issue", lottery.getIssue());
		return ServerResponse.createBySuccessMessage("front/lottery/lottery-list.jsp");
	}
	
	@RequestMapping("/manager_query.do")
	@ResponseBody
	public ServerResponse managerQuery(TypeModel type, HttpSession session,LotteryModel lottery, @RequestParam(value="pageNum",defaultValue="1") int pageNum, @RequestParam(value="pageSize",defaultValue="5") int pageSize) {
//																	为参数设置默认值
		System.out.println("++++++++++++++++++++"+lottery);
		PageInfo<LotteryVo> pageList = iLotteryService.queryAll(lottery, pageNum, pageSize);
		List<LotteryVo> lotteryList = pageList.getList();
		System.out.println("----------------------"+lotteryList);
		session.setAttribute("page", pageList);
		session.setAttribute("lotteryList", lotteryList);
		session.setAttribute("lid", lottery.getLid());
		session.setAttribute("lotypename", type.getLotypename());
		session.setAttribute("salemoney", type.getSalemoney());
		List<LotteryModel> issueList = iLotteryService.selectIssueByGroup();  //得到期号
		session.setAttribute("issueList", issueList);
		session.setAttribute("issue", lottery.getIssue());
		PageInfo<TypeModel> page = (PageInfo<TypeModel>) iTypeService.queryAll(null, 1, 100).getData();
		List<TypeModel> typeList = page.getList();  //得到所有彩种
		session.setAttribute("typeList", typeList);
		return ServerResponse.createBySuccessMessage("back/lottery/lottery-list.jsp");
	}
	
	/**
	 * 自动生成彩票
	 */
	@RequestMapping("/generate.do")
	@ResponseBody
	public void generate() {
		iLotteryService.generate();
	}
}
