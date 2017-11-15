package com.lottery.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lottery.pojo.TypeModel;

public interface TypeModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOTTERY.T_LOTTERYTYPE
     *
     * @mbggenerated Fri Oct 27 21:57:40 CST 2017
     */
    int deleteByPrimaryKey(Short lid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOTTERY.T_LOTTERYTYPE
     *
     * @mbggenerated Fri Oct 27 21:57:40 CST 2017
     */
    int insert(TypeModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOTTERY.T_LOTTERYTYPE
     *
     * @mbggenerated Fri Oct 27 21:57:40 CST 2017
     */
    int insertSelective(TypeModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOTTERY.T_LOTTERYTYPE
     *
     * @mbggenerated Fri Oct 27 21:57:40 CST 2017
     */
    TypeModel selectByPrimaryKey(Short lid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOTTERY.T_LOTTERYTYPE
     *
     * @mbggenerated Fri Oct 27 21:57:40 CST 2017
     */
    int updateByPrimaryKeySelective(TypeModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOTTERY.T_LOTTERYTYPE
     *
     * @mbggenerated Fri Oct 27 21:57:40 CST 2017
     */
    int updateByPrimaryKey(TypeModel record);
    
    /**
     * 按条件查询彩种信息
     * @param type
     * @return
     */
    List<TypeModel> queryAll(TypeModel type);
    
    /**
     * 按彩票名称查询彩种信息
     * @param type
     * @return
     */
    List<TypeModel> queryByUsername(@Param("lotypename")String lotypename);

    /**
     * 按彩种名称查询是否有此类彩票
     * @param lotypename
     * @return
     */
	int selectByLotypename(String lotypename);

	/**
	 * 彩种下架
	 * @param lid
	 * @return
	 */
	int undercarriage(Short lid);

	/**
	 * 查询此彩种是否已下架
	 * @param lid
	 * @return
	 */
	int selectStatus(Short lid);

	/**
	 * 彩种上线
	 * @param lid
	 * @return
	 */
	int live(Short lid);
    
    
    
}