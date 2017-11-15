package com.lottery.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lottery.common.ServerResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lottery.dao.DealModelMapper;
import com.lottery.dao.UserModelMapper;
import com.lottery.pojo.DealModel;
import com.lottery.pojo.UserModel;
import com.lottery.service.IDealService;

@Service
public class DealServiceImpl implements IDealService {
	@Autowired
	private DealModelMapper dealdao;
	@Autowired
	private UserModelMapper userdao;
	
	@Override
	public int deleteByPrimaryKey(Short did) {
		return dealdao.deleteByPrimaryKey(did);
	}

	@Override
	public int insert(DealModel record) {
		return dealdao.insert(record);
	}

	@Override
	@Transactional
	public ServerResponse insertSelective(UserModel user, DealModel deal, int flag) {
		UserModel u = new UserModel();
		u.setUserid(deal.getUserid());
		deal.setDealdate(new Timestamp(new Date().getTime()));
		if (flag == 1) {
			u.setBalance(user.getBalance().add(deal.getDealmoney()));   //设置余额
		} else {
			u.setBalance(user.getBalance().subtract(deal.getDealmoney()));   //设置余额
			if (u.getBalance().compareTo(BigDecimal.valueOf(0)) < 0) {
				return ServerResponse.createByErrorMessage("余额不足，请充值！");
			}
		}
		userdao.updateByPrimaryKeySelective(u);   //更改余额
		int saveCount = dealdao.insertSelective(deal);
		if (saveCount > 0) {
			return ServerResponse.createByErrorMessage("交易成功!");   //因为这里要弹出，所以要返回error
		}
		return ServerResponse.createByErrorMessage("交易失败!");
	}


	public ServerResponse queryAll(DealModel deal, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<DealModel> dealList = dealdao.queryAll(deal);
		PageInfo<DealModel> pageResult = new PageInfo<DealModel>(dealList);
		pageResult.setList(dealList);
		return ServerResponse.createBySuccess(pageResult);
	}
	
	@Override
	public DealModel selectByPrimaryKey(Short did) {
		return dealdao.selectByPrimaryKey(did);
	}

	@Override
	public int updateByPrimaryKeySelective(DealModel record) {
		return dealdao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(DealModel record) {
		return dealdao.updateByPrimaryKey(record);
	}

	@Override
	public BigDecimal getTotalMoney(short userid) {
		return dealdao.selectSumDrawMoney(userid);
	}

}
