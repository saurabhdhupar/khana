/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dishminer.resturants.tasksImpl;

import com.dishmer.taksrequest.BaseRequest;
import com.dishmer.taksrequest.BaseRequestImpl;
import com.dishminer.facebook.Facebook;
import com.dishminer.resturants.tasks.IAddress;
import com.dishminer.resturants.tasks.IResturantDO;
import com.dishminer.resturants.tasks.ITask;
import com.dishminer.resturantsUtils.APIIDEnum;
import com.dishminer.resturantsservice.APIMetadataBO;
import com.dishminer.resturantsservice.AddressDO;
import com.dishminer.resturantsservice.RestaurantDO;
import com.dishminer.resturantsservice.ResturantsApiUrls;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author agupta6
 */
public class FacebookDO implements ITask{
    
    
   private static List<IResturantDO> resturantDOList = new ArrayList<IResturantDO>();
   private BaseRequestImpl request; 
    
  private   HashMap<String, String> facebookUrl;
    private  HashMap<String, String> facebookkey;
    private String longitude;
    private String lattitude;
            
   
    @Override
    public List<IResturantDO> execute(APIMetadataBO Bo , BaseRequest request) {
        try {
            
            APICall facebookAPICall = new APICall();
            facebookUrl = Bo.getApiUrl();
           facebookkey = Bo.getKeys();
           longitude = request.getLongitude();
           lattitude = request.getLatitude();
           for(String resturantName :  request.getResturantList()){
               if(resturantName == null) {
                   continue;
               }
            String facebookURL= getFaceBookSearchURL(resturantName);
            facebookAPICall.setApiUrl(facebookURL);
           // System.out.print(facebookURL);
            ClientResponse response=facebookAPICall.getResponse();
            //System.out.print(response.getEntity(String.class));
            Facebook entity;
            
                    if (response.getClientResponseStatus().getFamily() == Response.Status.Family.SUCCESSFUL) {
                         entity = response.getEntity(Facebook.class);
                         RestaurantDO restDO = getResturantDO(entity);
                         resturantDOList.add(restDO);
                    } else {
                        System.out.println("ERROR! " + response.getStatus());    
                        System.out.println(response.getEntity(String.class));
                    }
            }
        } catch (Exception ex) {
            Logger.getLogger(FacebookDO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resturantDOList;
    }

    private RestaurantDO getResturantDO(Facebook entity) {
        
        RestaurantDO restDO = new RestaurantDO();
        if(entity != null){
           if( entity.getData() != null )
           {
              if( entity.getData().get(0)!= null )  {
                  restDO.setName(entity.getData().get(0).getName());
                    AddressDO add = new AddressDO();
                add.setAddressLine(entity.getData().get(0).getLocation().getStreet());
                add.setCity(entity.getData().get(0).getLocation().getCity());
                add.setCountry(entity.getData().get(0).getLocation().getCountry());
                add.setState(entity.getData().get(0).getLocation().getState());
                add.setZipCode(entity.getData().get(0).getLocation().getZip());
                List<AddressDO>addressList = new ArrayList<AddressDO>();
                addressList.add(add);
                  restDO.setAddress(addressList);
                    Map<String,String> apiID= new HashMap<String, String>();
                apiID.put(APIIDEnum.FACEBOOK.getName(), entity.getData().get(0).getId());
                restDO.setApiID(apiID);
                return restDO;
          }
               
           }
        
        
        }
        
        return null;
        
    }
	
    
    private String getFaceBookSearchURL(String resturant){
        
        String facebookURL="";
        try {
             facebookURL= facebookUrl.get("FacebookSearchUrl")+ResturantsApiUrls.FacebookQuery+URLEncoder.encode(resturant ,"UTF-8")+
                                        ResturantsApiUrls.FacebookType+facebookUrl.get("FacebookType")+
                                        ResturantsApiUrls.FacebookCenter+longitude+","+lattitude+
                                        ResturantsApiUrls.FacebookDistance+"10000"+ResturantsApiUrls.FacebookAccessToken+
                    URLEncoder.encode(facebookkey.get("accessToken") ,"UTF-8");
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FacebookDO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return facebookURL;
    }
   
    
}
