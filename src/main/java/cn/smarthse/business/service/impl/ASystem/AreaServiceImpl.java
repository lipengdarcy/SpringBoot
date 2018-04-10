package cn.smarthse.business.service.impl.ASystem;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.smarthse.business.dao.system.ASysareaMapper;
import cn.smarthse.business.model.system.ASysarea;
import cn.smarthse.business.model.system.ASysareaExample;
import cn.smarthse.business.service.ASystem.AreaService;
import cn.smarthse.framework.generic.GenericDao;
import cn.smarthse.framework.generic.GenericServiceImpl;
import cn.smarthse.framework.model.JqGridData;
import cn.smarthse.framework.model.JqGridParam;

@Service
public class AreaServiceImpl extends GenericServiceImpl<ASysarea> implements AreaService {

	@Resource
	ASysareaMapper ASysareaMapper;

	@Override
	public GenericDao<ASysarea> getDao() {
		return ASysareaMapper;
	}

	@Override
	public List<ASysarea> getAll() {
		return super.selectList();
	}

	/**
	 * 分页查询数据,返回grid格式的数据
	 * 
	 */
	@Override
	public JqGridData<ASysarea> getAll(JqGridParam param) {
		ASysareaExample e = new ASysareaExample();
		//ASysareaExample.Criteria c = e.createCriteria();
		ASysareaMapper.selectByExample(e);
		if (!StringUtils.isEmpty(param.getSidx()))
			e.setOrderByClause(param.getSidx() + " " + param.getSord());
		PageHelper.startPage((int) param.getPage(), (int) param.getRows());
		Page<ASysarea> list = (Page<ASysarea>) this.getAll();
		JqGridData<ASysarea> data = new JqGridData<ASysarea>(list, param);
		return data;
	}

}
