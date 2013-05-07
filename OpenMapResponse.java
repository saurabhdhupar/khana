package com.ebay.app.raptor.controller;

import java.util.HashSet;
import java.util.Set;

public class OpenMapResponse {
	Set<GeoCodeDO> geoCode = new HashSet<GeoCodeDO>();
	
	public OpenMapResponse() {
	}
	public Set<GeoCodeDO> getGeoCode() {
		return geoCode;
	}
	public void setGeoCode(Set<GeoCodeDO> geoCode) {
		this.geoCode = geoCode;
	}
	
	
	
}
