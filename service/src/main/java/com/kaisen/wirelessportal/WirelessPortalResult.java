package com.kaisen.wirelessportal;

import java.io.Serializable;

import com.kaisen.common.result.ResultEnum;

public class WirelessPortalResult implements Serializable {
	private static final long serialVersionUID = -2630167382685485457L;
	private Integer resultCode; // 错误码
	private String message; // 错误信息

	public final static WirelessPortalResult PARAMS_ERROR_RESULT = buildErrorResult(ResultEnum.PARAMS_ERROR);
	public final static WirelessPortalResult NOT_LOGIN_ERROR_RESULT = buildErrorResult(ResultEnum.NOT_LOGIN_ERROR);
	public final static WirelessPortalResult ILLEGAL_REQUEST_ERROR_RESULT = buildErrorResult(ResultEnum.ILLEGAL_REQUEST_ERROR);

	public Integer getResultCode() {
		return resultCode;
	}

	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static WirelessPortalResult buildErrorResult(ResultEnum resultEnum) {
		WirelessPortalResult result = new WirelessPortalResult();
		result.setResultCode(resultEnum.getResultCode());
		result.setMessage(resultEnum.getMessage());
		return result;
	}
}
