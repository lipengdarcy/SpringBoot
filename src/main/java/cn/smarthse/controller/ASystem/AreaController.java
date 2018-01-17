package cn.smarthse.controller.ASystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.smarthse.core.model.ASystem.ASysarea;
import cn.smarthse.core.model.common.JqGridData;
import cn.smarthse.core.model.common.JqGridParam;
import cn.smarthse.service.ASystem.AreaService;

@Controller
@RequestMapping("/area")
public class AreaController {
	@Autowired
	private AreaService AreaService;

	@RequestMapping()
	public String init(ModelMap m) {
		return "ASystem/areaList";
	}

	@RequestMapping("/getData")
	@ResponseBody
	public JqGridData<ASysarea> get(JqGridParam param) {
		JqGridData<ASysarea> list = AreaService.getAll(param);
		return list;
	}

}
