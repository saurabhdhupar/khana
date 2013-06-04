/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dishminer.resturants.tasksImpl;

import com.dishmer.taksrequest.BaseRequest;
import com.dishminer.googleresponse.GooglePlaceDetailDO;
import com.dishminer.googleresponse.GoogleResponseDO;
import com.dishminer.googleresponse.Results;
import com.dishminer.resturants.tasks.IAddress;
import com.dishminer.resturants.tasks.IResturantDO;
import com.dishminer.resturants.tasks.ITask;
import com.dishminer.resturantsUtils.APIIDEnum;
import com.dishminer.resturantsservice.APIMetadataBO;
import com.dishminer.resturantsservice.AddressDO;
import com.dishminer.resturantsservice.RestaurantDO;
import com.dishminer.resturantsservice.ResturantsApiUrls;
import com.domain.project.Address_components;
import com.sun.jersey.api.client.ClientResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;






/**
 *
 * @author agupta6
 */
public class GoogleDO implements ITask{

    /**
     * @param args the command line arguments
     */
    private static int COUNTRY =0;
    private static int STATE =1;
    private static int CITY =2;
   private static int STREET =3;
   private HashMap<String, String> googleURL;
   private HashMap<String, String> googleKey;
   private APIMetadataBO Bo;
   
    private List<IResturantDO> resutrantListDO = new ArrayList<IResturantDO>();
    
