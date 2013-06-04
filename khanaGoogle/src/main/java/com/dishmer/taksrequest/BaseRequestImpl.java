/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dishmer.taksrequest;

import com.dishminer.resturantsservice.ResturantsApiUrls;
import java.net.URLEncoder;
import java.util.List;

/**
 *
 * @author agupta6
 */
public class BaseRequestImpl implements BaseRequest{
    
  private String longitude;
  private String latitude;
  private List<String> resturantList;

    public List<String> getResturantList() {
        return resturantList;
    }

    public void setResturantList(List<String> resturantList) {
        this.resturantList = resturantList;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
  
    
}
