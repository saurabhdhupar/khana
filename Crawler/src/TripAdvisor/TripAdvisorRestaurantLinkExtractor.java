package TripAdvisor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class TripAdvisorRestaurantLinkExtractor {
	
	public static List<String> restaurants = new ArrayList<String>();
	static{
		//restaurants.add("Hamro Aangan_Albany_CA");
		//restaurants.add("Ajanta Restaurant_Berkeley_CA");
		//restaurants.add("Ajanta Restaurant_Berkeley_CA");
		//restaurants.add("All Seasons_Calistoga_CA");
		//restaurants.add("All Spice Indian Restaurant_American Canyon_CA");
		//restaurants.add("All Spice Indian Restaurant_American Canyon_CA");
		//restaurants.add("Amber Dhara_Palo Alto_CA");
		//.add("Amber Moon_Burlingame_CA");
		//restaurants.add("ARKA Indian Restaurant_Sunnyvale_CA");
		//restaurants.add("Arka_Sunnyvale_CA");
		restaurants.add("Aroma Indian Cuisine_Benicia_CA");
		restaurants.add("Athidhi Indian Cuisine_Sunnyvale_CA");
		restaurants.add("Bawarchi_Sunnyvale_CA");
		restaurants.add("Bawarchi_Sunnyvale_CA");
		//restaurants.add("Bhavika's Indian Vegetarian Food_Sunnyvale_CA");
		//restaurants.add("Biryani House_Berkeley_CA");
		restaurants.add("Biryani Mania_Concord_CA");
		restaurants.add("Bombay Cuisine_Berkeley_CA");
		restaurants.add("Breads of India & Gourmet Curries_Berkeley_CA");
		restaurants.add("Burma Cafe_Daly City_CA");
		restaurants.add("Cafe Raj_Albany_CA");
		restaurants.add("Cafe Tazza_Dublin_CA");
		restaurants.add("Chaat & Curries_Berkeley_CA");
		restaurants.add("Chaat Cafe_Berkeley_CA");
		restaurants.add("Chaats & Currys_Sunnyvale_CA");
		restaurants.add("Chaats & Currys_Sunnyvale_CA");
		restaurants.add("Chemmeen Restaurant_Dublin_CA");
		restaurants.add("Chulo_Berkeley_CA");
		restaurants.add("Chulo_Berkeley_CA");
		restaurants.add("Citrus Indian Fusion_Dublin_CA");
		restaurants.add("Curry Up Now_Palo Alto_CA");
		restaurants.add("Darbar Indian Cuisine_Palo Alto_CA");
		restaurants.add("Everest Cafe_Berkeley_CA");
		restaurants.add("Flavors of India_Alameda_CA");
		restaurants.add("Flavors of India_Berkeley_CA");
		restaurants.add("Haveli Indian Cuisine_Dublin_CA");
		restaurants.add("Himalayan Flavors_Berkeley_CA");
		restaurants.add("Hot Basil Cafe_Concord_CA");
		restaurants.add("House of Curries_Berkeley_CA");
		restaurants.add("House of Curries_Berkeley_CA");
		restaurants.add("House of Curries_Albany_CA");
		restaurants.add("iChaat Cafe_Sunnyvale_CA");
		restaurants.add("India 4 U_Antioch_CA");
		restaurants.add("India Chaat & Sweets Indian Cuisine_Berkeley_CA");
		restaurants.add("Udupi Palace_Berkeley_CA");
		restaurants.add("India Palace_Alameda_CA");
		restaurants.add("Indian Flavors Express_Berkeley_CA");
		restaurants.add("Indus Village Restaurant_Berkeley_CA");
		restaurants.add("Indus Village Restaurant_Berkeley_CA");
		restaurants.add("Janta Indian Cuisine_Palo Alto_CA");
		restaurants.add("K.P. Deli_Berkeley_CA");
		restaurants.add("Kabana Restaurant_Berkeley_CA");
		restaurants.add("Khana Peena Indian Cuisine_Berkeley_CA");
		restaurants.add("Peacock's Koriander Indian Cuisine_Belmont_CA");
		restaurants.add("Little India Cafe_Sunnyvale_CA");
		restaurants.add("Little India Cafe_Sunnyvale_CA");
		restaurants.add("Madhuban Indian Cuisine_Sunnyvale_CA");
		restaurants.add("Madurai Idli kadai_Sunnyvale_CA");
		restaurants.add("Mantra_Palo Alto_CA");
		restaurants.add("Masala Jack's Original Good Ol' Indian Curryhouse_Cotati_CA");
		restaurants.add("Mehak Indian Cuisine_Berkeley_CA");
		restaurants.add("Little India Cafe_Sunnyvale_CA");
		restaurants.add("Mint Leaf_Berkeley_CA");
		restaurants.add("Momo Masala_Berkeley_CA");
		restaurants.add("Mount Everest Restaurant_Berkeley_CA");
		restaurants.add("Naan N Curry_Concord_CA");
		restaurants.add("Naan N Curry_Berkeley_CA");
		restaurants.add("Naan N Curry_Concord_CA");
		restaurants.add("Namaste Madras Cuisine_Berkeley_CA");
		restaurants.add("Namaste Plaza_Belmont_CA");
		restaurants.add("Namaste Madras Cuisine_Berkeley_CA");
		restaurants.add("Peacock Indian Cuisine_Cupertino_CA");
		restaurants.add("Peacock Indian Cuisine_Dublin_CA");
		restaurants.add("Peacock's Koriander Indian Cuisine_Belmont_CA");
		restaurants.add("Pesarattu_Sunnyvale_CA");
		restaurants.add("Priya Indian Cuisine_Berkeley_CA");
		restaurants.add("Punjabi By Nature_Berkeley_CA");
		restaurants.add("Rajjot Sweet & Snacks_Sunnyvale_CA");
		restaurants.add("Rajjot Sweet & Snacks_Sunnyvale_CA");
		restaurants.add("Rangoon Ruby_Palo Alto_CA");
		restaurants.add("Bombay Spice House_Berkeley_CA");
		restaurants.add("Roti Indian Bistro_Burlingame_CA");
		restaurants.add("Royal Indian Cuisine_Berkeley_CA");
		restaurants.add("Royal Taj_Campbell_CA");
		restaurants.add("Royal Taj_Campbell_CA");
		restaurants.add("Shalimar_Dublin_CA");
		restaurants.add("Shan Restaurant_Cupertino_CA");
		restaurants.add("Sitar Express_Cupertino_CA");
		restaurants.add("Sneha_Sunnyvale_CA");
		restaurants.add("Spice Grill_Concord_CA");
		restaurants.add("Spice Grill_Concord_CA");
		restaurants.add("Ananda Bhavan_Sunnyvale_CA");
		restaurants.add("Swagat Indian Cuisine_Concord_CA");
		restaurants.add("Swathi Tiffins_Sunnyvale_CA");
		restaurants.add("Taj Mahal_Berkeley_CA");
		restaurants.add("Tandoori Oven_Campbell_CA");
		restaurants.add("Tandoori Oven_Daly City_CA");
		restaurants.add("Taste of the Himalayas_Berkeley_CA");
		restaurants.add("Tasty Subs & Pizza_Sunnyvale_CA");
		restaurants.add("Tava Indian Kitchen_Palo Alto_CA");
		restaurants.add("Tikka Korner_Berkeley_CA");
		restaurants.add("Turmeric Restaurant_Sunnyvale_CA");
		restaurants.add("Udupi Palace_Berkeley_CA");
		restaurants.add("Urbann Turbann_Berkeley_CA");
		restaurants.add("Viceroy Indian Cuisine_Berkeley_CA");
		restaurants.add("Vik's Chaat_Berkeley_CA");
		restaurants.add("Yak n Yeti_Berkeley_CA");
		restaurants.add("Zaika_Berkeley_CA");
		restaurants.add("iChaat Cafe_Sunnyvale_CA");
		restaurants.add("Vik's Chaat_Berkeley_CA");
	}
	
	private TripAdvisorRestaurant extract(TripAdvisorRestaurantInput tripAdvisor) throws IOException {
		String resturantName = formatString(tripAdvisor.getRestaurantName());
		String city = formatString(tripAdvisor.getCityName());
		String region = tripAdvisor.getRegion();
		String query = resturantName+"-"+city+"_"+region;
		query = query.substring(0, query.length());
		Connection conn;
        try {
			conn = Jsoup.connect(TripAdvisorSeedLinks.TRIP_ADVISOR_SEED_REVIEW
					+ query + ".html");
			conn.timeout(12000);
		
			conn.userAgent("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0)");
	        Document doc = conn.get();
	        TripAdvisorRestaurant restaurant = new TripAdvisorRestaurant();
	        restaurant.setTripAdvisorLink(doc.baseUri());
	        if(!doc.baseUri().contains(city.substring(0, city.length()-1)) || !doc.baseUri().contains(region)) {
	        	System.out.println(doc.baseUri());
	        }
	        return restaurant;
        } catch (HttpStatusException e) {
        	TripAdvisorRestaurant r = new TripAdvisorRestaurant();
        	r.setTripAdvisorLink("HTTP 404 Error for : "+query);
			return r;
		}
        
	}
	
	public static void main(String args[]) throws IOException {
		for (String input : restaurants) {
			String tokens[] = input.split("_");
			TripAdvisorRestaurantInput ip = new TripAdvisorRestaurantInput();
			ip.setRestaurantName(tokens[0]);
			ip.setCityName(tokens[1]);
			ip.setRegion("California");
			TripAdvisorRestaurantLinkExtractor extractor = new TripAdvisorRestaurantLinkExtractor();
			extractor.extract(ip);
		}
		
		
	}
	
	private static String formatString(String value) {	
		String tokens[] = value.split(" ");
		value = "";
		for (String token : tokens) {
			value += token.trim()+"_";
		}
		char[] value_arr = value.toCharArray();
		value_arr[value.length()-1] = '#';
		return String.valueOf(value_arr);
	}

}
