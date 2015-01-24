package com.kaisen.wirelessportal;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * 总入口
 * 
 * @author Administrator
 */

@Controller
@RequestMapping("/interfaces")
public class WirelessPortalMain {
	@Resource
	private ApplicationContext applicationContext;

	@RequestMapping(value = "{interfaceName}", consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String processJSON(@PathVariable String interfaceName,
			@RequestBody String requestBodyStr) {
		WirelessPortalService service = (WirelessPortalService) applicationContext
				.getBean(interfaceName);
		WirelessPortalResult result = service
				.process((WirelessPortalReqBody<?>) JSON.parseObject(
						requestBodyStr, service.getPrivateParamsMappingClass()));

		return JSON.toJSONString(result);
	}

	@RequestMapping(value = "{interfaceName}", params = "format=xml", consumes = "application/xml; charset=UTF-8", produces = "application/xml; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String processXML(HttpServletRequest request,
			HttpServletResponse response, @PathVariable String interfaceName) {
		return null;
	}

	// @Override
	// public void setApplicationContext(ApplicationContext applicationContext)
	// throws BeansException {
	// this.applicationContext = applicationContext;
	// }

	@PostConstruct
	private void init() throws Exception {

	}
}
