package com.kaisen.wirelessportal.services;

import org.springframework.stereotype.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.kaisen.common.result.CallServiceResult;
import com.kaisen.usercenter.domain.UserInfoDO;
import com.kaisen.usercenter.service.IUserService;
import com.kaisen.wirelessportal.WirelessPortalResult;

@Controller(value = "updateUserInfo")
public class UpdateUserInfoService extends BaseService<UserInfoDO> {
	@Reference(version = "1.0.0")
	private IUserService userService;

	@Override
	public WirelessPortalResult doBusiness(UserInfoDO userInfoDO) {
		// 修改密码走modifyPassword接口
		userInfoDO.setPassword(null);
		CallServiceResult<Void> callServiceResult = userService
				.updateUserInfo(userInfoDO);

		return getResult(callServiceResult);
	}

	@Override
	public boolean needLogin() {
		return true;
	}

	@Override
	protected UserInfoDO parseRequestBody(String requestBody) {
		return JSON.parseObject(requestBody, UserInfoDO.class);
	}
}
