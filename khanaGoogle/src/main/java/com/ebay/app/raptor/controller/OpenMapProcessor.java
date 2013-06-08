package com.ebay.app.raptor.controller;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status.Family;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class OpenMapProcessor {
	
	private String client_endpoint = "http://nominatim.openstreetmap.org/search";
	private DecimalFormat df = new DecimalFormat("#.######");

	public OpenMapResponse process(OpenMapRequest request) {
		OpenMapResponse openMapresponse = new OpenMapResponse();
		RegularExpression expression = new RegularExpression();
		Client client = Client.create();
		String client_url = client_endpoint+request.toString();
		WebResource resource = client.resource(client_url);

		resource.type(MediaType.APPLICATION_XML);

		ClientResponse response = resource.get(ClientResponse.class);

		if (response.getClientResponseStatus().getFamily() == Family.SUCCESSFUL) {
		    String openstreetMapOutput = response.getEntity(String.class);
		    String polygonPoints = expression.getPolygonPoints(openstreetMapOutput);
		    openMapresponse.setGeoCode(getGeoCodePoints(polygonPoints));		    
		} else {
		    System.out.println("ERROR! " + response.getStatus());    
		    System.out.println(response.getEntity(String.class));
		}
		return openMapresponse;
	}
	
	private Set<GeoCodeDO> getGeoCodePoints(String polygonPoints) {
		int index = 0;
		Set<GeoCodeDO> geoCodeObjs = new HashSet<GeoCodeDO>();
		while(index < polygonPoints.length() && index != -1) {
			int startIndex = polygonPoints.indexOf("\"polygonpoints\":[", index);
			if(startIndex == -1) {
				break;
			}
			int endIndex = polygonPoints.indexOf("],\"lat\"", startIndex);
			if(startIndex > -1 && endIndex > -1) {
				String points = polygonPoints.substring(startIndex, endIndex);
				geoCodeObjs.addAll(getGeoPoint(points));
			}
			index = endIndex;
			
		}
		System.out.println(geoCodeObjs.size());
		return geoCodeObjs;
	}
	
	private Set<GeoCodeDO> getGeoPoint(String points) {
		int index = 0;
		Set<GeoCodeDO> geoCodeObjs = new HashSet<GeoCodeDO>();
		while(index < points.length() && index != -1) {
			int startIndex = points.indexOf("[", index);
			if(startIndex == -1) {
				break;
			}
			int endIndex = points.indexOf("]", startIndex);
			if(startIndex > -1 && endIndex > -1) {
				String latlong = points.substring(startIndex, endIndex);
				latlong = latlong.replace("[", "").replace("\"", "").trim();
				String loc[] = latlong.split(",");
				double latitude = Double.valueOf(df.format(Double.valueOf(loc[0].trim())));
			    double longitude = Double.valueOf(df.format(Double.valueOf(loc[1].trim())));
			    GeoCodeDO geoCodeDO = new GeoCodeDO(latitude,longitude);
			    geoCodeObjs.add(geoCodeDO);
			}
			index = endIndex;
			
		}
		return geoCodeObjs;
	}
 	
	

}
