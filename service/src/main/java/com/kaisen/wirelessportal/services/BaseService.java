package com.kaisen.wirelessportal.services;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class BaseService implements WirelessPortalService {
	@Resource
	protected HttpServletRequest request;

	@Resource
	protected HttpSession session;
}
