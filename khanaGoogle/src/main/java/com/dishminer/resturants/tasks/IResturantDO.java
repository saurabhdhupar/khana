/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dishminer.resturants.tasks;

import com.dishminer.resturantsservice.AddressDO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author agupta6
 */
public interface IResturantDO {
    
    public String getName();
    public List<AddressDO> getAddress();
    public String getPhoneNumber();
    public String getLongitude();
    public String getLattitude();
    public String getRestCusine();
    public String getPricing();
    public Map<String,String> getAPIURL();
    public String getResturantRating();
    public String getTimeStamp();
    public String getResturantId();
     public Map<String,String> getApiID();

  
            
            
   
    
}
