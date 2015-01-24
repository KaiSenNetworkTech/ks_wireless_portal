package com.kaisen.wirelessportal;

import java.io.Serializable;

public class PublicParams implements Serializable {
	private static final long serialVersionUID = -6039406222773476607L;
	private Integer apiVer;
	private Integer appVer;
	private String app;
	private String sign;
	private Long appTime;

	public Integer getApiVer() {
		return apiVer;
	}

	public void setApiVer(Integer apiVer) {
		this.apiVer = apiVer;
	}

	public Integer getAppVer() {
		return appVer;
	}

	public void setAppVer(Integer appVer) {
		this.appVer = appVer;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Long getAppTime() {
		return appTime;
	}

	public void setAppTime(Long appTime) {
		this.appTime = appTime;
	}
}