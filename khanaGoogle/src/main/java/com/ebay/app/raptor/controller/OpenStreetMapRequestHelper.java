package com.ebay.app.raptor.controller;


public class OpenStreetMapRequestHelper {
	
	public OpenMapRequest createRequest(String zipCode,
			String location, String state, String country) {
		OpenMapRequest request = new OpenMapRequest();
		request.setZipCode(zipCode);
		request.setLocation(location);
		request.setState(state);
		request.setCountry(CountryEnum.getEnum(country));
		request.setPolygon(true);
		request.setFormat("json");
		return request;
	}

}
