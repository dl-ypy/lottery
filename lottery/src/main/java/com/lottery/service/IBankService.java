package com.lottery.service;

import java.math.BigDecimal;

import com.lottery.pojo.BankModel;

public interface IBankService {
	int insert(BankModel bank);
	int update(BigDecimal money, short userid);
}
