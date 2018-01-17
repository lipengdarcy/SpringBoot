package cn.smarthse.core.dao.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import cn.smarthse.core.model.ASystem.ASysarea;

/**
 * mongodb 行政区域
 */


public interface AreaDao extends MongoRepository<ASysarea, String> {

}
