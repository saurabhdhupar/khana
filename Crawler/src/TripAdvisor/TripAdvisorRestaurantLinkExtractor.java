package TripAdvisor;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class TripAdvisorRestaurantLinkExtractor {
	
	private TripAdvisorRestaurant extract(TripAdvisorRestaurantInput tripAdvisor) throws IOException {
		String resturantName = formatString(tripAdvisor.getRestaurantName());
		String city = formatString(tripAdvisor.getCityName());
		String region = formatString(tripAdvisor.getRegion());
		String query = resturantName+"-"+city+"_"+region;
		query = query.substring(0, query.length()-1);
		Connection conn = Jsoup.connect(TripAdvisorSeedLinks.TRIP_ADVISOR_SEED_REVIEW+query+".html");
        conn.timeout(12000);
        conn.userAgent("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0)");
        Document doc = conn.get();
        TripAdvisorRestaurant restaurant = new TripAdvisorRestaurant();
        restaurant.setTripAdvisorLink(doc.baseUri());
        System.out.println(doc.baseUri());
        return restaurant;
	}
	
	public static void main(String args[]) throws IOException {
		TripAdvisorRestaurantInput input = new TripAdvisorRestaurantInput();
		input.setRestaurantName("Rajjot Sweet & Snacks");
		input.setCityName("Sunnyvale");
		input.setRegion("California");
		TripAdvisorRestaurantLinkExtractor extractor = new TripAdvisorRestaurantLinkExtractor();
		extractor.extract(input);
		
	}
	
	private static String formatString(String value) {
		value = value.replaceAll("['&!@#$%^,.-]", "_");
		String tokens[] = value.split("_");;		
		value = "";
		for (String token : tokens) {
			value += token.trim()+"#";
		}
		value = value.replace(" ", "#");
		if(value.toCharArray()[value.length()-1] != '_') {
			return value;
		}else{
			return value.substring(0,value.length()-1);
		}
		
	}

}
