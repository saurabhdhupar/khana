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
	
	public List<UrbanSpoonRestaurant> extract(String cityId, String cityName, UrbanSpoonS3 urbanSpoonS3Client) 
			throws IOException{
		List<UrbanSpoonRestaurant> restaurantLinksList = new ArrayList<UrbanSpoonRestaurant>();
		String url = UrbanSpoonSeedLinks.getBestRestaurantSeedLink(cityId, cityName);
		url = "http://www.urbanspoon.com/lb/6/best-restaurants-SF-Bay-Area?page=362";
		while(url != null) {
			Connection conn = Jsoup.connect(url);
	        conn.timeout(12000);
	        conn.userAgent("Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0)");
	        Document doc = conn.get();
	       Elements links = doc.getElementsByClass("details");
	        for (Element link : links) {
	        	Element url_link = link.select("a[href]").get(0);
	        	String restaurantURL = RegexMatcher.match(url_link.attr("abs:href"), UrbanSpoonRegularExpression.RESTAURANT_RE);
	            if(restaurantURL != null) {	
	            	int endIndex = restaurantURL.lastIndexOf("/")+1;
	            	UrbanSpoonRestaurant restaurant = new UrbanSpoonRestaurant();
	            	restaurant.setRestaurantId(getRestaurantID(cityId, restaurantURL));
	            	restaurant.setRestaurantName(restaurantURL.substring(endIndex, restaurantURL.length()));
	            	restaurant.setUrbanSpoonRestaurantURL(restaurantURL);
	            	getResturantDeatails(restaurant, restaurantURL);
	            	//restaurantLinksList.add(restaurant);
	            	urbanSpoonS3Client.putUrbanSpoonRestaurantObject(restaurant);
	            }
	        }
	        Elements nextpagelink = doc.getElementsByClass("pagination").select("a[href]");
	        url = null;
	        for (Element nextlink : nextpagelink) {
	        	if(nextlink.text().contains("Next")) {
	        		url = nextlink.attr("abs:href");
	        		System.out.println(url);
	        	}
			}
		}
		
		return restaurantLinksList;
	}
	
	private String getRestaurantID(String cityID, String restaurantURL){
			int startIndex = restaurantURL.indexOf("/r/"+cityID+"/")+cityID.length()+4;
			int endIndex = restaurantURL.indexOf("/restaurant/");
			return restaurantURL.substring(startIndex, endIndex);
	}
	
	private void getResturantDeatails(UrbanSpoonRestaurant restaurant, String restaurantURL) throws IOException{
		Connection conn = Jsoup.connect(restaurantURL);
        conn.timeout(12000);
        conn.userAgent("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0)");
        Document doc = conn.get();
        Element restaurantElement = doc.getElementsByClass("resto_details").get(0);
        restaurant.setRestaurantName(restaurantElement.getElementsByClass("page_title").text());
        restaurant.setPhoneNumber(restaurantElement.getElementsByTag("h3").text());
        Element addrElement = doc.getElementsByClass("address").get(0);
        String tokens[] = addrElement.select("a[href]").text().split(" ");
        restaurant.setZipCode(tokens[tokens.length-1]);
        restaurant.setStreetName(addrElement.getElementsByClass("street-address").text());
        restaurant.setRegion(addrElement.getElementsByClass("region").text());
        restaurant.setCity(addrElement.getElementsByClass("locality").text());
        restaurant.setUrbanSpoonRestaurantURL(restaurantURL);        
/*        System.out.println(restaurant.toString());
*/        //System.out.println(doc.getElementsByClass("resto_details").get(0).getElementsByClass("page_title").text());
	}
	
	public static void main(String args[]) throws IOException {
		UrbanSpoonS3 urbanSpoonS3Client = new UrbanSpoonS3("us");
		BestRestaurantLinksExtractor extractor = new BestRestaurantLinksExtractor();
		extractor.extract("6","SF-Bay-Area",urbanSpoonS3Client);
		//System.out.println(extractor.extract("6","SF-Bay-Area").size());
	}

}
