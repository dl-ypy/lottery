package com.lottery.dao;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

import com.lottery.pojo.BankModel;

public interface BankModelMapper {
	int insert(BankModel bank);
	int update(@Param("money") BigDecimal money, @Param("userid") short userid);
}