    @Override
    public List<IResturantDO> execute(APIMetadataBO Bo , BaseRequest request) {
        try {
            
            APICall googleAPICall = new APICall();
            googleURL = Bo.getApiUrl();
            googleKey = Bo.getKeys();
            this.Bo = Bo;
            
           ArrayList<String> placesIds = new ArrayList<String>(); 
           String googleTextBasedURL = getGoogleTextBasedSearch(request.getLongitude()+","+ request.getLatitude());
           // System.out.print(googleTextSearchURL);
            googleAPICall.setApiUrl(googleTextBasedURL);
            ClientResponse response=googleAPICall.getResponse();
           // System.out.println(response.toString());
                    if (response.getClientResponseStatus().getFamily() == Response.Status.Family.SUCCESSFUL) {
                        //Response.status (Status.OK).entity (user).build ();
                        GoogleResponseDO entity = response.getEntity(GoogleResponseDO.class);
                        if(entity != null && entity.getResults() != null)
                        for(Results result : entity.getResults()) {
                            placesIds.add(result.getReference());
                        }
                        
                        
                      String  nextGoogleTextBasedURL = googleTextBasedURL+ ResturantsApiUrls.GoogleNextToken + entity.getNext_page_token();
                          
                        while(entity.getNext_page_token() != null ){
                               googleAPICall.setApiUrl(nextGoogleTextBasedURL);
                              Thread.sleep(5000);
                              response=googleAPICall.getResponse();
                              if (response.getClientResponseStatus().getFamily() == Response.Status.Family.SUCCESSFUL) {
                                  entity =  response.getEntity(GoogleResponseDO.class);
                                  if(entity != null && entity.getResults() != null)
                                    for(Results result : entity.getResults()) {
                                        if(result != null)
                                    placesIds.add(result.getReference());
                                    }
                                    nextGoogleTextBasedURL =googleTextBasedURL+ ResturantsApiUrls.GoogleNextToken + entity.getNext_page_token();
                          
                              }
                              
                              
                            
                        }
                int i=  0;   
                for(String placeid : placesIds) {       
               // System.out.println( "placeid " +i+" )  :- "  + placeid + "\n");
                
                              googleAPICall.setApiUrl(getGooglePlacesDetailSearch(placeid));
                              //System.out.println(getGooglePlacesDetailSearch(placeid)  +"\n");
                              response=googleAPICall.getResponse();
                              if (response.getClientResponseStatus().getFamily() == Response.Status.Family.SUCCESSFUL) {
                                 GooglePlaceDetailDO placeentity =  response.getEntity(GooglePlaceDetailDO.class);
                                 if(placeentity != null && placeentity.getResult() != null) {
                                      resutrantListDO.add(getResturantObj(placeentity));
                                  }
              
                              }
                i++;
                }
                
                
                
                
                
                    } else {
                        System.out.println("ERROR! " + response.getStatus());    
                        System.out.println(response.getEntity(String.class));
                    }
          
        } catch (Exception ex) {
            Logger.getLogger(FacebookDO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resutrantListDO;
    }
    
    
    
    private AddressDO getAddressFromResponse(List<Address_components> address){
    AddressDO addressDO = new AddressDO();
    // addressDO.setCountry(address.getTypes().equals(""));
    for(Address_components addr :  address)
    //addressDO.set
    {
        if(addr.getTypes() != null && !addr.getTypes().isEmpty()){
        if(addr.getTypes().get(0).equalsIgnoreCase("country"))
            addressDO.setCountry(addr.getLong_name());
        if(addr.getTypes().get(0).equalsIgnoreCase("postal_code"))
            addressDO.setZipCode(addr.getLong_name());
        if(addr.getTypes().get(0).equalsIgnoreCase("administrative_area_level_1"))
            addressDO.setState(addr.getLong_name());
         if(addr.getTypes().get(0).equalsIgnoreCase("administrative_area_level_2"))
            addressDO.setCity(addr.getLong_name());
         if(addr.getTypes().get(0).equalsIgnoreCase("route"))
            addressDO.setAddressLine(addr.getLong_name());
         
        }
        
    }
    
    
    
        return addressDO;
    
    
    
    
    }
    
    
    
    private String getGoogleTextBasedSearch(String location ){
    
       String googleTextSearchURL;
        try {
            googleTextSearchURL = googleURL.get("GoogleTextSearch")+ResturantsApiUrls.GoogleLocation+location
                               +ResturantsApiUrls.GoogleRadius+"1000"+ResturantsApiUrls.GoogleQuery+googleURL.get("GoogleKeywords")+ResturantsApiUrls.GoogleType
                               +URLEncoder.encode(googleURL.get("GoogleType") ,"UTF-8")+
                               ResturantsApiUrls.GoogleSensor+"false"+ResturantsApiUrls.GoogleKey+
                               URLEncoder.encode(googleKey.get("key") ,"UTF-8");
             return googleTextSearchURL;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(GoogleDO.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    return null;
    }
    
    
    private String getGoogleURLBasedSearch(String location){
    
     String googleRadiusSearchURL;
        try {
            googleRadiusSearchURL = googleURL.get("GoogleRadiusSearch")+ResturantsApiUrls.GoogleLocation+location
                                 +ResturantsApiUrls.GoogleRadius+"5000"+ResturantsApiUrls.GoogleType
                                 +URLEncoder.encode(googleURL.get("GoogleType") ,"UTF-8")+
                                 ResturantsApiUrls.GoogleSensor+"false"+ResturantsApiUrls.GoogleKeyword+googleURL.get("GoogleKeywords")+ResturantsApiUrls.GoogleKey+
                                 URLEncoder.encode(googleKey.get("key") ,"UTF-8");
            return googleRadiusSearchURL;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(GoogleDO.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    return null;
    
    
    }
    
    
    
    
    private String getGooglePlacesDetailSearch(String reference ){
    
       String googlePlacesDetailSearchURL;
        try {
            googlePlacesDetailSearchURL = googleURL.get("GooglePlacesDetailsSearch")+ResturantsApiUrls.GoogleReference+reference
                               +ResturantsApiUrls.GoogleSensor+"false"+ResturantsApiUrls.GoogleKey+
                               URLEncoder.encode(googleKey.get("key") ,"UTF-8");
             return googlePlacesDetailSearchURL;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(GoogleDO.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    return null;
    }
    
    
    private RestaurantDO getResturantObj(GooglePlaceDetailDO entity){
        
        if(entity != null && entity.getResult() != null){
        
        RestaurantDO rest= new RestaurantDO();
        HashMap<String,String> apiURL = new HashMap<String, String>();
        List<Address_components> addcomp = entity.getResult().getAddress_components();
        List<AddressDO> addressListDO = new ArrayList<AddressDO>();
        addressListDO.add(getAddressFromResponse(addcomp));
        rest.setAddress(addressListDO);
        apiURL.put(APIIDEnum.GOOGLE.getName(), entity.getResult().getUrl());
        apiURL.put(APIIDEnum.WEBSITE.getName(), entity.getResult().getWebsite());
        rest.setAPIURL(apiURL);
        rest.setLattitude(entity.getResult().getGeometry().getLocation().getLat());
        
        rest.setLongitude(entity.getResult().getGeometry().getLocation().getLng());
        rest.setName(entity.getResult().getName());
        rest.setPhoneNumber(entity.getResult().getInternational_phone_number());
        rest.setResturantRating(entity.getResult().getRating());
        HashMap<String,String> apiId = new HashMap<String, String>();
        apiId.put(APIIDEnum.GOOGLE.getName(), entity.getResult().getReference());
        rest.setApiID(apiId);
        
        rest.setPricing(entity.getResult().getPrice_level());
       // entity.getResult().getTypes().get(0).
       // rest.setResturantId(entity.getResult());
        return rest;
        }
        
        
        return null;
    
    
    }
                
	
   
}
