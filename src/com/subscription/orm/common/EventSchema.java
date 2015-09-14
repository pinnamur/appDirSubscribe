package com.subscription.orm.common;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * The schema object that is actually persisted to the database.
 */
@Entity  
@Table (name="events")
public class EventSchema implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id  
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private Integer id;
	
	private String email;
	private String editionCode;

	private String fullName;
	
	public EventSchema(){
		
	}
	
	public EventSchema(String fullName, String email, String editionCode) {
		this.setFullName(fullName);
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

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEditionCode(String editionCode) {
		this.editionCode = editionCode;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
