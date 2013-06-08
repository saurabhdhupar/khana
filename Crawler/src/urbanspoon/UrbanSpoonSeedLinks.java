package urbanspoon;

public class UrbanSpoonSeedLinks {
	public static String URBAN_SPOON_LINK = "http://www.urbanspoon.com/";
	public static String URBAN_SPOON_CITY_LINK = "http://www.urbanspoon.com/choose";
	private static String URBAN_SPOON_RESTAURANT_LINK =  "http://www.urbanspoon.com/lb/";
	
	public static String getBestRestaurantSeedLink(String cityId, String cityName){
		return URBAN_SPOON_RESTAURANT_LINK+cityId+"/best-restaurants-"+cityName;
	}
}
