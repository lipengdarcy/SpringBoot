package cn.smarthse.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.smarthse.business.dao.mongo.AreaDao;
import cn.smarthse.business.model.system.SysAreaStandard;
import cn.smarthse.business.service.mongo.MongoService;

@Service
public class MongoServiceImpl implements MongoService {

	@Resource
	AreaDao AreaDao;

	
	@Override
	public List<SysAreaStandard> getAllArea() {
		return AreaDao.findAll();
	}

	@Override
	public void addList(List<SysAreaStandard> list) {
		AreaDao.insert(list);		
	}

}
