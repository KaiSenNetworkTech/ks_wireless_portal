package com.kaisen.wirelessportal;

import com.kaisen.wirelessportal.WirelessPortalReqBody;
import com.kaisen.wirelessportal.WirelessPortalResult;

public interface WirelessPortalService {
	WirelessPortalResult process(WirelessPortalReqBody<?> requestBody);

	Class<?> getPrivateParamsMappingClass();

	boolean needLogin();

	boolean isLogin();

	boolean isNotLogin();
}
