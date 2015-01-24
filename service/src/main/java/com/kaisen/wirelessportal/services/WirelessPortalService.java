package com.kaisen.wirelessportal.services;

import com.kaisen.wirelessportal.WirelessPortalReqBody;
import com.kaisen.wirelessportal.WirelessPortalResult;

public interface WirelessPortalService {
	WirelessPortalResult process(WirelessPortalReqBody<?> requestBody);

	Class<?> getPrivateParamsMappingClass();
}
