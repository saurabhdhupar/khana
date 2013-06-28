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
public class FourSquareMenuDO implements ITask{

    /**
     * @param args the command line arguments
     */
    private static int COUNTRY =0;
    private static int STATE =1;
    private static int CITY =2;
   private static int STREET =3;
   private HashMap<String, String> foursquareURL;
   private HashMap<String, String> foursquareKey;
   private APIMetadataBO Bo;
   
    private List<IResturantDO> resutrantListDO = new ArrayList<IResturantDO>();
    
    @Override
    public List<IResturantDO> execute(APIMetadataBO Bo , BaseRequest request) {
        try {
            
            APICall googleAPICall = new APICall();
            foursquareURL = Bo.getApiUrl();
            foursquareKey = Bo.getKeys();
            this.Bo = Bo;
            
            String grtFourSquareMenu = foursquareURL.get("FourSquareMenuUrl") + "venueID"+ ResturantsApiUrls.FourSquareClientID+ foursquareKey.get("clientId")
                                  + ResturantsApiUrls.FourSquareClientSecret +foursquareKey.get("client_secret");
                    foursquareKey.get("client_secret");
           
           ClientResponse response=googleAPICall.getResponse();
           // System.out.println(response.toString());
                    if (response.getClientResponseStatus().getFamily() == Response.Status.Family.SUCCESSFUL) {
                        //Response.status (Status.OK).entity (user).build ();
                        GoogleResponseDO entity = response.getEntity(GoogleResponseDO.class);
                       // entity.getResults().get(0).
                        if(entity != null && entity.getResults() != null)
                        for(Results result : entity.getResults()) {
                        }
                        
                           
                       
                            
                        }
                
          
        } catch (Exception ex) {
            Logger.getLogger(FacebookDO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resutrantListDO;
    }
    
    
    
    

  
   
}
