package com.kaisen.wirelessportal.services;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kaisen.common.result.CallServiceResult;
import com.kaisen.common.result.ResultEnum;
import com.kaisen.usercenter.domain.UserInfoDO;
import com.kaisen.wirelessportal.WirelessPortalResult;
import com.kaisen.wirelessportal.WirelessPortalService;

public abstract class BaseService implements WirelessPortalService {
	@Resource
	protected HttpServletRequest request;

	@Resource
	protected HttpSession session;

	protected WirelessPortalResult getResult(
			CallServiceResult<?> callServiceResult) {
		WirelessPortalResult result = new WirelessPortalResult();

		ResultEnum resultEnum = ResultEnum.INTERNAL_ERROR;
		if (callServiceResult != null
				&& callServiceResult.getResultEnum() != null) {
			resultEnum = callServiceResult.getResultEnum();
		}

		result.setResultCode(resultEnum.getResultCode());
		result.setMessage(resultEnum.getMessage());
		result.setData(callServiceResult == null ? null : callServiceResult
				.getReturnObject());

		return result;
	}

	@Override
	public boolean needLogin() {
		return false;
	}

	@Override
	public boolean isLogin() {
		return session.getAttribute("USER_INFO") != null;
	}

	@Override
	public boolean isNotLogin() {
		return !this.isLogin();
	}

	protected UserInfoDO getUserInfoFromSession() {
		return (UserInfoDO) session.getAttribute("USER_INFO");
	}
}
