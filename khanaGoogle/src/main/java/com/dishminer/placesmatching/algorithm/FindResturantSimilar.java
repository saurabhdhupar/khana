/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dishminer.placesmatching.algorithm;

import com.dishminer.resturants.tasks.IAddress;
import com.dishminer.resturants.tasks.IResturantDO;
import com.dishminer.resturantsservice.RestaurantDO;

/**
 *
 * @author agupta6
 */
public class FindResturantSimilar {
    
    public static boolean isResturantSimilar(IResturantDO rest1 , IResturantDO rest2){
        
        
        double similarName = MatchStringUtils.nameSimilar(rest1.getName(), rest2.getName());
        boolean isAddressSimilar = false;
        boolean isZipcodeSimilar = false;
        for(IAddress add : rest1.getAddress())
            for(IAddress add2 : rest2.getAddress()){
            
             double similarStreetName = MatchStringUtils.streetSimilar(add.getAddressLine(),add2.getAddressLine());
             double similarCityName = MatchStringUtils.streetSimilar(add.getCity(), add2.getCity());
             double similarStateName = MatchStringUtils.streetSimilar(add.getState(), add2.getState());
             double similarCountryName = MatchStringUtils.streetSimilar(add.getCountry(), add2.getCountry());
             double similarZipCode = MatchStringUtils.zipcodeSimilar(add.getZipCode(), add2.getZipCode());
             if(similarZipCode > 50) isZipcodeSimilar = true;
             if( similarStreetName > 20 )
              isAddressSimilar= true;
             else
             if(similarCityName > 20  )
                    isAddressSimilar=  true;
             if( similarStateName >  20 )
                    isAddressSimilar=  true;
             else;
             if(similarCountryName > 20 )
                    isAddressSimilar=  true;
             
            
            }
      
        if(similarName > 50 && isZipcodeSimilar && isAddressSimilar)
            return true;
        
        
        
        return false;
        
        
        
    
    
    
    
    
    }
    
}
