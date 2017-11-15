package com.lottery.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.lottery.common.Const;
import com.lottery.common.ServerResponse;
import com.lottery.pojo.NewsModel;
import com.lottery.pojo.TypeModel;
import com.lottery.pojo.UserModel;
import com.lottery.service.ILotteryService;
import com.lottery.service.INewsService;
import com.lottery.service.ITypeService;
import com.lottery.vo.LotteryVo;

@Controller
@RequestMapping("/type")
public class TypeAction {
	@Autowired
	private ITypeService iTypeService;
	@Autowired
	private ILotteryService iLotteryService;
	@Autowired
	private INewsService iNewsService;

	/**
	 * 查询彩票类别信息
	 * @param session
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/query.do")
	@ResponseBody
	public ServerResponse query(HttpSession session,TypeModel type,@RequestParam(value="pageNum",defaultValue="1") int pageNum, @RequestParam(value="pageSize",defaultValue="5") int pageSize) {
		UserModel user = (UserModel) session.getAttribute(Const.CURRENT_MANAGER);
		if(user !=null){
			PageInfo<TypeModel> pageList = (PageInfo<TypeModel>) iTypeService.queryAll(null,pageNum,pageSize).getData();
			List<TypeModel> typeList = pageList.getList();
			session.setAttribute("page", pageList);
			session.setAttribute("typeList", typeList);
			return ServerResponse.createBySuccessMessage("back/type/type-list.jsp");
		}
		return ServerResponse.createByErrorMessage("需要管理员登录！");
	}


	/**
	 * 首页查询彩种、彩票及新闻
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/index")
//	@ResponseBody
	public String query(Map<String, Object> map, @RequestParam(value="pageNum",defaultValue="1") int pageNum, @RequestParam(value="pageSize",defaultValue="5") int pageSize) {
		PageInfo<TypeModel> pageTList = (PageInfo<TypeModel>) iTypeService.queryAll(null, pageNum, pageSize).getData();
		List<TypeModel> typeList = pageTList.getList();
		map.put("type", typeList);
		PageInfo<LotteryVo> pageList = (PageInfo<LotteryVo>) iLotteryService.queryAll(null, pageNum, 10);
		System.out.println("-----------------------"+pageList);
		List<LotteryVo> lotteryList = pageList.getList();
		map.put("lottery", lotteryList);
		PageInfo<NewsModel> pageNList = (PageInfo<NewsModel>)iNewsService.queryAll(null, 1, 100);
		List<NewsModel> newsList = pageNList.getList();
		map.put("news", newsList);
		map.put("page", pageList);
		return "/front/index.jsp";
	}
	
	/**
	 * 添加彩种
	 */
	@RequestMapping("/save.do")
	@ResponseBody
	public ServerResponse save(HttpSession session, TypeModel type){
		UserModel user = (UserModel)session.getAttribute(Const.CURRENT_MANAGER);
		if (user != null) {
			return iTypeService.insertSelective(type);
		}
		return ServerResponse.createByErrorMessage("需要管理员登录!");
	}
	/**
	 * 删除彩票类别信息
	 * @param session
	 * @return
	 */
	@RequestMapping("/delete.do")
	@ResponseBody
	public ServerResponse delete(HttpSession session, Short lid){
		UserModel user = (UserModel)session.getAttribute(Const.CURRENT_MANAGER);
		if (user != null) {
			return iTypeService.deleteByPrimaryKey(lid);
		}
		return ServerResponse.createByErrorMessage("需要管理员登录!");
	}
	
	/**
	 * 下架彩种
	 * @param lid
	 * @return
	 */
	@RequestMapping("/undercarriage.do")
	@ResponseBody
	public ServerResponse undercarriage(HttpSession session, Short lid){
		UserModel user = (UserModel)session.getAttribute(Const.CURRENT_MANAGER);
		if (user != null) {
			return iTypeService.undercarriage(lid);
		}
		return ServerResponse.createByErrorMessage("需要管理员登录!");
	}
	
	/**
	 * 彩种上线
	 * @param lid
	 * @return
	 */
	@RequestMapping("/live.do")
	@ResponseBody
	public ServerResponse live(HttpSession session, Short lid){
		UserModel user = (UserModel)session.getAttribute(Const.CURRENT_MANAGER);
		if (user != null) {
			return iTypeService.live(lid);
		}
		return ServerResponse.createByErrorMessage("需要管理员登录!");
	}
	

	/**
	 * 修改彩票基本信息
	 * @param session
	 * @param user
	 * @return
	 */
	@RequestMapping("/update.do")
	@ResponseBody
	public ServerResponse update(HttpSession session, TypeModel type){
		UserModel user = (UserModel)session.getAttribute(Const.CURRENT_MANAGER);
		if (user != null) {
			return iTypeService.updateByPrimaryKeySelective(type);
		}
		return ServerResponse.createByErrorMessage("需要管理员登录!");
	}
	
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), true));
	}
}
