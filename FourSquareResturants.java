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
	public static void main(String[] args) throws FoursquareApiException {
		// TODO Auto-generated method stub


		
		
		FourSquareCredentials credentials = FourSquareUtils.createFourSquareCredentials();
		 // First we need a initialize FoursquareApi. 
	    FoursquareApi foursquareApi = new FoursquareApi(credentials.getClientId(), credentials.getClient_Secret(), credentials.getRedirectUrl());
	    
	    
	    
	    Map<String,String> params = FourSquareUtils.createRequestParams("37.388454908915,-122.03045437738", "4bf58dd8d48988d10f941735", "4000" , "50");
	    
	   
	    
	    // After client has been initialized we can make queries.
	    Result<VenuesSearchResult> result = foursquareApi.venuesSearch(params);
	  
	    if (result.getMeta().getCode() == 200) {
	      // if query was ok we can finally we do something with the data
	      for (CompactVenue venue : result.getResult().getVenues()) {
	        // TODO: Do something we the data
	        System.out.println(venue.getName());
	        //venue.getTips());
	        
	      }
	    } else {
	      // TODO: Proper error handling
	      System.out.println("Error occured: ");
	      System.out.println("  code: " + result.getMeta().getCode());
	      System.out.println("  type: " + result.getMeta().getErrorType());
	      System.out.println("  detail: " + result.getMeta().getErrorDetail()); 
	    }
		
		
		
		
	}

}
