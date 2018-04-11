package cn.smarthse.business.service.mongo;

import java.util.List;

import cn.smarthse.business.model.system.SysAreaStandard;

public interface MongoService {	
	List<SysAreaStandard> getAllArea();	
	void addList(List<SysAreaStandard> list);
}
