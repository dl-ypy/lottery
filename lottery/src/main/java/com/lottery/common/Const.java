package com.lottery.common;

import java.math.BigDecimal;

/**
 * 封装常量
 * @author Administrator
 */
public class Const {
	public static final String CURRENT_USER = "current_user";    //当前普通用户
	public static final String CURRENT_MANAGER = "current_manager";    //当前管理员

	public enum weekday {
		日,一,二,三,四,五,六
	}
	
	public static final BigDecimal INIT_MONEY = BigDecimal.valueOf(50000);  //初始化奖池
}
