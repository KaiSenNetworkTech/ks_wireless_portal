package com.kaisen.wirelessportal.services;

import static com.kaisen.common.result.ResultEnum.PASSWORD_FORMAT_ERROR;

import org.springframework.stereotype.Controller;

import wirelessportal.common.utils.PasswordUtil;

import com.alibaba.fastjson.JSON;
import com.kaisen.usercenter.domain.UserInfoDO;
import com.kaisen.wirelessportal.WirelessPortalResult;

@Controller(value = "userRegister")
public class UserRegisterService extends
		BaseService<UserRegisterService.UserRegisterDO> {

	@Override
	public WirelessPortalResult doBusiness(final UserRegisterDO userRegisterDO) {
		return PasswordUtil.passwordFormatCheck(userRegisterDO.getPassword()) ? getResult(userService
				.register(userRegisterDO)) : WirelessPortalResult
				.buildErrorResult(PASSWORD_FORMAT_ERROR);
	}

	@Override
	protected UserRegisterDO parseRequestBody(String requestBody) {
		return JSON.parseObject(requestBody, UserRegisterDO.class);
	}

	class UserRegisterDO extends UserInfoDO {
		private static final long serialVersionUID = -7818125790395418340L;
		private String captchaCode;

		public String getCaptchaCode() {
			return captchaCode;
		}

		public void setCaptchaCode(String captchaCode) {
			this.captchaCode = captchaCode;
		}
	}
}