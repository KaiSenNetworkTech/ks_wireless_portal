package com.kaisen.wirelessportal.services;

import static com.kaisen.common.result.ResultEnum.PASSWORD_FORMAT_ERROR;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;

import wirelessportal.common.utils.PasswordUtil;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.kaisen.common.result.ResultEnum;
import com.kaisen.usercenter.domain.UserInfoDO;
import com.kaisen.usercenter.service.IUserService;
import com.kaisen.wirelessportal.WirelessPortalResult;

@Controller(value = "modifyPassword")
public class ModifyPasswordService extends
		BaseService<ModifyPasswordService.ModifyPasswordDO> {
	@Reference(version = "1.0.0")
	private IUserService userService;

	@Override
	public WirelessPortalResult doBusiness(ModifyPasswordDO modifyPasswordDO) {
		if (PasswordUtil.passwordFormatCheck(modifyPasswordDO.getNewPassword())) {
			UserInfoDO userInfoDO = super.getUserInfoFromSession();
			userInfoDO.setPassword(modifyPasswordDO.getNewPassword());

			return StringUtils.equals(
					DigestUtils.md5Hex(modifyPasswordDO.getOldPassword()),
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
		private String oldPassword;
		private String newPassword;

		public String getOldPassword() {
			return oldPassword;
		}

		public void setOldPassword(String oldPassword) {
			this.oldPassword = oldPassword;
		}

		public String getNewPassword() {
			return newPassword;
		}

		public void setNewPassword(String newPassword) {
			this.newPassword = newPassword;
		}
	}
}
