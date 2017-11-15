package com.lottery.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.lottery.common.ServerResponse;
import com.lottery.pojo.LotteryModel;
import com.lottery.vo.LotteryVo;

public interface ILotteryService {
	/**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOTTERY.T_LOTTERY
     *
     * @mbggenerated Sat Oct 28 09:17:31 CST 2017
     */
    int deleteByPrimaryKey(Short lotterid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOTTERY.T_LOTTERY
     *
     * @mbggenerated Sat Oct 28 09:17:31 CST 2017
     */
    int insert(LotteryModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOTTERY.T_LOTTERY
     *
     * @mbggenerated Sat Oct 28 09:17:31 CST 2017
     */
    int insertSelective(LotteryModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOTTERY.T_LOTTERY
     *
     * @mbggenerated Sat Oct 28 09:17:31 CST 2017
     */
    LotteryModel selectByPrimaryKey(Short lotterid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOTTERY.T_LOTTERY
     *
     * @mbggenerated Sat Oct 28 09:17:31 CST 2017
     */
    int updateByPrimaryKeySelective(LotteryModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOTTERY.T_LOTTERY
     *
     * @mbggenerated Sat Oct 28 09:17:31 CST 2017
     */
    int updateByPrimaryKey(LotteryModel record);
    
    /**
     * 按条件查询所有彩票信息
     * @param lottory
     * @return
     */
    PageInfo<LotteryVo> queryAll(LotteryModel lottory, int pageNum, int pageSize);

    /**
     * 系统自动产生彩票
     * @return
     */
	void generate();

	List<LotteryModel> selectIssueByGroup();
}