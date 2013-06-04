/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dishmer.taksrequest;

import java.util.List;

/**
 *
 * @author agupta6
 */
public interface BaseRequest {
    
    
   public String getLongitude();
   public String getLatitude();
   public List<String> getResturantList();
    
    
}
