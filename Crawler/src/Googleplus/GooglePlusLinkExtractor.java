package Googleplus;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GooglePlusLinkExtractor {
	
	public GooglePlusRestaurant extract() throws IOException{
		GooglePlusRestaurantInputObject inputObject = new GooglePlusRestaurantInputObject();
		inputObject.setRestaurantName("Dish dash");
		inputObject.setRegion("CA");
		inputObject.setCity("Sunnyvale");
		inputObject.setCountry("US");
		inputObject.setStreetAddress("190 S Murphy Ave");
		String url = GooglePlusSeedLinks.GOOGLE_PLUS_LINK;
		url += GooglePlusSeedLinks.buildQuery(inputObject);
		Connection conn = Jsoup.connect(url);
        conn.timeout(12000);
        conn.userAgent("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0)");
        Document doc = conn.get();
        Elements restaurantsBlob = doc.select("div[class=r6Rtbe Gec ts boa]");
        for (Element restaurant : restaurantsBlob) {
        	String google_url = restaurant.select("a[href]").get(0).attr("abs:href");
        	String tokens[] = restaurant.select("span[class=lja SIa]").get(0).text().split(",");
        	if(tokens[0].trim().equals(inputObject.getStreetAddress()) && tokens[1].trim().equals(inputObject.getCity()) &&
        			tokens[2].trim().equals(inputObject.getRegion())) {
        		GooglePlusRestaurant googleRestaurant = new GooglePlusRestaurant();
        		googleRestaurant.setCity(inputObject.getCity());
        		googleRestaurant.setCountry(inputObject.getCountry());
        		googleRestaurant.setRestaurantName(inputObject.getRestaurantName());
        		googleRestaurant.setRegion(inputObject.getRegion());
        		googleRestaurant.setStreetAddress(inputObject.getStreetAddress());
        		googleRestaurant.setGooglePlusURL(google_url);
        		return googleRestaurant;
        	}
        }
        Elements links = doc.select("a[href]");
        for (Element link : links) {
        	if(link.attr("abs:href").contains(inputObject.getRestaurantName())) {
        		System.out.println(link.attr("abs:href"));
        	}
        }
		return null;
	}
	
	public static void main(String args[]) throws IOException {
		GooglePlusLinkExtractor extractor = new GooglePlusLinkExtractor();
		System.out.println(extractor.extract().toString());
		//System.out.println(extractor.extract("6","SF-Bay-Area").size());
	}


}
