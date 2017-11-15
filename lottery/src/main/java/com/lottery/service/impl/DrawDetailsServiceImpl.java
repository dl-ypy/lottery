package com.lottery.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lottery.dao.DrawDetailsModelMapper;
import com.lottery.pojo.DrawDetailsModel;
import com.lottery.service.IDrawDetailsService;
@Service
public class DrawDetailsServiceImpl implements IDrawDetailsService {
	@Autowired
	private DrawDetailsModelMapper dao;
	
	@Override
	public int deleteByPrimaryKey(Short ddid) {
		return dao.deleteByPrimaryKey(ddid);
	}

	@Override
	public int insert(DrawDetailsModel record) {
		return dao.insert(record);
	}

	@Override
	public int insertSelective(DrawDetailsModel record) {
		return dao.insertSelective(record);
	}

	@Override
	public DrawDetailsModel selectByPrimaryKey(Short ddid) {
		return dao.selectByPrimaryKey(ddid);
	}

	@Override
	public int updateByPrimaryKeySelective(DrawDetailsModel record) {
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(DrawDetailsModel record) {
		return dao.updateByPrimaryKey(record);
	}

	@Override
	public List<DrawDetailsModel> queryAll(DrawDetailsModel details) {
		return dao.queryAll(details);
	}

}
