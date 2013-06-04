/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dishminer.resturants.tasksImpl;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author agupta6
 */
public class APICall {
    
    
    private String apiUrl;
    private String fmtType;
    
     @GET
 @Produces (MediaType.APPLICATION_JSON)
    public ClientResponse getResponse(){
   
        try {
             ClientResponse response=null;
            Client client = Client.create();
                    
                      WebResource resource = client.resource(getApiUrl());
                       resource.type(MediaType.APPLICATION_JSON_TYPE);
                        response = resource.get(ClientResponse.class);
                    //System.out.println(response.toString());
                   
            
            return response;
          
        } catch (Exception ex) {
           // Logger.getLogger(FacebookDO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    
    
    
    
    
    }

    /**
     * @return the apiUrl
     */
    public String getApiUrl() {
        return apiUrl;
    }

    /**
     * @param apiUrl the apiUrl to set
     */
    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    /**
     * @return the fmtType
     */
    public String getFmtType() {
        return fmtType;
    }

    /**
     * @param fmtType the fmtType to set
     */
    public void setFmtType(String fmtType) {
        this.fmtType = fmtType;
    }
    
    
    
}
