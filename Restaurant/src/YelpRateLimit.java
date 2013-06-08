import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class YelpRateLimit {
	
	public static void main(String args[]) throws IOException {
		int i = 1;
		while(true) {
			
			Connection conn = Jsoup.connect("http://www.yelp.com/biz/elizabeth-on-37th-savannah");
	        conn.timeout(12000);
	        conn.userAgent("Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0)");
	        Document doc = conn.get();
	        System.out.println(i);
	        i+= 1;
		}
		
	}

}
