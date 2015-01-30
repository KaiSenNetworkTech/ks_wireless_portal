package com.kaisen.wirelessportal;

public interface WirelessPortalService {
	WirelessPortalResult process(String requestBody);

	boolean needLogin();

	boolean isLogin();

	boolean isNotLogin();
}
