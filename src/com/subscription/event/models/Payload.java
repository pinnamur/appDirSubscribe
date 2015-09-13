package com.subscription.event.models;

import javax.xml.bind.annotation.XmlRootElement;

import com.subscription.event.models.payload.Company;
import com.subscription.event.models.payload.Configuration;
import com.subscription.event.models.payload.Order;

@XmlRootElement
public class Payload {

	Company company;
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Configuration getConfig() {
		return config;
	}
	public void setConfig(Configuration config) {
		this.config = config;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	Configuration config;
	Order order;
	
}




