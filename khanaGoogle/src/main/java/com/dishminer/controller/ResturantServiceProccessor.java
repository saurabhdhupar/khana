/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dishminer.controller;

import AmazonS3.S3Processor;
import com.dishmer.taksrequest.BaseRequestImpl;
import com.dishminer.placesmatching.algorithm.FindResturantSimilar;
import com.dishminer.resturants.tasks.IAddress;
import com.dishminer.resturants.tasks.IResturantDO;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.dishminer.resturants.tasks.ITask;
import com.dishminer.resturantsUtils.APIIDEnum;
import com.dishminer.resturantsUtils.resturantsUtils;
import com.dishminer.resturantsservice.APIMetadataBO;
import com.dishminer.resturantsservice.RestaurantDO;
import com.ebay.app.raptor.controller.FourSquareCredentials;
import com.ebay.app.raptor.controller.FourSquareUtils;
import com.ebay.app.raptor.controller.GeoCodeDO;
import com.ebay.app.raptor.controller.OpenMapProcessor;
import com.ebay.app.raptor.controller.OpenMapRequest;
import com.ebay.app.raptor.controller.OpenMapResponse;
import com.ebay.app.raptor.controller.OpenStreetMapRequestHelper;

import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author agupta6
 */
public class ResturantServiceProccessor {
    
    
 
    public static ResturantController controler;
    
    private static final String packageName = "com.dishminer.resturants.tasksImpl.";
    private static final String classSuffix ="DO";
    private static List<IResturantDO> resturantList = new ArrayList<IResturantDO>();
    private static S3Processor processor = new S3Processor();
    private static int numberofCalls=0;
        // Three contexts following different strategies
    
