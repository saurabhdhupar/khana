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

public class CityLinksExtracter {
	
	public List<UrbanSpoonCity> extract() throws IOException{
		List<UrbanSpoonCity> cityLinksList = new ArrayList<UrbanSpoonCity>();
		String url = UrbanSpoonSeedLinks.URBAN_SPOON_CITY_LINK;
        Connection conn = Jsoup.connect(url);
        conn.timeout(12000);
        conn.userAgent("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0)");
        Document doc = conn.get();
        Elements links = doc.select("a[href]");
        for (Element link : links) {
        	String cityURL = RegexMatcher.match(link.attr("abs:href"), UrbanSpoonRegularExpression.CITY_RE);
            if(cityURL != null) {
            	int endIndex = cityURL.lastIndexOf("-");
            	int startIndex = cityURL.indexOf("/c/")+3;
            	String tokens[]= cityURL.substring(startIndex, endIndex)
            			.split("/");
            	UrbanSpoonCity city = new UrbanSpoonCity();
            	city.setCityId(tokens[0]);
            	city.setCityName(tokens[1]);
            	city.setUrbanSpoonCityURL(cityURL);
            	cityLinksList.add(city);
            }
        }
		return cityLinksList;
	}
	
	public static void main(String args[]) throws IOException {
		CityLinksExtracter extractor = new CityLinksExtracter();
		System.out.println(extractor.extract().toString());
		System.out.println(extractor.extract().size());
	}

}
