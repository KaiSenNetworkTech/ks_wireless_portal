package com.kaisen.wirelessportal.services;

import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.kaisen.common.result.CallServiceResult;
import com.kaisen.usercenter.domain.UserInfoDO;
import com.kaisen.wirelessportal.WirelessPortalResult;

@Controller(value = "userAuthentication")
public class UserAuthenticationService extends BaseService<UserInfoDO> {
	@Override
	public WirelessPortalResult doBusiness(final UserInfoDO userInfoDO) {
		CallServiceResult<UserInfoDO> callServiceResult = userService
				.authentication(userInfoDO);
		if (callServiceResult.isSuccessful()) {
			session.setAttribute("USER_INFO",
					callServiceResult.getReturnObject());
		}

		return getResult(callServiceResult);
	}

	@Override
	protected UserInfoDO parseRequestBody(String requestBody) {
		return JSON.parseObject(requestBody, UserInfoDO.class);
	}
}