    public static void main(String args[]){
            ArrayList<APIMetadataBO> apiMetaDataBOList = resturantsUtils.getApis();
            ArrayList<String> apiNames = new ArrayList<String>();
            HashMap<String,APIMetadataBO> apiMetaDataMap = new HashMap<String , APIMetadataBO>();
             HashMap<String,Integer> numberofSameAPiCalls = new HashMap<String, Integer>();
               
            for(APIMetadataBO api : apiMetaDataBOList){
            
                String apiname = api.getApiName();
                apiMetaDataMap.put(apiname, api);
                apiNames.add(apiname);
                numberofSameAPiCalls.put(apiname, 0);
                processor.setNumberofSameAPiCalls(numberofSameAPiCalls);
            
            }
            //S3Processor.readJSon();
             System.out.println("Current City :- " + "sunnyvale,ca");
               
            String [] zipcodes = processor.getZipcodes("zipcodesvc.dishminer.us", "sunnyvale,ca");
             System.out.println("Number of Zipcodes :- " + zipcodes.length);
           
            for(String zipcode : zipcodes){
                System.out.println("Current ZipCode  :- " + zipcode);
                processor.setZipcode(zipcode);
               zipcode= zipcode.replaceAll("\"","");
                if(zipcode.isEmpty()) break;
                Set<GeoCodeDO> codes = getCodes(zipcode,"Sunnyvale", "CA", "us");
                 System.out.println("Number of GeoCodes for ZipCode:- "+zipcode+" are  :- " + codes.size());
              
                 Iterator<GeoCodeDO> itr_gecode = codes.iterator();
                 int i=0;
                 int numberOfSameCalls=0;
                 boolean skipRecords=false;
                 int numberOfRecordsToSkip=0;
                 while(itr_gecode.hasNext() ) {
                     if(skipRecords && numberOfRecordsToSkip < 20 ) {
                         
                         numberOfRecordsToSkip++;
                         continue;
                     }
                     
                    //System.out.print( codes.size() );
                    numberOfRecordsToSkip=0;
                    skipRecords = false;
	    	GeoCodeDO code = itr_gecode.next();
                 System.out.println("Current GeoCode  :- " + code.toString());
              
                 setDatainS3(apiNames, apiMetaDataMap, String.valueOf(code.getLongitude()), String.valueOf(code.getLatitude()));
                 i++;
                    try {
                         if(processor.getNumberOfSameEntries() > 6) {
                             System.out.println("processor.getNumberOfSameEntries()" + processor.getNumberOfSameEntries()  );
              
                               processor.setNumberOfSameEntries(0);
                              skipRecords = true;
                              numberOfSameCalls++;
                              if(numberOfSameCalls > 10) {
                                   System.out.println("Breaking Loop For ZipCode "  + zipcode);
                                     System.out.println("Breaking Loop For GeoCode "  + code.toString());
              
                                 break;
                             }
                         }
               
                         System.out.println("Sleeping main thread for 60000 " );
              
                        Thread.sleep(60000);
                        if(numberofCalls > 5000) {
                              System.out.println("Number of FourSquare calls greater than 5000/hr " );
              
                            Thread.sleep(600000);
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ResturantServiceProccessor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 }
             
                 //break;
  
            
            }
        
  
      
    
    }

    private static boolean checkForAddress(IResturantDO restDO, IResturantDO mainrestDO) {
        if(restDO.getAddress() == null  || mainrestDO.getAddress() == null 
                || restDO.getAddress().isEmpty() || mainrestDO.getAddress().isEmpty()) return false;
        for(IAddress add : restDO.getAddress()){
            if(add == null)return false;
            for(IAddress otherAdd : mainrestDO.getAddress()){
                if(otherAdd == null) return false;
                if(add.getCountry() == null  || otherAdd.getCountry() == null )return false;
                if(add.getZipCode() == null ||  null == otherAdd.getZipCode()) return false;
               if(add.getCountry().equals(otherAdd.getCountry()) && add.getZipCode().equals(otherAdd.getZipCode()))
               return true;
                }
       }
        
        
        return false;
        
        
    }

    private static void setMapInMainObj(IResturantDO restDO, IResturantDO mainrestDO) {
        Map<String,String> apiidMap =  restDO.getApiID();
        Map<String,String> apiURLMap =  restDO.getApiID();
        mainrestDO.getApiID().putAll(apiidMap);
        mainrestDO.getAPIURL().putAll(apiURLMap);
        
        
        
    }

    private static List<String> getResturantList(List<IResturantDO> resturantList) {
        List<String> resturantName = new ArrayList<String>();
        
        for(IResturantDO restDo :  resturantList){
            if(restDo == null) continue;
        resturantName.add(restDo.getName());
        }
        return resturantName;
        
            }

    private static void mergeFaceBookListToOrigList(List<IResturantDO> restList) {
        //restList.remove(null);
        for(int i =0 ; i< resturantList.size() && i < restList.size() ; i++){
          if(resturantList.get(i) != null && restList.get(i) != null ) {
              //System.out.print(restList);
            
               if(resturantList.get(i).getApiID() != null &&  restList.get(i).getApiID() != null){
                Map<String,String> apiId =  resturantList.get(i).getApiID();
                  Map<String,String> facebookapiId = restList.get(i).getApiID();
              
                apiId.put(APIIDEnum.FACEBOOK.getName(), facebookapiId.get(APIIDEnum.FACEBOOK.getName()));
                resturantList.get(i).getApiID().putAll(apiId);
                }
                  }
        
        }
        
     }
    
    private static void mergeResturantObjects(IResturantDO origResturant , IResturantDO newResturant ){
    
   
                Map<String,String> apiId =  origResturant.getApiID();
                if(apiId == null)
                    apiId = new HashMap<String, String>();
                
                  Map<String,String> newResturantapiId = newResturant.getApiID();
                apiId.putAll(newResturantapiId);
                
                      Map<String,String> apiURL =  origResturant.getApiID();
                if(apiURL == null)
                    apiURL = new HashMap<String, String>();
                
                  Map<String,String> newResturantapiURL = newResturant.getApiID();
                apiURL.putAll(newResturantapiURL);
                
    
    
    
    }

    private static void setDatainS3(ArrayList<String> apiNames, HashMap<String, APIMetadataBO> apiMetaDataMap , String longitude , String lattitude) {
        BaseRequestImpl request = new BaseRequestImpl();
            request.setLatitude(lattitude);
            request.setLongitude(longitude);
            request.setResturantList(getResturantList(resturantList));
                  
        
      
        try {
            for(String apiname : apiNames){
                if(apiname.equalsIgnoreCase("Facebook")) {
                    continue;
                }
            System.out.println("Calling Api : " + apiname );
              
            if(processor.getNumberofSameAPiCalls() != null && processor.getNumberofSameAPiCalls().get(apiname) > 3) continue;
            controler = new ResturantController((ITask)Class.forName(packageName+apiname+classSuffix).newInstance(),apiMetaDataMap.get(apiname) , request);
           List<IResturantDO> restList =  controler.executeStrategy();
           processor.setApiname(apiname);
            processor.setGoogleRestList(restList);
            if(apiname.equalsIgnoreCase("FourSquare")) {
                    numberofCalls = restList.size();
                }
            writeInS3();
           Set<IResturantDO> templist= new HashSet<IResturantDO>();
               Set<IResturantDO> tempSet= new HashSet<IResturantDO>();
                    Set<IResturantDO> templist1= new HashSet<IResturantDO>();
                    List<IResturantDO> templist2= new ArrayList<IResturantDO>();
           if(resturantList.isEmpty()){resturantList.addAll(restList);
           }
           else {
                   
                  
                    for(IResturantDO restaurant :  resturantList){
                          boolean check = false;
                        for(IResturantDO restaurantDO :  restList){
                            if( FindResturantSimilar.isResturantSimilar(restaurant, restaurantDO)) {
                                mergeResturantObjects(restaurant, restaurantDO);
                                templist.add(restaurant);
                                tempSet.add(restaurantDO);
                                check = true;
                            }
                            else{
                                //resturantList.add(restaurantDO);
                                if(!tempSet.contains(restaurantDO))
                                templist1.add(restaurantDO);
                                
                                
                            }
                        
                        }
                        if(!check) {
                             if(!templist.contains(restaurant))
                            templist2.add(restaurant);
                        }
                    
                    
                    }
            resturantList.clear();
            resturantList.addAll(templist);
            Iterator iter = templist1.iterator();
                while (iter.hasNext()) {
                    IResturantDO re =(IResturantDO)iter.next(); 
                  if(!tempSet.contains(re))
                      resturantList.add(re);
                }
                
            //resturantList.addAll(templist1);
             Iterator iter1 = templist2.iterator();
                while (iter1.hasNext()) {
                    IResturantDO re =(IResturantDO)iter1.next(); 
                  if(!templist.contains(re))
                     resturantList.add(re);
                }
           // resturantList.addAll(templist2);
                }
           
           
            }
            
           request.setResturantList(getResturantList(resturantList));
                  
             
           controler = new ResturantController((ITask)Class.forName(packageName+"Facebook"+classSuffix).newInstance(),apiMetaDataMap.get("Facebook") , request);
           System.out.println("Calling Api : " + "Facebook" );
          
           List<IResturantDO> restList =  controler.executeStrategy();
          // mergeFaceBookListToOrigList(restList);
            
            if(restList !=null && !restList.isEmpty()){
           processor.setApiname("Facebook");
           processor.setGoogleRestList(restList);
                try {
                    processor.s3Writer();
                    
                    
                } catch (IOException ex) {
                    Logger.getLogger(ResturantServiceProccessor.class.getName()).log(Level.SEVERE, null, ex);
                }
           
            }
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ResturantServiceProccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
            catch (InstantiationException ex) {
            Logger.getLogger(ResturantServiceProccessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ResturantServiceProccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
    public static Set<GeoCodeDO> getCodes(String zipCode, String location, String state, String country) {
		OpenStreetMapRequestHelper requestHelper = new OpenStreetMapRequestHelper();
		OpenMapProcessor openStreetMapProcessor = new OpenMapProcessor();
		OpenMapRequest request = requestHelper.createRequest(zipCode, location, state, country);
		OpenMapResponse response = openStreetMapProcessor.process(request);
                Iterator<GeoCodeDO> itr_gecode = response.getGeoCode().iterator();
	    return response.getGeoCode(); 
	}
    
    
    
    
    private static void writeInS3(){
    
    
       try {
                    processor.s3Writer();
                    
                    
                } catch (IOException ex) {
                    Logger.getLogger(ResturantServiceProccessor.class.getName()).log(Level.SEVERE, null, ex);
                }
           
      }
            
    
    
    
	
	
    
}
