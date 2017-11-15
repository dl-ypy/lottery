package com.lottery.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lottery.dao.BankModelMapper;
import com.lottery.pojo.BankModel;
import com.lottery.service.IBankService;
@Service
public class BankServiceImpl implements IBankService {
	@Autowired
	private BankModelMapper dao;
	
	@Override
	public int insert(BankModel bank) {
		return dao.insert(bank);
	}

	@Override
	public int update(BigDecimal money, short userid) {
		return dao.update(money, userid);
	}

}
