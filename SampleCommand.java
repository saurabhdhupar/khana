package com.ebay.app.raptor.controller; 

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;

public class SampleCommand {
	

	public void run(String zipCode, String location, String state, String country) {
		OpenStreetMapRequestHelper requestHelper = new OpenStreetMapRequestHelper();
		OpenMapProcessor openStreetMapProcessor = new OpenMapProcessor();
		OpenMapRequest request = requestHelper.createRequest(zipCode, location, state, country);
		OpenMapResponse response = openStreetMapProcessor.process(request);
		FourSquareCredentials credentials = FourSquareUtils.createFourSquareCredentials();
		 // First we need a initialize FoursquareApi. 
	    FoursquareApi foursquareApi = new FoursquareApi(credentials.getClientId(), credentials.getClient_Secret(), credentials.getRedirectUrl());
	    Set<RestaurantDO> restaurantSet = new HashSet<RestaurantDO>();
	    
	    Iterator<GeoCodeDO> itr_gecode = response.getGeoCode().iterator();
	    int i = 0;
	    while(itr_gecode.hasNext() && i <= 10) {
	    	GeoCodeDO code = itr_gecode.next();
	    	Map<String, String> params = FourSquareUtils.createRequestParams(
					String.valueOf(code.getLongitude())+","+String.valueOf(code.getLatitude()),
					"4bf58dd8d48988d10f941735", "4000", "50");
			// After client has been initialized we can make queries.
			Result<VenuesSearchResult> result;
			try {
				result = foursquareApi
						.venuesSearch(params);
			
				if (result.getMeta().getCode() == 200) {
					// if query was ok we can finally we do something with the data
					for (CompactVenue venue : result.getResult().getVenues()) {
						// TODO: Do something we the data
						RestaurantDO restaurantdo = new RestaurantDO();
						restaurantdo.setAddress(venue.getLocation().getAddress());
						restaurantdo.setPhoneNumber(venue.getContact().getPhone());
						restaurantdo.setName(venue.getName());
						restaurantSet.add(restaurantdo);
						System.out.println(venue.getName());						
					}
				} else {
					// TODO: Proper error handling
					System.out.println("Error occured: ");
					System.out.println("  code: " + result.getMeta().getCode());
					System.out
							.println("  type: " + result.getMeta().getErrorType());
					System.out.println("  detail: "
							+ result.getMeta().getErrorDetail());
				}
			} catch (FoursquareApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			i++;
	    }
	    FileWriter file = null;
		try {
			file = new FileWriter("rest.txt");
		
	    BufferedWriter bf = new BufferedWriter(file);
	    Iterator<RestaurantDO> itr_rest = restaurantSet.iterator();
	    int j = 0;
	    while(itr_rest.hasNext()) {
	    	bf.write(itr_rest.next().getName()+"\n");
	    	j++;
	    }
	    bf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	    
	}
	
	public static void main(String args[]) {
		SampleCommand cmd = new SampleCommand();
		cmd.run("94087", "Sunnyvale", "CA", "us");
	}

}
