package cn.smarthse.core.dao.mongo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.smarthse.core.model.ASystem.ASyslog;

/**
 * mongodb 系统日志
 */

public interface LogDao extends PagingAndSortingRepository<ASyslog, String> {

	// 这个方法名不能乱写，findByXXX，那么对于的类中必须有XXX字段。也就是说对应的数据库中一定要存在XXX字段对应的列
	public Page<ASyslog> findByuid(String uid, Pageable pageable);

}
