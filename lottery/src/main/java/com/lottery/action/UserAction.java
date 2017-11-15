package com.lottery.action;

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
import com.lottery.pojo.UserModel;
import com.lottery.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserAction {
	@Autowired
	private IUserService iUserService;
	
	/**
	 * 管理员分页查询所有用户信息
	 * @param session
	 * @param user
	 * @return
	 */
	@RequestMapping("/query.do")
	@ResponseBody
	public ServerResponse query(HttpSession session, UserModel user, @RequestParam(value="pageNum",defaultValue="1") int pageNum, @RequestParam(value="pageSize",defaultValue="5") int pageSize) {
		UserModel u = (UserModel)session.getAttribute(Const.CURRENT_MANAGER);
		if (u != null) {
			System.out.println("-----------------------"+user);
			PageInfo<UserModel> pageList = (PageInfo<UserModel>) iUserService.queryAll(user, pageNum, pageSize).getData();
			List<UserModel> userList = pageList.getList();
			session.setAttribute("page", pageList);
			session.setAttribute("userList", userList);
			session.setAttribute("username", user.getUsername());
			session.setAttribute("phone", user.getPhone());
			return ServerResponse.createBySuccessMessage("back/user/user-list.jsp");
		}
		return ServerResponse.createByErrorMessage("需要管理员登录！");
	}
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	@RequestMapping("/register.do")
	@ResponseBody
	public ServerResponse register(UserModel user) {
		return iUserService.register(user);
	}
	
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	@RequestMapping("/login.do")
	@ResponseBody
	public ServerResponse login(UserModel user, HttpSession session) {
		ServerResponse<UserModel> response = iUserService.login(user);
		if (response.isLogin()) {   //判断是否登录
			session.setAttribute(Const.CURRENT_USER, response.getData());  //将成功登录用户的数据存入session中
		}
		return response;
	}
	
	/**
	 * 管理员登录
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping("/manager_login.do")
	@ResponseBody
	public ServerResponse<UserModel> managerLogin(UserModel user, HttpSession session) {
		System.out.println("-----------------------"+user);
		ServerResponse<UserModel> response = iUserService.mangerlogin(user);
		if (response.isLogin()) {   //判断是否登录
			session.setAttribute(Const.CURRENT_MANAGER, response.getData());  //将成功登录用户的数据存入session中
		}
		return response;
	}
	
	/**
	 * 用户注销
	 * @param user
	 * @return
	 */
	@RequestMapping("/logout")
//	@ResponseBody
	public String logout(HttpSession session) {
		session.removeAttribute(Const.CURRENT_USER);   
		return "redirect:/type/index";
	}
	
	/**
	 * 管理员注销
	 * @param user
	 * @return
	 */
	@RequestMapping("/manager_logout")
//	@ResponseBody
	public String managerLogout(HttpSession session) {
		session.removeAttribute(Const.CURRENT_MANAGER);   
		return "/back/login.jsp";
	}
	
	/**
	 * 绑定支付宝账号
	 * @param anumber
	 * @param apassword
	 * @param session
	 * @return
	 */
	@RequestMapping("/binding.do")
	@ResponseBody
	public ServerResponse binding(String anumber, Integer apassword, HttpSession session) {
		UserModel user = (UserModel) session.getAttribute(Const.CURRENT_USER);
		if (user != null) {  //判断用户是否登录
			return iUserService.binding(user.getUserid(), anumber, apassword);
		}
		return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
	}
	
	/**
	 * 解绑支付宝账号
	 * @param session
	 * @return
	 */
	@RequestMapping("/unbind.do")
	@ResponseBody
	public ServerResponse unbind(HttpSession session) {
		UserModel user = (UserModel) session.getAttribute(Const.CURRENT_USER);
		if (user != null) {  //判断用户是否登录
			return iUserService.unbind(user.getUserid());
		}
		return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
	}
	
	/**
	 * 修改密码
	 * @param session
	 * @param password
	 * @return
	 */
	@RequestMapping("/updatePwd.do")
	@ResponseBody
	public ServerResponse updatePwd(HttpSession session, String oldPassword, String newPassword) {
		UserModel user = (UserModel) session.getAttribute(Const.CURRENT_USER);
		if (user != null) {  //判断用户是否登录
			return iUserService.updatePwd(user.getUserid(), oldPassword, newPassword);
		}
		return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
	}
	
	/**
	 * 查询单个用户信息
	 * @param session
	 * @return
	 */
	@RequestMapping("/queryOne.do")
	@ResponseBody
	public ServerResponse<UserModel> queryOne(HttpSession session) {
		UserModel user = (UserModel) session.getAttribute(Const.CURRENT_USER);
		if (user != null) {  //判断用户是否登录
			return iUserService.selectByPrimaryKey(user.getUserid(), session);
		}
		return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
	}
	
	/**
	 * 修改用户个人基本信息
	 * @param session
	 * @param user
	 * @return
	 */
	@RequestMapping("/update.do")
	@ResponseBody
	public ServerResponse update(HttpSession session, UserModel user) {
		UserModel u = (UserModel) session.getAttribute(Const.CURRENT_USER);
		if (u != null) {  //判断用户是否登录
			return iUserService.updateByPrimaryKeySelective(user);
		} 
		return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
	}
	
	/**
	 * 冻结用户,在服务器启动时通过监听器进行线程的调用
	 * 在注册后2分钟内未登录进行冻结
	 * @param session
	 * @param userid
	 * @return
	 */
	@RequestMapping("/freeze.do")
	@ResponseBody
	public ServerResponse freeze() {
		return iUserService.freeze();
	}
	
	/**
	 * 管理员进行解冻
	 * @param session
	 * @param phone
	 * @return
	 */
	@RequestMapping("/un_freeze.do")
	@ResponseBody
	public ServerResponse unFreeze(HttpSession session, short userid) {
		UserModel user = (UserModel)session.getAttribute(Const.CURRENT_MANAGER);
		if (user != null) {
			return iUserService.unfreeze(userid);
		}
		return ServerResponse.createByErrorMessage("需要以管理员身份登录！");
	}
}
