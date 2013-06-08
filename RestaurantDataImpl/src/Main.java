import java.util.HashMap;
import java.util.Map;

import common.HeaderTypes;
import common.RequestParam;
import yelp.YelpConsumer;
import yelp.YelpServiceProcessor;


public class Main {
	public static void main(String args[]) {
		Map<String,String> paramMap = new HashMap<String,String>();
		YelpServiceProcessor processor = new YelpServiceProcessor();
		YelpConsumer consumer = (YelpConsumer) processor.getConsumer();
		Map<String,String> headers = consumer.getHeaders();
		paramMap.put(RequestParam.END_POINT,consumer.getEndPoint());
		paramMap.put(RequestParam.CONSUMER_KEY, headers.get(HeaderTypes.consumerKey));
		paramMap.put(RequestParam.CONSUMER_SECRET, headers.get(HeaderTypes.consumerSecret));
		paramMap.put(RequestParam.TOKEN, headers.get(HeaderTypes.token));
		paramMap.put(RequestParam.TOKEN_SECRET, headers.get(HeaderTypes.tokenSecret));
		paramMap.put(RequestParam.LATITUDE,  "30.361471");
		paramMap.put(RequestParam.LONGITUDE, "-87.164326");
		paramMap.put(RequestParam.CATEGORY, "restaurants");
		processor.exceute(paramMap);
	}

}
