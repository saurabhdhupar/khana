/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dishminer.placesmatching.algorithm;

import com.dishminer.resturantsservice.RestaurantDO;
import com.google.api.client.util.StringUtils;

/**
 *
 * @author agupta6
 */
public class MatchStringUtils {
    
    
    public static double nameSimilar(String rest1, String rest2){
    if((rest1 == null || rest1.isEmpty()) || (rest2 == null || rest2.isEmpty())) return -1;
    rest1.replace("-", " ");
    rest2.replace("-", " ");
    String [] rest1Tokens = rest1.split(" ");
    String [] rest2Tokens = rest2.split(" ");
    double maxNumberOfWords = rest1Tokens.length > rest2Tokens.length ? rest1Tokens.length : rest2Tokens.length;
    double matchingWord=0;
    boolean firstWordMatch = false;
    if(rest1Tokens[0].trim().equalsIgnoreCase(rest2Tokens[0].trim()))
        firstWordMatch = true;
    for(String restname1 : rest1Tokens){
    
        for(String restname2 :  rest2Tokens){
            if(restname1.trim().equalsIgnoreCase(restname2.trim())) {
                matchingWord++;
            }
        
        }
    
    }    
    if(matchingWord == 0 || maxNumberOfWords ==0 || !firstWordMatch) {
            return 0;
        }
    else{
    
    return (matchingWord/maxNumberOfWords)*100;
    }
    }
    
    
    
    public static double streetSimilar(String rest1, String rest2){
    if((rest1 == null || rest1.isEmpty()) || (rest2 == null || rest2.isEmpty())) return -1;
    
    String [] rest1Tokens = rest1.split(" ");
    String [] rest2Tokens = rest2.split(" ");
    double maxNumberOfWords = rest1Tokens.length > rest2Tokens.length ? rest1Tokens.length : rest2Tokens.length;
    double matchingWord=0;
   // boolean firstWordMatch = false;
   for(String restname1 : rest1Tokens){
    
        for(String restname2 :  rest2Tokens){
            if(restname1.trim().equalsIgnoreCase(restname2.trim())) {
                matchingWord++;
            }
        
        }
    
    }    
    if(matchingWord == 0 || maxNumberOfWords ==0 ) {
            return 0;
        }
    else{
    
    return (matchingWord/maxNumberOfWords)*100;
    }
    }
    
    
    public static double zipcodeSimilar(String zipcode1 , String zipcode2){
  if((zipcode1 == null || zipcode1.isEmpty()) || (zipcode2 == null || zipcode2.isEmpty())) return -1;
    
    
     String [] zipcode11Tokens = zipcode1.split("-");
    String [] zipcode12Tokens = zipcode2.split("-");
    double maxNumberOfWords = zipcode11Tokens.length > zipcode12Tokens.length ? zipcode11Tokens.length : zipcode12Tokens.length;
    double matchingWord=0;
    for(String zipcodename1 : zipcode11Tokens){
    
        for(String zipcodename2 :  zipcode12Tokens){
            if(zipcodename1.trim().equalsIgnoreCase(zipcodename2.trim())) {
                matchingWord++;
            }
        
        }
    
    }    
    if(matchingWord == 0 || maxNumberOfWords ==0) {
            return 0;
        }
    else{
    
    return (matchingWord/maxNumberOfWords)*100;
    }
    
    }
    
    
    
    
    
}
