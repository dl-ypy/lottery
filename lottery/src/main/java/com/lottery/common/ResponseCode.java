package com.lottery.common;
/**
 * 封装返回的提示信息，枚举类型
 * @author Administrator
 */
public enum ResponseCode {
	SUCCESS(0, "成功"),
	ERROR(1, "失败"),
	NEED_LOGIN(2, "用户未登录，需要登录"),
	ILLEGAL_ARGUMENT(3, "参数错误");
	
	private final int code;
	private final String desc;
	
	private ResponseCode(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
