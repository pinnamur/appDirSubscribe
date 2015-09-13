package com.subscription.common;

public class OrderData implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private String email;
	private String editionCode;
	
	public OrderData(String email, String editionCode) {
		this.email = email;
		this.editionCode = editionCode;
		
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEmail() {
		return email;
	}

	public String getEditionCode() {
		return editionCode;
	}

}
