package cn.smarthse.business.controller.mongo;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.smarthse.business.service.ASystem.AreaService;
import cn.smarthse.business.service.mongo.LogService;
import cn.smarthse.business.service.mongo.MongoService;
import cn.smarthse.core.model.ASystem.ASysarea;
import cn.smarthse.core.model.ASystem.ASyslog;
import cn.smarthse.core.model.common.JqGridData;
import cn.smarthse.core.model.common.JqGridParam;
import cn.smarthse.core.model.mongo.Customer;

@Controller
@RequestMapping("/mongo")
public class MongoController {

	@Autowired
	private AreaService AreaService;

	@Autowired
	private MongoService MongoService;

	@Autowired
	private LogService LogService;

	@RequestMapping()
	public String init(ModelMap m) {
		List<Customer> list = MongoService.getAll();
		m.put("list", list);
		List<ASysarea> areaList = MongoService.getAllArea();
		m.put("areaList", areaList);
		return "Mongo/mongoList";
	}

	/**
	 * 系统日志页面
	 */
	@RequestMapping("logList")
	public String logList(ModelMap m) {
		return "Mongo/logList";
	}

	/**
	 * 系统日志页面数据
	 */
	@ResponseBody
	@RequestMapping("logData")
	public JqGridData<ASyslog> logData(JqGridParam param, ModelMap m, HttpSession session) {
		JqGridData<ASyslog> data = LogService.getAll(param.buildPageRequest());
		return data;
	}

	/**
	 * mysql导入mongo系统日志
	 */
	@RequestMapping("importLog")
	@ResponseBody
	public String importLog(ModelMap m) {
		List<ASyslog> list = LogService.getAllFromMysql();
		// LogService.addList(list);
		return "mysql日志导入mongo成功，导入记录数：" + list.size();
	}

	@RequestMapping("import")
	@ResponseBody
	public String inportFromMysql(ModelMap m) {
		List<ASysarea> list = AreaService.getAll();
		// MongoService.addList(list);
		return "mysql数据导入mongo成功，导入记录数：" + list.size();
	}

}
