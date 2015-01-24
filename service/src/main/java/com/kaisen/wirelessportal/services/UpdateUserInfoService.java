package com.kaisen.wirelessportal.services;

import org.springframework.stereotype.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kaisen.common.result.CallServiceResult;
import com.kaisen.usercenter.domain.UserInfoDO;
import com.kaisen.usercenter.service.IUserService;
import com.kaisen.wirelessportal.WirelessPortalReqBody;
import com.kaisen.wirelessportal.WirelessPortalResult;
import com.kaisen.wirelessportal.params.mapping.UserInfoMapping;

@Controller(value = "updateUserInfo")
public class UpdateUserInfoService extends BaseService {
	@Reference(version = "1.0.0")
	private IUserService userService;

	@Override
	public WirelessPortalResult process(WirelessPortalReqBody<?> requestBody) {
		WirelessPortalResult result = new WirelessPortalResult();

		UserInfoDO userInfoDO = (UserInfoDO) requestBody.getPrivateParams();

		CallServiceResult<Void> callServiceResult = userService
				.updateUserInfo(userInfoDO);

		result.setResultEnum(callServiceResult.getResultEnum());
		return result;
	}

	@Override
	public Class<UserInfoMapping> getPrivateParamsMappingClass() {
		return UserInfoMapping.class;
	}
}
