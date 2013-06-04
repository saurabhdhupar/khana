package com.dishminer.FourSqaure;

import java.util.HashMap;
import java.util.Map;

import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;


public class FourSquareResturants {

	/**
	 * @param args
	 * @throws FoursquareApiException
        */
        private FourSquareCredentials credentials;
    
            
    
    
	public Result<VenuesSearchResult> getFourSquareResturants( Map<String,String> params  ) throws FoursquareApiException {
	
            FoursquareApi foursquareApi = new FoursquareApi(credentials.getClientId(), credentials.getClient_Secret(), credentials.getRedirectUrl());
	    Result<VenuesSearchResult> result = foursquareApi.venuesSearch(params);
	  
	    if (result.getMeta().getCode() == 200) {
	      // if query was ok we can finally we do something with the data
	      for (CompactVenue venue : result.getResult().getVenues()) {
	        // TODO: Do something we the data
	        System.out.println(venue.getName());
	        //venue.getTips());
	        
	      }
              return result;
	    } else {
	      // TODO: Proper error handling
	      System.out.println("Error occured: ");
	      System.out.println("  code: " + result.getMeta().getCode());
	      System.out.println("  type: " + result.getMeta().getErrorType());
	      System.out.println("  detail: " + result.getMeta().getErrorDetail()); 
	    
        return null;
		
		
		
		
	}

        }
        
    public FourSquareCredentials getCredentials(){
        return credentials;
    }

    /**
     * @param credentials the credentials to set
     */
    public void setCredentials(FourSquareCredentials credentials) {
        this.credentials = credentials;
    }

    /**
     * @return the credentials
     */
  


}
