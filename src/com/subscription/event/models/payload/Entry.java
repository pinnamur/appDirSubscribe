package com.subscription.event.models.payload;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Entry
{
	String key;
	String value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}