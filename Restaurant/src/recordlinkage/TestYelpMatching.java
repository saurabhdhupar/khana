package recordlinkage;

import common.RestaurantDO;
import common.S3Processor;

public class TestYelpMatching {
	
	public static void main(String args[]) {
		String objectname = "Peacock Indian Cuisine_37.3187630_-122.0326870_95014_South De Anza Boulevard#_#Google";
		String latitude = "37.3187630";
		String longitude = "-122.0326870";
		String searchTerm = "Peacock Indian Cuisine";
		RestaurantDO restaurant = S3Processor.getObject(objectname);
		BlockingAlogrithm.getBestYelpObject(restaurant, BlockingAlogrithm.makeYelpServiceCall(latitude, longitude, searchTerm));
	}

}
