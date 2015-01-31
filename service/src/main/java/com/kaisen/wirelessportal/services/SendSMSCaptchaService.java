package com.kaisen.wirelessportal.services;

import org.apache.commons.lang3.StringUtils;

import com.kaisen.wirelessportal.WirelessPortalResult;

public class SendSMSCaptchaService extends BaseService<String> {
	@Override
	protected WirelessPortalResult doBusiness(String mobilePhoneNo) {
		if (StringUtils.isBlank(mobilePhoneNo)) {
			
		}

		return null;
	}

	@Override
	protected String parseRequestBody(String requestBody) {
		return requestBody;
	}
}
