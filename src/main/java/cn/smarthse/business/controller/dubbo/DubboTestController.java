package cn.smarthse.business.controller.dubbo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.smarthse.business.service.dubbo.DubboTestService;

@RestController
public class DubboTestController {

	// 本地服务
	@Autowired
	private DubboTestService DubboTestService;

	// 远程服务
	@Autowired
	private cn.smarthse.modules.platform.provider.IPUserService IPUserService;

	@RequestMapping("/dubbo")
	public String saveUser() {
		try {
			IPUserService.getUserInfo("ff2993436eaa4347967cc7f441b53b68");
		}catch(Exception e) {
			//没有提供者时候就异常啦
			e.printStackTrace();
		}		
		return DubboTestService.sayHi();
	}
}