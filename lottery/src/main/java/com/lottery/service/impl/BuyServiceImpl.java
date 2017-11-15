package com.lottery.service.impl;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lottery.common.ServerResponse;
import com.lottery.dao.BuyModelMapper;
import com.lottery.dao.UserModelMapper;
import com.lottery.pojo.BuyModel;
import com.lottery.pojo.DealModel;
import com.lottery.pojo.UserModel;
import com.lottery.service.IBuyService;
import com.lottery.service.IDealService;
import com.lottery.vo.BuyVo;
@Service
public class BuyServiceImpl implements IBuyService {
	@Autowired
	private BuyModelMapper buydao;
	@Autowired
	private IDealService iDealService;
	@Autowired
	private UserModelMapper userdao;
	
	@Override
	public int deleteByPrimaryKey(Short bid) {
		return buydao.deleteByPrimaryKey(bid);
	}

	@Override
	public int insert(BuyModel record) {
		return buydao.insert(record);
	}

	@Override
	public int insertSelective(BuyModel record) {
		return buydao.insertSelective(record);
	}

	@Override
	public BuyModel selectByPrimaryKey(Short bid) {
		return buydao.selectByPrimaryKey(bid);
	}

	@Override
	public int updateByPrimaryKeySelective(BuyModel record) {
		return buydao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(BuyModel record) {
		return buydao.updateByPrimaryKey(record);
	}

	@Override
	public PageInfo<BuyVo> queryAll(BuyVo buy, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<BuyVo> buyList = buydao.queryAll(buy);
		PageInfo<BuyVo> pageResult = new PageInfo<BuyVo>(buyList);
		pageResult.setList(buyList);
		return pageResult;
	}

	@Override
	@Transactional
	public ServerResponse save(BuyModel buy, UserModel user, DealModel deal) {
		int userCount = userdao.selectCount(user);      //判断用户的支付宝、密码和id是否匹配
		if (userCount > 0) {
			ServerResponse response = iDealService.insertSelective(user, deal, 0);
			if ("交易成功!".equals(response.getMsg())) {
				buydao.insertSelective(buy);
			}
			return response;
		}
		return ServerResponse.createByErrorMessage("账户名或密码错误！");
	}

	@Override
	public List<BuyModel> selectIsDraw() {
		return buydao.selectIsDraw();
	}

	@Override
	public BigDecimal getTotalMoney(short userid) {
		return buydao.selectSumMoney(userid);
	}

}
