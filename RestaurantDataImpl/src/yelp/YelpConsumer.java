package yelp;

import java.util.HashMap;
import java.util.Map;

import common.HeaderTypes;

import consumer.BaseConsumer;

public class YelpConsumer implements BaseConsumer{

	private static String CLIENT_END_POINT = "http://api.yelp.com/v2/search";
	private static Map<String,String> HEADERS_MAP = new HashMap<String,String>();
	
	static {
		HEADERS_MAP.put(HeaderTypes.consumerKey, "LKIazio6anr-e9kusysxfw");
		HEADERS_MAP.put(HeaderTypes.consumerSecret, "GcXV9l0e3U6AYQGdWt5J5_BhpN0");
		HEADERS_MAP.put(HeaderTypes.token, "aA7PPydkwYCq44LqVZvJZCfuVdbPOi3h");
		HEADERS_MAP.put(HeaderTypes.tokenSecret, "6d1l3H-QtVZHVarzaFfFHDMfk20");
	}
	
	@Override
	public String getEndPoint() {
		return CLIENT_END_POINT;
	}

	@Override
	public Map<String, String> getHeaders() {
		return HEADERS_MAP;
	}
	
	public YelpService getService() {
		YelpService yelp = new YelpService();
		return yelp;
	}

}
