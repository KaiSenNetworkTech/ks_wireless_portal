package com.kaisen.wirelessportal.services;

import static com.kaisen.common.result.ResultEnum.PASSWORD_FORMAT_ERROR;

import org.springframework.stereotype.Controller;

import wirelessportal.common.utils.PasswordUtil;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.kaisen.usercenter.domain.UserInfoDO;
import com.kaisen.usercenter.service.IUserService;
import com.kaisen.wirelessportal.WirelessPortalResult;

@Controller(value = "userRegister")
public class UserRegisterService extends BaseService<UserInfoDO> {
	@Reference(version = "1.0.0")
	private IUserService userService;

	@Override
	public WirelessPortalResult doBusiness(UserInfoDO userInfoDO) {
		return PasswordUtil.passwordFormatCheck(userInfoDO.getPassword()) ? getResult(userService
				.register(userInfoDO)) : WirelessPortalResult
				.buildErrorResult(PASSWORD_FORMAT_ERROR);
	}

	@Override
	protected UserInfoDO parseRequestBody(String requestBody) {
		return JSON.parseObject(requestBody, UserInfoDO.class);
	}
}