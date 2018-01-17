package cn.smarthse.service.impl.ASystem;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.smarthse.core.dao.GenericDao;
import cn.smarthse.core.dao.GenericServiceImpl;
import cn.smarthse.core.dao.ASystem.ASysareaMapper;
import cn.smarthse.core.model.ASystem.ASysarea;
import cn.smarthse.core.model.ASystem.ASysareaExample;
import cn.smarthse.core.model.common.JqGridData;
import cn.smarthse.core.model.common.JqGridParam;
import cn.smarthse.service.ASystem.AreaService;

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
