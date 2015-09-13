package com.subscription.event.models.payload;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Order {

	String editionCode;
	String pricingDuration;
	Item[] item;
	public String getEditionCode() {
		return editionCode;
	}
	public void setEditionCode(String editionCode) {
		this.editionCode = editionCode;
	}
	public String getPricingDuration() {
		return pricingDuration;
	}
	public void setPricingDuration(String pricingDuration) {
		this.pricingDuration = pricingDuration;
	}
	public Item[] getItem() {
		return item;
	}
	public void setItem(Item[] item) {
		this.item = item;
	}
	

}
