package com.dishminer.FourSqaure;

import java.util.HashMap;
import java.util.Map;


public class FourSquareUtils {

	
	
	public static Map<String, String> createRequestParams(String ll, String categoryId, String radius, String limit){
		
		
		FourSquareRequest request = new FourSquareRequest();
		request.setLongitudeLatitude(ll);
		request.setRadius(radius);
		request.setCategoryId(categoryId);
		request.setLimit(limit);
		return createFourSquareRequest(request);
		
		
		
		
	}
	
	
	public static FourSquareCredentials createFourSquareCredentials(){
		
		FourSquareCredentials credentials = new FourSquareCredentials();
		credentials.setClientId("O2AXUARD3AJDUBACUFCSDYQOXAJUUZ0XL1XXYRK45SMOGTFA");
		credentials.setClient_Secret("ZWBPOTKOLKRAFPCMS2EPYVE3YXPWFAQVGM2URNYF0KFB0XVG");
		credentials.setRedirectUrl("dishminer.com");
		
		return credentials;
		
		
		
		
	}
	
	
	
	
	private static Map<String,String> createFourSquareRequest(FourSquareRequest request){
		
		
		 Map<String,String> requestParams =new  HashMap<String, String>();
		 
		 requestParams.put(FourSquareParams.ll, request.getLongitudeLatitude());
		 requestParams.put(FourSquareParams.CategoryId, request.getCategoryId());
		 requestParams.put(FourSquareParams.Radius, request.getRadius());
		 requestParams.put(FourSquareParams.Limit, request.getLimit());
		 
		 return requestParams;
		 
		 
		
		
	}
	
	
	
}
