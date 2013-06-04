/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dishminer.resturantsservice;

import java.util.HashMap;

/**
 *
 * @author agupta6
 */
public class APIMetadataBO {
    
    
    private String apiName;
    private HashMap<String,String> apiUrl;
    private HashMap<String,String> keys;

    /**
     * @return the apiName
     */
    public String getApiName() {
        return apiName;
    }

    /**
     * @param apiName the apiName to set
     */
    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    /**
     * @return the apiUrl
     */
    public HashMap<String,String> getApiUrl() {
        return apiUrl;
    }

    /**
     * @param apiUrl the apiUrl to set
     */
    public void setApiUrl(HashMap<String,String> apiUrl) {
        this.apiUrl = apiUrl;
    }

    /**
     * @return the keys
     */
    public HashMap<String,String> getKeys() {
        return keys;
    }

    /**
     * @param keys the keys to set
     */
    public void setKeys(HashMap<String,String> keys) {
        this.keys = keys;
    }
    
    
   
    
    
}
