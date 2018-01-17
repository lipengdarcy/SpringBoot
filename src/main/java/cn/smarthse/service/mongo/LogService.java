package cn.smarthse.service.mongo;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import cn.smarthse.core.model.ASystem.ASyslog;
import cn.smarthse.core.model.common.JqGridData;

public interface LogService {

	List<ASyslog> getAllFromMysql();

	List<ASyslog> getAll();

	public JqGridData<ASyslog> getAll(PageRequest pageRequest);

	void addList(List<ASyslog> list);
}
