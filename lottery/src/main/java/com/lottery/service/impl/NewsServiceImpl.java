package com.lottery.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lottery.common.ServerResponse;
import com.lottery.dao.NewsModelMapper;
import com.lottery.pojo.NewsModel;
import com.lottery.service.INewsService;
@Service
public class NewsServiceImpl implements INewsService {
	@Autowired
	private NewsModelMapper dao;
	
	@Override
	public int deleteByPrimaryKey(Short newsid) {
		return dao.deleteByPrimaryKey(newsid);
	}

	@Override
	public int insert(NewsModel record) {
		return dao.insert(record);
	}

	@Override
	public ServerResponse insertSelective(NewsModel news) {
		int insertCount = dao.insertSelective(news);
		if (insertCount > 0) {
			return ServerResponse.createBySuccessMessage("news/queryback.do");
		}
		return ServerResponse.createByErrorMessage("添加失败！");
	}

	@Override
	public NewsModel selectByPrimaryKey(Short newsid) {
		return dao.selectByPrimaryKey(newsid);
	}

	@Override
	public int updateByPrimaryKeySelective(NewsModel record) {
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(NewsModel record) {
		return dao.updateByPrimaryKey(record);
	}

	@Override
	public PageInfo<NewsModel> queryAll(NewsModel news, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<NewsModel> newsList = dao.queryAll(news);
		PageInfo<NewsModel> pageResult = new PageInfo<NewsModel>(newsList);
		pageResult.setList(newsList);
		return pageResult;
	}

	@Override
	public ServerResponse previous(short newsid) {
		List<NewsModel> newsList = dao.previous();
		NewsModel news = null;
		for (int i=0; i<newsList.size(); i++) {
			news = newsList.get(i);
			if (news.getNewsid() == newsid) {
				if (i == 0) {
					return ServerResponse.createByErrorMessage("已经是第一条新闻！");
				}
				return ServerResponse.createBySuccess(newsList.get(i-1));
			}
		}
		return ServerResponse.createByErrorMessage("访问失败！");
	}

	@Override
	public ServerResponse next(short newsid) {
		List<NewsModel> newsList = dao.previous();
		NewsModel news = null;
		for (int i=0; i<newsList.size(); i++) {
			news = newsList.get(i);
			if (news.getNewsid() == newsid) {
				if (i == (newsList.size()-1)) {
					return ServerResponse.createByErrorMessage("已经是最后一条新闻！");
				}
				return ServerResponse.createBySuccess(newsList.get(i+1));
			}
		}
		return ServerResponse.createByErrorMessage("访问失败！");
	}

}
