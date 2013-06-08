package yelp;

import org.scribe.builder.ServiceBuilder;
import org.scribe.oauth.OAuthService;
import com.yelp.v2.YelpV2API;

import service.Service;

public class YelpService implements Service{
	private OAuthService service;
	
	public YelpService() {
	}
	
	public OAuthService getAuthService(){
		return service;
	}

	@Override
	public void initService(String consumerKey, String consumerSecret) {
	    this.service = new ServiceBuilder().provider(YelpV2API.class).apiKey(consumerKey).apiSecret(consumerSecret).build();
	}

}
