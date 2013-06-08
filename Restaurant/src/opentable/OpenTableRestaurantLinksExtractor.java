package opentable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import common.RegexMatcher;

public class OpenTableRestaurantLinksExtractor {
	
	private List<String> extract() throws IOException {
		List<String> restaurantUrlList = new ArrayList<String>();
		String url = OpenTableSeedLinks.SAN_FRANCISCO_BAY_AREA_RESTAURANTS;
		Connection conn = Jsoup.connect(url);
        conn.timeout(12000);
        conn.userAgent("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0)");
        Document doc = conn.get();
        Elements links = doc.select("a[href]");
        for (Element link : links) {
        	String restaurantURL = RegexMatcher.match(link.attr("abs:href"), 
        			OpenTableRegularExpression.RESTAURANT_RE);
            if(restaurantURL != null) {	
            	restaurantUrlList.add(restaurantURL);
            }
        
        }
		return restaurantUrlList;
	}
	
	private OpenTableRestaurant extarctRestaurantDetails(String resutarantLink) throws IOException {
		OpenTableRestaurant restaurant = new OpenTableRestaurant();
		Connection conn = Jsoup.connect(resutarantLink);
        conn.timeout(12000);
        conn.userAgent("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0)");
        Document doc = conn.get();
        Elements links = doc.getElementsByClass("information");
        restaurant.setRestaurantName(links.get(0).getElementById("ProfileOverview_RestaurantName").text());
        String address[] = links.get(0).getElementById("ProfileOverview_lblAddressText").html().split("<br />");
        restaurant.setRestaurantURL(resutarantLink);
        if(address.length == 2) {
        	restaurant.setStreetName1(address[0]);
        	String[] city = address[1].split(",");
        	restaurant.setCity(city[0].trim());
        	String[] zipregion = city[1].trim().split(" ");
        	restaurant.setZipCode(zipregion[1].trim());
        	restaurant.setRegion(zipregion[0].trim());  
        }
        else{
        	restaurant.setStreetName1(address[0]);
        	restaurant.setStreetName2(address[1]);
        	String[] city = address[2].split(",");
        	restaurant.setCity(city[0].trim());
        	String[] zipregion = city[1].trim().split(" ");
        	restaurant.setZipCode(zipregion[1].trim());
        	restaurant.setRegion(zipregion[0].trim());        	
        }
        System.out.println(restaurant.toString());
        return restaurant;
	}
	
	public static void main(String args[]) throws IOException {
		OpenTableRestaurantLinksExtractor extract = new OpenTableRestaurantLinksExtractor();
		List<String> restaurantUrlList = extract.extract();
		OpenTableS3 opentables3 = new OpenTableS3("us");
		//opentables3.createOpenTableBucket();
		for (String restaurantUrl : restaurantUrlList) {
			opentables3.putOpenTableRestaurantObject(extract.extarctRestaurantDetails(restaurantUrl));
		}
	}

}
