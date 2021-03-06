package com.lottery.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class DealModel {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LOTTERY.T_DEAL.DID
     *
     * @mbggenerated Fri Oct 27 21:28:08 CST 2017
     */
    private Short did;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LOTTERY.T_DEAL.USERID
     *
     * @mbggenerated Fri Oct 27 21:28:08 CST 2017
     */
    private Short userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LOTTERY.T_DEAL.DEALDATE
     *
     * @mbggenerated Fri Oct 27 21:28:08 CST 2017
     */
    private Timestamp dealdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LOTTERY.T_DEAL.DEALMONEY
     *
     * @mbggenerated Fri Oct 27 21:28:08 CST 2017
     */
    private BigDecimal dealmoney;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LOTTERY.T_DEAL.DEALINFO
     *
     * @mbggenerated Fri Oct 27 21:28:08 CST 2017
     */
    private String dealinfo;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LOTTERY.T_DEAL.DID
     *
     * @return the value of LOTTERY.T_DEAL.DID
     *
     * @mbggenerated Fri Oct 27 21:28:08 CST 2017
     */
    public Short getDid() {
        return did;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LOTTERY.T_DEAL.DID
     *
     * @param did the value for LOTTERY.T_DEAL.DID
     *
     * @mbggenerated Fri Oct 27 21:28:08 CST 2017
     */
    public void setDid(Short did) {
        this.did = did;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LOTTERY.T_DEAL.USERID
     *
     * @return the value of LOTTERY.T_DEAL.USERID
     *
     * @mbggenerated Fri Oct 27 21:28:08 CST 2017
     */
    public Short getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LOTTERY.T_DEAL.USERID
     *
     * @param userid the value for LOTTERY.T_DEAL.USERID
     *
     * @mbggenerated Fri Oct 27 21:28:08 CST 2017
     */
    public void setUserid(Short userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LOTTERY.T_DEAL.DEALDATE
     *
     * @return the value of LOTTERY.T_DEAL.DEALDATE
     *
     * @mbggenerated Fri Oct 27 21:28:08 CST 2017
     */
    public Timestamp getDealdate() {
        return dealdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LOTTERY.T_DEAL.DEALDATE
     *
     * @param dealdate the value for LOTTERY.T_DEAL.DEALDATE
     *
     * @mbggenerated Fri Oct 27 21:28:08 CST 2017
     */
    public void setDealdate(Timestamp dealdate) {
        this.dealdate = dealdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LOTTERY.T_DEAL.DEALMONEY
     *
     * @return the value of LOTTERY.T_DEAL.DEALMONEY
     *
     * @mbggenerated Fri Oct 27 21:28:08 CST 2017
     */
    public BigDecimal getDealmoney() {
        return dealmoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LOTTERY.T_DEAL.DEALMONEY
     *
     * @param dealmoney the value for LOTTERY.T_DEAL.DEALMONEY
     *
     * @mbggenerated Fri Oct 27 21:28:08 CST 2017
     */
    public void setDealmoney(BigDecimal dealmoney) {
        this.dealmoney = dealmoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LOTTERY.T_DEAL.DEALINFO
     *
     * @return the value of LOTTERY.T_DEAL.DEALINFO
     *
     * @mbggenerated Fri Oct 27 21:28:08 CST 2017
     */
    public String getDealinfo() {
        return dealinfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LOTTERY.T_DEAL.DEALINFO
     *
     * @param dealinfo the value for LOTTERY.T_DEAL.DEALINFO
     *
     * @mbggenerated Fri Oct 27 21:28:08 CST 2017
     */
    public void setDealinfo(String dealinfo) {
        this.dealinfo = dealinfo == null ? null : dealinfo.trim();
    }

	@Override
	public String toString() {
		return "DealModel [did=" + did + ", userid=" + userid + ", dealdate=" + dealdate + ", dealmoney=" + dealmoney
				+ ", dealinfo=" + dealinfo + "]";
	}
    

}