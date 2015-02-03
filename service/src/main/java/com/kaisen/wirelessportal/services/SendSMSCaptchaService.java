package com.kaisen.wirelessportal.services;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import wirelessportal.common.utils.CaptchaCode;

import com.kaisen.common.result.ResultEnum;
import com.kaisen.wirelessportal.WirelessPortalResult;

public class SendSMSCaptchaService extends BaseService<String> {
	@Override
	protected WirelessPortalResult doBusiness(final String mobilePhoneNo) {
		if (StringUtils.isBlank(mobilePhoneNo)) {
			return WirelessPortalResult
					.buildErrorResult(ResultEnum.MOBILE_PHONE_NO_IS_NULL);
		} else {
			// 6位验证码，5分钟内可使用100次
			CaptchaCode captchaCode = new CaptchaCode(
					String.valueOf(RandomUtils.nextInt(100000, 1000000)), 5,
					100);
			super.session.setAttribute("CaptchaCode", captchaCode);
			return null;
		}
	}

	@Override
	protected String parseRequestBody(String requestBody) {
		return requestBody;
	}

	public static void main(String[] args) {
		System.out.println(RandomUtils.nextInt(100000, 1000000));
	}
}
