package cn.smarthse.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.smarthse.business.model.system.ASysarea;
import cn.smarthse.business.service.RedisClient;

@Controller
@RequestMapping("/redis")
public class RedisController {
	
	@Autowired
	private RedisClient redisClient;
	
	@RequestMapping("/get")
	@ResponseBody
	public Object get()throws Exception{
		String string = redisClient.get("5");
		ASysarea player = null;
		//player = new ObjectMapper().readValue(string,Player.class);
		player = redisClient.getObject("5",ASysarea.class);
		return player;
	}

	
	
}





