package com.kaisen.wirelessportal.service.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kaisen.wirelessportal.service.ISayHelloWorld;

@Controller
@RequestMapping("test")
public class SayHelloWorldImpl implements ISayHelloWorld {
	@Reference(version = "1.0.0")
	private com.kaisen.usercenter.service.ISayHelloWorld sayHelloWorld;

	@Override
	@RequestMapping(value = "sayHelloWorld/{userName}", produces = "application/xml; charset=UTF-8")
	@ResponseBody
	public String sayHelloWorld(@PathVariable String userName) {
		return sayHelloWorld.sayHelloWorld() + userName;
	}

	@Override
	@RequestMapping(value = "sayHelloWorld/{userName}", produces = "application/json; charset=UTF-8", params = "format=json")
	@ResponseBody
	public String sayHelloWorld_JSON(@PathVariable String userName) {
		return sayHelloWorld.sayHelloWorld() + userName;
	}
}
