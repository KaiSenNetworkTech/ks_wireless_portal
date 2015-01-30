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
		private String mobilePhoneNo;
		private String newPassword;
		private String verificationCode;

		public String getMobilePhoneNo() {
			return mobilePhoneNo;
		}

		public void setMobilePhoneNo(String mobilePhoneNo) {
			this.mobilePhoneNo = mobilePhoneNo;
		}

		public String getNewPassword() {
			return newPassword;
		}

		public void setNewPassword(String newPassword) {
			this.newPassword = newPassword;
		}

		public String getVerificationCode() {
			return verificationCode;
		}

		public void setVerificationCode(String verificationCode) {
			this.verificationCode = verificationCode;
		}
	}
}
