package com.lottery.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class BuyVo {
	private short bid;
	private String lotypename;
	private Timestamp buydate;
	private BigDecimal buymoney;
	private String buyno;
	private String issue;
	private short lotterid;
	private short lid;
	private String isdraw;
	private short userid;
	public short getBid() {
		return bid;
	}
	public void setBid(short bid) {
		this.bid = bid;
	}
	public String getLotypename() {
		return lotypename;
	}
	public void setLotypename(String lotypename) {
		this.lotypename = lotypename;
	}
	public Timestamp getBuydate() {
		return buydate;
	}
	public void setBuydate(Timestamp buydate) {
		this.buydate = buydate;
	}
	public BigDecimal getBuymoney() {
		return buymoney;
	}
	public void setBuymoney(BigDecimal buymoney) {
		this.buymoney = buymoney;
	}
	public String getBuyno() {
		return buyno;
	}
	public void setBuyno(String buyno) {
		this.buyno = buyno;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public short getLotterid() {
		return lotterid;
	}
	public void setLotterid(short lotterid) {
		this.lotterid = lotterid;
	}
	public short getLid() {
		return lid;
	}
	public void setLid(short lid) {
		this.lid = lid;
	}
	@Override
	public String toString() {
		return "BuyVo [bid=" + bid + ", lotypename=" + lotypename + ", buydate=" + buydate + ", buymoney=" + buymoney
				+ ", buyno=" + buyno + ", issue=" + issue + ", lotterid=" + lotterid + ", lid=" + lid + ", isdraw="
				+ isdraw + ", userid=" + userid + "]";
	}
	public String getIsdraw() {
		return isdraw;
	}
	public void setIsdraw(String isdraw) {
		this.isdraw = isdraw;
	}
	public short getUserid() {
		return userid;
	}
	public void setUserid(short userid) {
		this.userid = userid;
	}
}
