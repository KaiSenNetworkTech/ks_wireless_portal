package com.kaisen.wirelessportal;

import java.io.Serializable;

public class WirelessPortalResult implements Serializable {
	private static final long serialVersionUID = -2630167382685485457L;
	private Integer resultCode; // 错误码
	private String message; // 错误信息

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
}
