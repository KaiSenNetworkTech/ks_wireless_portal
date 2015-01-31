package com.kaisen.wirelessportal.services;

import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.kaisen.wirelessportal.WirelessPortalResult;

@Controller(value = "resetPassword")
public class ResetPasswordService extends
		BaseService<ResetPasswordService.ResetPasswordDO> {

	@Override
	protected WirelessPortalResult doBusiness(ResetPasswordDO resetPasswordDO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ResetPasswordDO parseRequestBody(String requestBody) {
		return JSON.parseObject(requestBody, ResetPasswordDO.class);
	}

	class ResetPasswordDO {
		public String mobilePhoneNo;
		public String newPassword;
		public String inputCaptchaCode;
	}
}
