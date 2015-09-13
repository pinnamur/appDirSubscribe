package com.subscription.event.models.payload;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Configuration {
	Entry entry;

	public Entry getEntry() {
		return entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}
	
}