package com.lottery.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lottery.common.ServerResponse;
import com.lottery.dao.BankModelMapper;
import com.lottery.dao.UserModelMapper;
import com.lottery.pojo.BankModel;
import com.lottery.pojo.UserModel;
import com.lottery.service.IUserService;
@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserModelMapper dao;
	@Autowired
	private BankModelMapper bankdao;
	
	@Override
	public int deleteByPrimaryKey(Short userid) {
		return dao.deleteByPrimaryKey(userid);
	}

	@Override
	public int insert(UserModel record) {
		return dao.insert(record);
	}

	@Override
	public int insertSelective(UserModel record) {
		return dao.insertSelective(record);
	}

	@Override
	public ServerResponse<UserModel> selectByPrimaryKey(Short userid, HttpSession session) {
		UserModel user = dao.selectByPrimaryKey(userid);
		if (user != null) {
			session.setAttribute("user", user);
			return ServerResponse.createBySuccessMessage("front/user/user-center.jsp");
		}
		return ServerResponse.createByErrorMessage("此用户不存在！");
	}

	@Override
	public ServerResponse updateByPrimaryKeySelective(UserModel user) {
		if (user.getUsername()!=null && checkUsername(user)) {
			return ServerResponse.createByErrorMessage("此用户名已被占用！");
		}
		if (user.getPhone()!=null && checkPhone(user)) {
			return ServerResponse.createByErrorMessage("此电话号码已被占用！");
		}
		int updaCount = dao.updateByPrimaryKeySelective(user);
		if (updaCount > 0) {
			return ServerResponse.createBySuccessMessage("user/queryOne.do");
		}
		return ServerResponse.createByErrorMessage("修改失败！");
	}

	@Override
	public int updateByPrimaryKey(UserModel record) {
		return dao.updateByPrimaryKey(record);
	}

	@Override
	public ServerResponse queryAll(UserModel user, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<UserModel> list = dao.queryAll(user);
		PageInfo<UserModel> pageResult = new PageInfo<UserModel>(list);
		pageResult.setList(list);
		return ServerResponse.createBySuccess(pageResult);
	}

	@Override
	@Transactional
	public ServerResponse register(UserModel user) {
		int userCount = dao.selectByUsernameOrPhone(user);
		if (userCount == 0) {
			int insertCount = dao.insertSelective(user);
			if (insertCount > 0) {
				BankModel bank = new BankModel();
				UserModel u = dao.selectByUserName(user.getUsername());
				bank.setUserid(u.getUserid());
				bankdao.insert(bank);
				return ServerResponse.createBySuccessMessage("front/user/user-login.jsp");
			} else {
				return ServerResponse.createByErrorMessage("注册失败!");
			}
		}
		return ServerResponse.createByErrorMessage("该用户名或手机号已注册！");
	}

	@Override
	public ServerResponse<UserModel> login(UserModel user) {
		int userCount = dao.selectByUsernameOrPhone(user);
		if (userCount > 0) {
			UserModel u = dao.selectByUsernameOrPhoneAndPassword(user);
			if (u != null) {
				if (u.getStatus() == 0) {
					dao.updateLoginDate(u.getUserid());   //修改最后一次登录
					return ServerResponse.createBySuccess("type/index", u);   //登录成功，将用户数据返回
				}
				return ServerResponse.createByErrorMessage("此用户已被冻结！");
			}
			return ServerResponse.createByErrorMessage("密码错误！");
		}
		return ServerResponse.createByErrorMessage("该用户未注册！");
	}

	@Override
	public ServerResponse binding(Short userid, String anumber, Integer apassword) {
		int isBinding = dao.selectByAnumber(anumber);
		if (isBinding == 0) {
			UserModel user = new UserModel();
			user.setUserid(userid);
			user.setAnumber(anumber);
			user.setApassword(apassword);
			int updateCount = dao.updateByPrimaryKeySelective(user);
			if (updateCount > 0) {
				return ServerResponse.createBySuccessMessage("user/queryOne.do");
			}
			return ServerResponse.createByErrorMessage("绑定支付宝失败！");
		}
		return ServerResponse.createByErrorMessage("此账号已被绑定！");
	}

	@Override
	public ServerResponse unbind(Short userid) {
		UserModel user = new UserModel();
		user.setUserid(userid);
		user.setAnumber("");
		user.setApassword(0);
		int updateCount = dao.updateByPrimaryKeySelective(user);
		if (updateCount > 0) {
			return ServerResponse.createBySuccessMessage("解绑成功！");
		}
		return ServerResponse.createByErrorMessage("解绑失败！");
	}

	@Override
	public ServerResponse updatePwd(Short userid, String oldPassword, String newPassword) {
		int checkCount = dao.checkPassword(userid, oldPassword);
		if (checkCount > 0) {   //判断用户和原密码是否匹配
			UserModel user = new UserModel();
			user.setUserid(userid);
			user.setPassword(newPassword);
			int updateCount = dao.updateByPrimaryKeySelective(user);
			if (updateCount > 0) {
				return ServerResponse.createBySuccessMessage("user/logout");
			}
			return ServerResponse.createByErrorMessage("修改失败！");
		}
		return ServerResponse.createByErrorMessage("旧密码错误！");
	}
	
	/**
	 * 判断用户名是否存在
	 * @param user
	 * @return
	 */
	private boolean checkUsername(UserModel user) {
		int checkUCount = dao.checkUsername(user.getUserid(), user.getUsername());
		if (checkUCount > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断电话是否存在
	 * @param user
	 * @return
	 */
	private boolean checkPhone(UserModel user) {
		int checkPCount = dao.checkPhone(user.getUserid(), user.getPhone());
		if (checkPCount > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 根据id判断用户是否存在
	 * @param userid
	 * @return
	 */
	private boolean checkById(short userid) {
		int checkCount = dao.checkById(userid);
		if (checkCount > 0) {
			return true;
		}
		return false;
	}

	@Override
	public ServerResponse<UserModel> mangerlogin(UserModel user) {
		int userCount = dao.selectByUsernameOrPhone(user);
		if (userCount > 0) {
			UserModel u = dao.selectByUsernameOrPhoneAndPasswordM(user);
			if (u != null) {
				return ServerResponse.createBySuccess("back/index.jsp", u);   //登录成功，将用户数据返回
			}
			return ServerResponse.createByErrorMessage("密码错误或此用户不是管理员！");
		}
		return ServerResponse.createByErrorMessage("该用户未注册！");
	}

	@Override
	public ServerResponse freeze() {
		List<UserModel> useridList = dao.selectFreezeByTime();
		if (useridList.size() != 0) {
			for (UserModel user : useridList) {
				if (checkById(user.getUserid())) {
					dao.freezeByTime(user.getUserid());
				}
			}
			System.out.println("-------------冻结了"+useridList.size()+"个用户--------------");
			return ServerResponse.createBySuccessMessage("冻结"+useridList.size()+"个用户成功！");
		}
		return ServerResponse.createByErrorMessage("没有需要冻结的用户！");
	}

	@Override
	public ServerResponse unfreeze(short userid) {
		int updateCount = dao.unFreezeById(userid);
		if (updateCount > 0) {
			return ServerResponse.createBySuccessMessage("解冻成功！");
		}
		return ServerResponse.createByErrorMessage("此用户不需要解冻！");
	}
}
