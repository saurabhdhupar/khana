package recordlinkage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import common.DistanceFinder;
import common.S3Processor;
import common.StringDistance;

public class BlockingAlogrithm {
	
	private static int PERCENT = 20;
	private static double STRING_SIMILARITY = 0.8;
	
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
			// Get only top X percent of elements from this list
			List<String> topDistanceMatch = getTopObjects(distanceMap);
			// Run the next criteria for matching which is matching the restaurant name
			String restaurantname1 = getRestaurantName(object);
			List<String> finalMatchList = getTopRestaurantMatch(restaurantname1 , topDistanceMatch);
			alreadyMatch.addAll(finalMatchList);
			// Need to remove match elemts from list so that as we dont want to 
			// run this algorithm fro them
			Set<String> cluster_ = new HashSet<String>(finalMatchList);
			cluster_.add(object);
			clusters.add(cluster_);
		}
		return clusters;
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
			topObject.add(distanceMap.get(itr.next()));
			i += 1;
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
		System.out.println(algorithm.getMatch(restaurantList).toString());
	}

}
