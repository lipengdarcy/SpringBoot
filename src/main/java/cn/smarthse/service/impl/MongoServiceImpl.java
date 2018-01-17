package cn.smarthse.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.smarthse.core.dao.mongo.AreaDao;
import cn.smarthse.core.dao.mongo.CustomerRepository;
import cn.smarthse.core.model.ASystem.ASysarea;
import cn.smarthse.core.model.mongo.Customer;
import cn.smarthse.service.mongo.MongoService;

@Service
public class MongoServiceImpl implements MongoService {

	@Resource
	AreaDao AreaDao;
	
	@Resource
	CustomerRepository CustomerRepository;

	@Override
	public List<Customer> getAll() {
		return CustomerRepository.findAll();
	}

	
	@Override
	public List<ASysarea> getAllArea() {
		return AreaDao.findAll();
	}

	@Override
	public void addList(List<ASysarea> list) {
		AreaDao.insert(list);		
	}

}
