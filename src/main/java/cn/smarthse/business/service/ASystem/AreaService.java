package cn.smarthse.business.service.ASystem;

import java.util.List;

import cn.smarthse.business.model.system.ASysarea;
import cn.smarthse.framework.model.JqGridData;
import cn.smarthse.framework.model.JqGridParam;

public interface AreaService {

	List<ASysarea> getAll();

	public JqGridData<ASysarea> getAll(JqGridParam param);
}
