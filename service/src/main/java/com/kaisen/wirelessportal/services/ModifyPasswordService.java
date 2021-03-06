package com.kaisen.wirelessportal.services;

import static com.kaisen.common.result.ResultEnum.PASSWORD_FORMAT_ERROR;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;

import wirelessportal.common.utils.PasswordUtil;

import com.alibaba.fastjson.JSON;
import com.kaisen.common.result.ResultEnum;
import com.kaisen.usercenter.domain.UserInfoDO;
import com.kaisen.wirelessportal.WirelessPortalResult;

@Controller(value = "modifyPassword")
public class ModifyPasswordService extends
		BaseService<ModifyPasswordService.ModifyPasswordDO> {
	@Override
	public WirelessPortalResult doBusiness(
			final ModifyPasswordDO modifyPasswordDO) {
		if (PasswordUtil.passwordFormatCheck(modifyPasswordDO.newPassword)) {
			UserInfoDO userInfoDO = super.getUserInfoFromSession();
			userInfoDO.setPassword(modifyPasswordDO.newPassword);

			return StringUtils.equals(
					DigestUtils.md5Hex(modifyPasswordDO.oldPassword),
					userInfoDO.getPassword()) ? getResult(userService
					.updateUserInfo(userInfoDO)) : WirelessPortalResult
					.buildErrorResult(ResultEnum.OLD_PASSWORD_ERROR);
		} else
			return WirelessPortalResult.buildErrorResult(PASSWORD_FORMAT_ERROR);
	}

	@Override
	public boolean needLogin() {
		return true;
	}

	@Override
	protected ModifyPasswordDO parseRequestBody(String requestBody) {
		return JSON.parseObject(requestBody, ModifyPasswordDO.class);
	}

	class ModifyPasswordDO {
		public String oldPassword;
		public String newPassword;
	}
}
