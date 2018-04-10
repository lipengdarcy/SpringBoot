package cn.smarthse.business.service.mongo;

import java.util.List;

import cn.smarthse.business.model.system.ASysarea;

public interface MongoService {	
	List<ASysarea> getAllArea();	
	void addList(List<ASysarea> list);
}
