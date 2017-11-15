package com.lottery.pojo;

import java.math.BigDecimal;

public class BankModel {
	private short bankid;
	private short userid;
	private BigDecimal money;
	@Override
	public String toString() {
		return "BankModel [bankid=" + bankid + ", userid=" + userid + ", money=" + money + "]";
	}
	public short getBankid() {
		return bankid;
	}
	public void setBankid(short bankid) {
		this.bankid = bankid;
	}
	public short getUserid() {
		return userid;
	}
	public void setUserid(short userid) {
		this.userid = userid;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
}
