package com.subscription.event.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Event {
	String type;
	MarketPlace marketPlace;
	String flag;
	Creator creator;
	Payload payload;
	String returnUrl;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public MarketPlace getMarketPlace() {
		return marketPlace;
	}
	public void setMarketPlace(MarketPlace marketPlace) {
		this.marketPlace = marketPlace;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public Creator getCreator() {
		return creator;
	}
	public void setCreator(Creator creator) {
		this.creator = creator;
	}
	public Payload getPayload() {
		return payload;
	}
	public void setPayload(Payload payload) {
		this.payload = payload;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	
}
