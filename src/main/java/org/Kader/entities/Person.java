package org.Kader.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Table
@Entity
public class Person implements Serializable{

	private static final long serialVersionUID = -2607136568019798924L;
	
	
		@Id
	    private String id;
	    private String name;
	    private String mobile;
	    private String email;
	    private String[] address;
	    
	    public Person() {
		// TODO Auto-generated constructor stub
	    }

	   public Person(String id, String name, String mobile, String email, String[] address) {
		super();
		this.id = id;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
	    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String[] getAddress() {
		return address;
	}

	public void setAddress(String[] address) {
		this.address = address;
	}
	   
	    
	   

}
