package com.amdocs.assignment.assignment.vo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProfileServiceData {
	@Id
	private String name;
	private String address;
	private Long phone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

}
