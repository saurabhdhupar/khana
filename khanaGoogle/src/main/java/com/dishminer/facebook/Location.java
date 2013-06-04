
package com.dishminer.facebook;

import java.util.List;

public class Location{
   	private String city;
   	private String country;
   	private String latitude;
   	private String longitude;
   	private String state;
   	private String street;
   	private String zip;

 	public String getCity(){
		return this.city;
	}
	public void setCity(String city){
		this.city = city;
	}
 	public String getCountry(){
		return this.country;
	}
	public void setCountry(String country){
		this.country = country;
	}
 	public String getLatitude(){
		return this.latitude;
	}
	public void setLatitude(String latitude){
		this.latitude = latitude;
	}
 	public String getLongitude(){
		return this.longitude;
	}
	public void setLongitude(String longitude){
		this.longitude = longitude;
	}
 	public String getState(){
		return this.state;
	}
	public void setState(String state){
		this.state = state;
	}
 	public String getStreet(){
		return this.street;
	}
	public void setStreet(String street){
		this.street = street;
	}
 	public String getZip(){
		return this.zip;
	}
	public void setZip(String zip){
		this.zip = zip;
	}
}
