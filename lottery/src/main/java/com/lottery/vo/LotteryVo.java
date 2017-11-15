package com.lottery.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class LotteryVo {
	private short lotterid;
	private String lotypename;
	private String issue;
	private BigDecimal accumulated;
	private Timestamp starttime;
	private BigDecimal drawmoney;
	private BigDecimal drawnum;
	private String drawno;
	private short lid;
	private BigDecimal totalsale;
	private short status;
	public short getLotterid() {
		return lotterid;
	}
	public void setLotterid(short lotterid) {
		this.lotterid = lotterid;
	}
	public String getLotypename() {
		return lotypename;
	}
	public void setLotypename(String lotypename) {
		this.lotypename = lotypename;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public BigDecimal getAccumulated() {
		return accumulated;
	}
	public void setAccumulated(BigDecimal accumulated) {
		this.accumulated = accumulated;
	}
	public Timestamp getStarttime() {
		return starttime;
	}
	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}
	public BigDecimal getDrawmoney() {
		return drawmoney;
	}
	public void setDrawmoney(BigDecimal drawmoney) {
		this.drawmoney = drawmoney;
	}
	public BigDecimal getDrawnum() {
		return drawnum;
	}
	public void setDrawnum(BigDecimal drawnum) {
		this.drawnum = drawnum;
	}
	public String getDrawno() {
		return drawno;
	}
	public void setDrawno(String drawno) {
		this.drawno = drawno;
	}
	public short getLid() {
		return lid;
	}
	public void setLid(short lid) {
		this.lid = lid;
	}
	@Override
	public String toString() {
		return "LotteryVo [lotterid=" + lotterid + ", lotypename=" + lotypename + ", issue=" + issue + ", accumulated="
				+ accumulated + ", starttime=" + starttime + ", drawmoney=" + drawmoney + ", drawnum=" + drawnum
				+ ", drawno=" + drawno + ", lid=" + lid + "]";
	}
	public BigDecimal getTotalsale() {
		return totalsale;
	}
	public void setTotalsale(BigDecimal totalsale) {
		this.totalsale = totalsale;
	}
	public short getStatus() {
		return status;
	}
	public void setStatus(short status) {
		this.status = status;
	}
}
