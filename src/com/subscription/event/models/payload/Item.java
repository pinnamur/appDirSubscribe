package com.subscription.event.models.payload;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Item
{
	int quantity;
	String unit;
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
}