/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dishminer.resturants.tasksImpl;

import com.amazonaws.services.ec2.model.Address;
import com.dishmer.taksrequest.BaseRequest;
import com.dishminer.FourSqaure.FourSquareCredentials;
import com.dishminer.FourSqaure.FourSquareParams;
import com.dishminer.FourSqaure.FourSquareResturants;
import com.dishminer.FourSqaure.FourSquareUtils;
import com.dishminer.resturants.tasks.IAddress;
import com.dishminer.resturants.tasks.IResturantDO;
import com.dishminer.resturants.tasks.ITask;
import com.dishminer.resturantsUtils.APIIDEnum;
import com.dishminer.resturantsservice.APIMetadataBO;
import com.dishminer.resturantsservice.AddressDO;
import com.dishminer.resturantsservice.RestaurantDO;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author agupta6
 */
public class FourSquareDO implements ITask{

   private HashMap<String, String> foursquareURL;
   private HashMap<String, String> foursquareKey;
   private APIMetadataBO Bo;
   FourSquareResturants fresturants = new FourSquareResturants();
   private List<IResturantDO> resturantslists = new ArrayList<IResturantDO>(); 
   
   
    @Override
    public List<IResturantDO> execute(APIMetadataBO Bo , BaseRequest request) {
       
        foursquareKey = Bo.getKeys();
        foursquareURL = Bo.getApiUrl();
        this.Bo = Bo;
        
        FourSquareCredentials credentials = new FourSquareCredentials();
        credentials.setClientId(foursquareKey.get(FourSquareParams.Client_Id));
        credentials.setClient_Secret(foursquareKey.get(FourSquareParams.Client_Secret));
        credentials.setRedirectUrl(foursquareKey.get(FourSquareParams.Redirect_Url));
        
        Map<String,String> params = FourSquareUtils.createRequestParams(request.getLongitude() +","+request.getLatitude(), foursquareURL.get(FourSquareParams.CategoryId),
                foursquareURL.get(FourSquareParams.Radius )  , foursquareURL.get(FourSquareParams.Limit));
	    
        
        fresturants.setCredentials(credentials);
        try {
            Result<VenuesSearchResult> fourSquareResturants = fresturants.getFourSquareResturants(params);
            
            for (CompactVenue venue : fourSquareResturants.getResult().getVenues()) {
	        // TODO: Do something we the data
                RestaurantDO restDO = new RestaurantDO();
                AddressDO add = new AddressDO();
                add.setAddressLine(venue.getLocation().getAddress());
                add.setCity(venue.getLocation().getCity());
                add.setCountry(venue.getLocation().getCountry());
                add.setState(venue.getLocation().getState());
                add.setZipCode(venue.getLocation().getPostalCode());
                List<AddressDO>addressList = new ArrayList<AddressDO>();
                addressList.add(add);
                restDO.setAddress(addressList);
                restDO.setLongitude(String.valueOf(venue.getLocation().getLng()));
                restDO.setLattitude(String.valueOf(venue.getLocation().getLat()));
                restDO.setName(venue.getName());
                restDO.setPhoneNumber(venue.getContact().getPhone());
                Map<String,String> apiID= new HashMap<String, String>();
                apiID.put(APIIDEnum.FACEBOOK.getName(), venue.getContact().getFacebook());
                apiID.put(APIIDEnum.TWITTER.getName(), venue.getContact().getTwitter());
                apiID.put(APIIDEnum.FOURSQUARE.getName(), venue.getId());
                restDO.setApiID(apiID);
               resturantslists.add(restDO);
                
                System.out.println(venue.getName());
	        //venue.getTips());
	        
	      }
        } catch (FoursquareApiException ex) {
            Logger.getLogger(FourSquareDO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resturantslists;
        
    }
    
}
