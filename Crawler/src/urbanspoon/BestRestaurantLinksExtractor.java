package urbanspoon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import common.RegexMatcher;

public class BestRestaurantLinksExtractor {
	
	public List<UrbanSpoonRestaurant> extract(String cityId, String cityName) 
			throws IOException{
		List<UrbanSpoonRestaurant> restaurantLinksList = new ArrayList<UrbanSpoonRestaurant>();
		String url = UrbanSpoonSeedLinks.getBestRestaurantSeedLink(cityId, cityName);
		while(url != null) {
			Connection conn = Jsoup.connect(url);
	        conn.timeout(12000);
	        conn.userAgent("Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0)");
	        Document doc = conn.get();
	        Elements links = doc.select("a[href]");
	        for (Element link : links) {
	        	String restaurantURL = RegexMatcher.match(link.attr("abs:href"), UrbanSpoonRegularExpression.RESTAURANT_RE);
	            if(restaurantURL != null) {	
	            	int endIndex = restaurantURL.lastIndexOf("/")+1;
	            	UrbanSpoonRestaurant restaurant = new UrbanSpoonRestaurant();
	            	restaurant.setRestaurantId(getRestaurantID(cityId, restaurantURL));
	            	restaurant.setRestaurantName(restaurantURL.substring(endIndex, restaurantURL.length()));
	            	restaurant.setUrbanSpoonRestaurantURL(restaurantURL);
	            	getResturantDeatails(restaurantURL);
	            	restaurantLinksList.add(restaurant);
	            }
	        }
	        doc.getElementsByClass("pagination");
	        url = doc.getElementsByClass("pagination").select("a[href]").get(0).attr("abs:href");
		}
		
		return restaurantLinksList;
	}
	
	private String getRestaurantID(String cityID, String restaurantURL){
			int startIndex = restaurantURL.indexOf("/r/"+cityID+"/")+cityID.length()+4;
			int endIndex = restaurantURL.indexOf("/restaurant/");
			return restaurantURL.substring(startIndex, endIndex);
	}
	
	private String getResturantDeatails(String restaurantURL) throws IOException{
		UrbanSpoonRestaurant restaurant = new UrbanSpoonRestaurant();
		Connection conn = Jsoup.connect(restaurantURL);
        conn.timeout(12000);
        conn.userAgent("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0)");
        Document doc = conn.get();
        Element restaurantElement = doc.getElementsByClass("resto_details").get(0);
        restaurant.setRestaurantName(restaurantElement.getElementsByClass("page_title").text());
        restaurant.setPhoneNumber(restaurantElement.getElementsByTag("h3").text());
        Element addrElement = doc.getElementsByClass("address").get(0);
        addrElement.select("a[href]").text();
        restaurant.setStreetName(addrElement.getElementsByClass("street-address").text());
        restaurant.setRegion(addrElement.getElementsByClass("region").text());
        restaurant.setCity(addrElement.getElementsByClass("locality").text());
        System.out.println(restaurant.toString());
        //System.out.println(doc.getElementsByClass("resto_details").get(0).getElementsByClass("page_title").text());
        return null;
	}
	
	public static void main(String args[]) throws IOException {
		BestRestaurantLinksExtractor extractor = new BestRestaurantLinksExtractor();
		System.out.println(extractor.extract("6","SF-Bay-Area").toString());
		//System.out.println(extractor.extract("6","SF-Bay-Area").size());
	}

}
