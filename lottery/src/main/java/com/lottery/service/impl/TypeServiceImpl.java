package com.lottery.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lottery.common.ServerResponse;
import com.lottery.dao.LotteryModelMapper;
import com.lottery.dao.TypeModelMapper;
import com.lottery.pojo.DealModel;
import com.lottery.pojo.TypeModel;
import com.lottery.pojo.UserModel;
import com.lottery.service.ITypeService;
@Service
public class TypeServiceImpl implements ITypeService {
	@Autowired
	private TypeModelMapper dao;
	@Autowired
	private LotteryModelMapper lotterydao;
	
	@Override
	@Transactional
	public ServerResponse deleteByPrimaryKey(Short lid) {
		TypeModel type = dao.selectByPrimaryKey(lid);
		if (type.getStatus() != 0) {
			int selectCount = lotterydao.selectByLidAndStatus(lid);
			System.out.println("-----------------"+selectCount);
			if (selectCount > 0) {
				return ServerResponse.createByErrorMessage("最新一期此类彩票还未开奖，请等其开奖后再进行删除操作！");
			}
			lotterydao.deleteByLid(lid);
			dao.deleteByPrimaryKey(lid);
			return ServerResponse.createBySuccessMessage("type/query.do");
		}
		return ServerResponse.createByErrorMessage("该彩票类型还未下架，请先下架！");
	}

	@Override
	public int insert(TypeModel record) {
		return dao.insert(record);
	}

	@Override
	public ServerResponse insertSelective(TypeModel record) {
		int queryCount = dao.selectByLotypename(record.getLotypename());
		if (queryCount == 0) {
			int saveCount = dao.insertSelective(record);
			if (saveCount > 0) {
				System.out.println("----------ddddddd-------");
				return ServerResponse.createBySuccessMessage("type/query.do");
			}
			return ServerResponse.createBySuccessMessage("添加失败!");
		}
		return ServerResponse.createByErrorMessage("此类彩票已存在!");
	}

	@Override
	public TypeModel selectByPrimaryKey(Short lid) {
		return dao.selectByPrimaryKey(lid);
	}

	@Override
	public ServerResponse updateByPrimaryKeySelective(TypeModel record) {
		int updateCount = dao.updateByPrimaryKeySelective(record);
		if (updateCount > 0) {
			return ServerResponse.createBySuccessMessage("type/query.do");
		}
		return ServerResponse.createBySuccessMessage("修改失败!"); 
	}

	@Override
	public int updateByPrimaryKey(TypeModel record) {
		return dao.updateByPrimaryKey(record);
	}

	@Override
	public ServerResponse queryAll(TypeModel type,int pageNum,int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<TypeModel> typeList = dao.queryAll(type);
		PageInfo<TypeModel> pageResult = new PageInfo<TypeModel>(typeList);
		pageResult.setList(typeList);
		return ServerResponse.createBySuccess(pageResult);		
	}

	@Override
	public ServerResponse queryByUsername(String lotypename,TypeModel type,int pageNum,int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<TypeModel> typeList = dao.queryByUsername(lotypename);
		PageInfo<TypeModel> pageResult = new PageInfo<TypeModel>(typeList);
		pageResult.setList(typeList);
		return ServerResponse.createBySuccess(pageResult);		
	}

	@Override
	public ServerResponse undercarriage(Short lid) {
		int selectCount = dao.selectStatus(lid);
		if (selectCount == 0) {
			int updateCount = dao.undercarriage(lid);
			if (updateCount > 0) {
				return ServerResponse.createBySuccessMessage("type/query.do");
			}
			return ServerResponse.createByErrorMessage("下架失败！");
		}
		return ServerResponse.createByErrorMessage("此商品已下架！");
	}

	@Override
	public ServerResponse live(Short lid) {
		int updateCount = dao.live(lid);
		if (updateCount > 0) {
			return ServerResponse.createBySuccessMessage("type/query.do");
		}
		return ServerResponse.createByErrorMessage("上线失败！");
	}
}
