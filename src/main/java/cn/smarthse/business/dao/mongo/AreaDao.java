package cn.smarthse.business.dao.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import cn.smarthse.business.model.system.ASysarea;

/**
 * mongodb 行政区域
 */


public interface AreaDao extends MongoRepository<ASysarea, String> {

}
