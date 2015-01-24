package com.kaisen.wirelessportal;

import java.io.Serializable;

import com.kaisen.common.result.ResultEnum;

public class WirelessPortalResult implements Serializable {
	private static final long serialVersionUID = -2630167382685485457L;
	private ResultEnum resultEnum;
	private Object data;

	public ResultEnum getResultEnum() {
		return resultEnum;
	}

	public void setResultEnum(ResultEnum resultEnum) {
		this.resultEnum = resultEnum;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
