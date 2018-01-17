package cn.smarthse.service.mongo;

import java.util.List;

import cn.smarthse.core.model.ASystem.ASysarea;
import cn.smarthse.core.model.mongo.Customer;

public interface MongoService {
	
	List<Customer> getAll();
	
	List<ASysarea> getAllArea();
	
	void addList(List<ASysarea> list);
}
