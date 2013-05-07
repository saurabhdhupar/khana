package com.ebay.app.raptor.controller;

public class RestaurantDO {
	private String address;
	private String phoneNumber;
	private String name;
	
	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "RestaurantDO [address=" + address + ", phoneNumber="
				+ phoneNumber + ", name=" + name + "]";
	}
	
	

}
