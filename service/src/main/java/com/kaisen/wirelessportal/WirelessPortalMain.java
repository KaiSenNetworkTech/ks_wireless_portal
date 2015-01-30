package com.kaisen.wirelessportal;

import static com.kaisen.wirelessportal.WirelessPortalResult.ILLEGAL_REQUEST_ERROR_RESULT;
import static com.kaisen.wirelessportal.WirelessPortalResult.NOT_LOGIN_ERROR_RESULT;
import static com.kaisen.wirelessportal.WirelessPortalResult.PARAMS_ERROR_RESULT;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
@RequestMapping("/")
public class WirelessPortalMain {
	private static final Logger logger = LoggerFactory
			.getLogger(WirelessPortalMain.class);

	@Resource
	private ApplicationContext applicationContext;

	@RequestMapping(value = "api/{interfaceName}", consumes = "application/json; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String execute(@RequestHeader HttpHeaders headers,
			@PathVariable String interfaceName,
			@RequestBody String requestBodyStr) {
		WirelessPortalResult result = null;
		WirelessPortalService service = (WirelessPortalService) applicationContext
				.getBean(interfaceName);
		if (!verifySignature(interfaceName, headers)) {
			logger.warn("Illegal request,interfaceName={},headers={}",
					interfaceName, headers);
			result = ILLEGAL_REQUEST_ERROR_RESULT;
		} else if (service == null) {
			logger.warn("Service processer is null,interfaceName="
					+ interfaceName);
			result = PARAMS_ERROR_RESULT;
		} else if (service.needLogin() && service.isNotLogin()) {
			logger.debug("User not login,interfaceName={},headers={}",
					interfaceName, headers);
			result = NOT_LOGIN_ERROR_RESULT;
		} else
			result = service.process(requestBodyStr);

		return JSON.toJSONString(result);
	}

	private static final String API_VER_KEY = "API_VER";
	private static final String APP_VER_KEY = "APP_VER";
	private static final String SYSTEM_PLATFORM_KEY = "SYSTEM_PLATFORM";
	private static final String TIMESTAMP_KEY = "TIMESTAMP";
	private static final String SIGN_KEY = "SIGN";
	private static final String MD5_ENCRYP_KEY = "6CUFV2'0#D@4&>R5Q%()S}Z*M`YJ_]!$LENBH|P/1?,X+G^[=.O3-T98{;KA7~W<:I";

	private boolean verifySignature(String interfaceName, HttpHeaders headers) {
		if (StringUtils.isBlank(interfaceName)
				|| CollectionUtils.isEmpty(headers))
			return false;
		else {
			String apiVer = headers.getFirst(API_VER_KEY);
			String appVer = headers.getFirst(APP_VER_KEY);
			String systemPlatform = headers.getFirst(SYSTEM_PLATFORM_KEY);
			String timestamp = headers.getFirst(TIMESTAMP_KEY);
			String sign = headers.getFirst(SIGN_KEY);

			if (StringUtils.isAnyBlank(apiVer, appVer, systemPlatform,
					timestamp, sign))
				return false;
			else
				return sign.equals(DigestUtils.md5Hex(new StringBuilder(128)
						.append(apiVer).append(appVer).append(systemPlatform)
						.append(timestamp).append(MD5_ENCRYP_KEY).toString()));
		}
	}
}
