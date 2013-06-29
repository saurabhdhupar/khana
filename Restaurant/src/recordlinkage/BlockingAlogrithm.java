package recordlinkage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.google.common.collect.ImmutableBiMap.Builder;
import com.yelp.v2.Business;

import yelp.YelpConsumer;
import yelp.YelpResponse;
import yelp.YelpServiceProcessor;
import common.AddressNormalizer;
import common.DistanceFinder;
import common.HeaderTypes;
import common.RequestParam;
import common.RestaurantDO;
import common.S3Processor;
import common.StringDistance;

public class BlockingAlogrithm {
	
	private static int PERCENT = 20;
	private static double STRING_SIMILARITY = 0.8;
	private static double ADDRESS_STRING_SIMILARITY = 0.6;
	
	
	private static enum BlockingFeature {
		RESTAURANT_NAME_STRING_DISTANCE,
		GEO_LOCCATION_DISTANCE,
	}
    private static final Map<BlockingFeature, Double> weightMap = new HashMap<BlockingFeature, Double>();
	static {
		weightMap.put(BlockingFeature.RESTAURANT_NAME_STRING_DISTANCE, 0.5);
		weightMap.put(BlockingFeature.GEO_LOCCATION_DISTANCE, 0.5);
	}
	
	List<Set<String>> getMatch(List<String> resturantObjects) {
		Iterator<String> resIterator = resturantObjects.iterator();
		Set<String> alreadyMatch = new HashSet<String>();
		List<Set<String>> clusters = new ArrayList<Set<String>>();
		while(resIterator.hasNext()) {
			// get the first object
			String object = resIterator.next();
			if(alreadyMatch.contains(object)) {
				continue;
			}
			//remove this element from the list 
			resIterator.remove();
			// Find distance of this resurant from all the other restaurant
			TreeMap<Double,String> distanceMap  = 
					findDistanceBetweenPoints(object, resturantObjects);
			// Can do normalization (value - mean)/s.d
			// Get only top X percent of elements from this list
			List<String> topDistanceMatch = getTopObjects(distanceMap);
			// Run the next criteria for matching which is matching the restaurant name
			String restaurantname1 = getRestaurantName(object);
			List<String> finalMatchList = getTopRestaurantMatch(restaurantname1 , topDistanceMatch);
			List<String>  matchaddressList = matchRestaurantAddress(object , finalMatchList);
			alreadyMatch.addAll(matchaddressList);
			// Need to remove match elements from list so that as we dont want to 
			// run this algorithm fro them
			Set<String> cluster_ = new HashSet<String>(matchaddressList);
			cluster_.add(object);
			clusters.add(cluster_);
		}
		return clusters;
	}

	private List<String> matchRestaurantAddress(String restaurantName1,
			List<String> topDistanceMatch) {
		List<String> topRestaurant = new ArrayList<String>();
		String zipCode1 = getRestaurantZipCode(restaurantName1);
		String restaurantAddres1 = getRestaurantAddress(restaurantName1);		
		for (String restaurant : topDistanceMatch) {
			String zipCode2 = getRestaurantZipCode(restaurant);
			if(zipCode1.equals(zipCode2)) {
				String restaurantAddres2 = getRestaurantAddress(restaurant);
				if(StringDistance.findSimillarityByJaroWinkler(restaurantAddres1,
						restaurantAddres2) > ADDRESS_STRING_SIMILARITY){
					topRestaurant.add(restaurant);
				}
			}
			
		}
		return topRestaurant;
	}

	private String getRestaurantAddress(String restaurant) {
		return AddressNormalizer.getNormalizeStreetAddress(restaurant.split("_")[4].split("#_#")[0]);
	}
	
	private String getRestaurantZipCode(String restaurant) {
		return restaurant.split("_")[3];
	}

	private List<String> getTopRestaurantMatch(String restaurantName1 , List<String> topDistanceMatch) {
		List<String> topRestaurant = new ArrayList<String>();
		for (String restaurant : topDistanceMatch) {
			String restaurantName2 = getRestaurantName(restaurant);
			if(StringDistance.findSimillarityByJaroWinkler(restaurantName1,
					restaurantName2) > STRING_SIMILARITY){
				topRestaurant.add(restaurant);
			}
		}
		return topRestaurant;
	}

	// TODO
	private String getRestaurantName(String restaurant) {
		return restaurant.split("_")[0];
	}

	private List<String> getTopObjects(TreeMap<Double, String> distanceMap) {
		List<String> topObject = new ArrayList<String>();
		int total = distanceMap.keySet().size();
		int number_to_take = Math.round((PERCENT*total/100));
		Set<Double> distanceValues = distanceMap.descendingKeySet();
		Iterator<Double> itr = distanceValues.iterator();
		int i = 0;
		while(itr.hasNext() && i < number_to_take) {
			double calculateDistance = itr.next();
			double distance = 1000/calculateDistance;
			if(distance < 1000) {
				topObject.add(distanceMap.get(calculateDistance));
				i += 1;
			}
		}
		return topObject;
	}

	private TreeMap<Double,String> findDistanceBetweenPoints(String object,
			List<String> restaurantList) {	
		double latitude1 = getLatitude(object);
		double longitude1 = getLongitude(object);
		TreeMap<Double,String> distanceMap = new TreeMap<Double,String>();
		for (String restaurant : restaurantList) {
			double latitude2 = getLatitude(restaurant);
			double longitude2 = getLongitude(restaurant);
			double distance = DistanceFinder.distance(latitude1, longitude1, latitude2, longitude2, 'K');
			distanceMap.put(1/distance , restaurant);
		}
		return distanceMap;
	}
	
