package com.subscription.event.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Creator {
	
	private final static String EMPTY_STRING = "";
	
	String email;
	String firstName;
	String lastName;
	String language;
	String openId;
	String uuid;
	
	public String getFullName() {
		if (firstName == null && lastName == null) {
			return EMPTY_STRING;
		}
		return getEmptyIfNull(firstName) + " " + getEmptyIfNull(lastName);
	}
	
	private String getEmptyIfNull(String name) {
		return name != null ? name : "";
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	

}
