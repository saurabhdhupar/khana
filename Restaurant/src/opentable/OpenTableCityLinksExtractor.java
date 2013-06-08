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

public class OpenTableCityLinksExtractor {
	
	private List<OpenTableCity> extract() throws IOException {
		List<OpenTableCity> cityList = new ArrayList<OpenTableCity>();
		String url = OpenTableSeedLinks.OPEN_TABLE_CITIES;
		Connection conn = Jsoup.connect(url);
        conn.timeout(12000);
        conn.userAgent("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0)");
        Document doc = conn.get();
        Elements links = doc.select("a[href]");
        for (Element link : links) {
        	String cityURL = RegexMatcher.match(link.attr("abs:href"), 
        			OpenTableRegularExpression.CITY_RE);
        
        }
		return cityList;
	}

}
