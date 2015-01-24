package com.kaisen.wirelessportal;

public class WirelessPortalReqBody<T> {
	private PublicParams publicParams;
	private T privateParams;

	public PublicParams getPublicParams() {
		return publicParams;
	}

	public void setPublicParams(PublicParams publicParams) {
		this.publicParams = publicParams;
	}

	public T getPrivateParams() {
		return privateParams;
	}

	public void setPrivateParams(T privateParams) {
		this.privateParams = privateParams;
	}
}
