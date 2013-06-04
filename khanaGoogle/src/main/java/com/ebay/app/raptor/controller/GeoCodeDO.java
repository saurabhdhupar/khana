package com.ebay.app.raptor.controller;

public class GeoCodeDO {
	private double latitude;
	private double longitude;
	
	public GeoCodeDO(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "GeoCodeDO [latitude=" + latitude + ", longitude=" + longitude
				+ "]";
	}
	
}
