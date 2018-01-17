package cn.smarthse.service.ASystem;

import java.util.List;

import cn.smarthse.core.model.ASystem.ASysarea;
import cn.smarthse.core.model.common.JqGridData;
import cn.smarthse.core.model.common.JqGridParam;

public interface AreaService {

	List<ASysarea> getAll();

	public JqGridData<ASysarea> getAll(JqGridParam param);
}
