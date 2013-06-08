package Googleplus;

import java.net.URLEncoder;

public class GooglePlusSeedLinks {
	
	public static String GOOGLE_PLUS_LINK = "https://plus.google.com/local/";
	
	public static String buildQuery(GooglePlusRestaurantInputObject restaurantInput) {
		String location = "";
		if(restaurantInput.getCity() != null) {
			location += restaurantInput.getCity()+",";
		}
		if(restaurantInput.getRegion() != null) {
			location += restaurantInput.getRegion()+",";
		}
		if(restaurantInput.getCountry() != null) {
			location += restaurantInput.getCountry();
		}
		return URLEncoder.encode(location)+"/s/"+
				URLEncoder.encode(restaurantInput.getRestaurantName()).replace("+", "%20");
	}
}
