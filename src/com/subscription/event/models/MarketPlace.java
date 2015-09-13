package com.subscription.event.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MarketPlace {

	String baseUrl;
	String partner;
	public String getBaseUrl() {
		return baseUrl;
	}
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	
}