	//TODO :
	private double getLatitude(String restaurantObject) {
		return Double.valueOf(restaurantObject.split("_")[1]);
	}
	
	//TODO :
	private double getLongitude(String restaurantObject) {
		return Double.valueOf(restaurantObject.split("_")[2]);
	}
	
	public static void main(String args[]) {
		BlockingAlogrithm algorithm = new BlockingAlogrithm();
		List<String> restaurantList = S3Processor.getObjectList();
		List<Set<String>> list_obj = algorithm.getMatch(restaurantList);
		HashMap<String,Set<String>> yelpClusterMap = new HashMap<String,Set<String>>();
		Set<String> yelp_unknow = new HashSet<String>();
		List<Set<String>> remove_list_obj = new ArrayList<Set<String>>();
		for (Set<String> set : list_obj) {
			String objectname = getObjectNameForYelp(set);
			RestaurantDO restaurant = S3Processor.getObject(objectname);
			YelpResponse response = makeYelpServiceCall(restaurant.getLattitude(), restaurant.getLongitude(), restaurant.getName());
			TreeMap<Double,Business> yelpDistancemap = getBestYelpObject(restaurant , response);
			Set<Double> yelpDistance = yelpDistancemap.descendingKeySet();
			Iterator<Double> itr = yelpDistance.iterator();
			int top = 0;
			while(itr.hasNext()) {
				Double distance = itr.next();
				Business yelpObj = yelpDistancemap.get(distance);
				if(top == 0) {
					set.add(yelpObj.toString());
					if(yelpClusterMap.containsKey(yelpObj.getUrl())) {
						Set<String> existingset = yelpClusterMap.get(yelpObj.getUrl());
						remove_list_obj.add(existingset);
						set.addAll(existingset);
					}
					else {
						yelpClusterMap.put(yelpObj.getUrl(), set);
					}
					yelp_unknow.remove(yelpObj.getUrl());
				}
				else{
					yelp_unknow.add(yelpObj.getUrl());
				}
				top += 1;
			}
			//System.out.println(set.toString());
		}
		
		Iterator<Set<String>> itr_set_list = list_obj.iterator();
		while(itr_set_list.hasNext()) {
			Set<String> current = itr_set_list.next();
			if(remove_list_obj.contains(current)) {
				itr_set_list.remove();
			}
		}
		Iterator<Set<String>> itr_set_list1 = list_obj.iterator();
		while(itr_set_list1.hasNext()) {
			System.out.println("<Start>"+itr_set_list1.next()+"<End>");
		}
		
		System.out.println("<--------------->");
		System.out.println("<--------------->");
		System.out.println("<--------------->");
		System.out.println("<--------------->");
		System.out.println(yelp_unknow.toString());
		
		//System.out.println(list_obj.toString());
	}
	
	private static TreeMap<Double,Business> getBestYelpObject(RestaurantDO restaurant,
			YelpResponse response) {
		TreeMap<Double,Business> distanceMap = new TreeMap<Double,Business>();
		if(response != null && response.getResults().getBusinesses().size() > 0) {
			List<Business> results = response.getResults().getBusinesses();
			for (Business business : results) {
				double name = StringDistance.findSimillarityByJaroWinkler(restaurant.getName(),business.getName());
				double distance = 1/DistanceFinder.distance(Double.valueOf(restaurant.getLattitude()), Double.valueOf(restaurant.getLongitude()), 
						business.getLocation().getCoordinate().getLatitude(), business.getLocation().getCoordinate().getLongitude(), 'K');
				//double address = StringDistance.findSimillarityByJaroWinkler(restaurant.getAddress().get(0).getAddressLine(),business.getLocation().getAddress().get(0));
				distanceMap.put(name+distance, business);
			}
		}
		return distanceMap; 
		
	}

	private static String getObjectNameForYelp(Set<String> set) {
		Iterator<String> itr = set.iterator();
		boolean hasFourSquare = false;
		String  objNameFourSqure = null;
		boolean hasGoogle = false;
		String  objNameGoogle = null;
		while(itr.hasNext()) {
			String objName = itr.next();
			if(objName.contains("FourSquare")) {
				hasFourSquare = true;
				objNameFourSqure = objName;
			}
			else if(objName.contains("Google")) {
				hasGoogle = true;
				objNameGoogle = objName;
			}
		}
		
		if(hasFourSquare) {
			return objNameFourSqure;
		}
		
		else if(hasGoogle) {
			return objNameGoogle;
		}
		
		return null;
	}

	static YelpResponse makeYelpServiceCall(String latitude,String longitude,String searchTerm){
		Map<String,String> paramMap = new HashMap<String,String>();
		YelpServiceProcessor processor = new YelpServiceProcessor();
		YelpConsumer consumer = (YelpConsumer) processor.getConsumer();
		Map<String,String> headers = consumer.getHeaders();
		paramMap.put(RequestParam.END_POINT,consumer.getEndPoint());
		paramMap.put(RequestParam.CONSUMER_KEY, headers.get(HeaderTypes.consumerKey));
		paramMap.put(RequestParam.CONSUMER_SECRET, headers.get(HeaderTypes.consumerSecret));
		paramMap.put(RequestParam.TOKEN, headers.get(HeaderTypes.token));
		paramMap.put(RequestParam.TOKEN_SECRET, headers.get(HeaderTypes.tokenSecret));
		paramMap.put(RequestParam.LATITUDE,  latitude);
		paramMap.put(RequestParam.LONGITUDE, longitude);
		paramMap.put(RequestParam.CATEGORY, "restaurants");
		paramMap.put(RequestParam.TERM, searchTerm);
		YelpResponse response = (YelpResponse) processor.exceute(paramMap);
		return response;
	}

}

