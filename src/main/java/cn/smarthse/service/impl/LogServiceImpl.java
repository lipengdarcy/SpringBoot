package cn.smarthse.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import cn.smarthse.core.dao.GenericDao;
import cn.smarthse.core.dao.GenericServiceImpl;
import cn.smarthse.core.dao.ASystem.ASyslogMapper;
import cn.smarthse.core.dao.mongo.LogDao;
import cn.smarthse.core.model.ASystem.ASyslog;
import cn.smarthse.core.model.common.JqGridData;
import cn.smarthse.service.mongo.LogService;

@Service
public class LogServiceImpl extends GenericServiceImpl<ASyslog> implements LogService {

	@Resource
	LogDao LogDao;

	@Resource
	ASyslogMapper ASyslogMapper;

	@Override
	public GenericDao<ASyslog> getDao() {
		return ASyslogMapper;
	}

	@Override
	public List<ASyslog> getAllFromMysql() {
		return ASyslogMapper.selectByExample(null);
	}

	@Override
	public void addList(List<ASyslog> list) {
		LogDao.save(list);
	}

	/**
	 * 分页查询日志数据,返回grid格式的数据
	 * 
	 */
	@Override
	public JqGridData<ASyslog> getAll(PageRequest pageRequest) {
		Page<ASyslog> data = LogDao.findAll(pageRequest);
		JqGridData<ASyslog> result = new JqGridData<ASyslog>(data);
		return result;
	}

	/**
	 * 查询所有日志数据,返回List格式的数据
	 * 
	 */
	@Override
	public List<ASyslog> getAll() {
		List<ASyslog> list = new ArrayList<ASyslog>();
		Iterable<ASyslog> data = LogDao.findAll();
		Iterator<ASyslog> it = data.iterator();
		while (it.hasNext()) {
			list.add(it.next());
		}
		return list;
	}

}
