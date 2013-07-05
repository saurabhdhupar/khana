package yelp;

import java.io.IOException;
import java.util.Map;

import operation.BaseOperation;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;

import request.BaseRequest;
import response.BaseResponse;

import com.yelp.v2.YelpSearchResult;

import common.RequestParam;

public class YelpSearchOpeartion implements BaseOperation{

	@Override
	public BaseRequest prepareRequest(Map<String,String> requestParam) {
		YelpRequest request = new YelpRequest();
		OAuthRequest authRequest = new OAuthRequest(Verb.GET, requestParam.get(RequestParam.END_POINT));
		request.setRequest(authRequest);
		request.setCategory(requestParam.get(RequestParam.CATEGORY));
		request.setLatitude(requestParam.get(RequestParam.LATITUDE));
		request.setLongitude(requestParam.get(RequestParam.LONGITUDE));
		request.setConsumerkey(requestParam.get(RequestParam.CONSUMER_KEY));
		request.setConsumerSecret(requestParam.get(RequestParam.CONSUMER_SECRET));
		request.setToken(requestParam.get(RequestParam.TOKEN));
		request.setTokenSecret(requestParam.get(RequestParam.TOKEN_SECRET));
		request.setSearchTerm(requestParam.get(RequestParam.TERM));
		return request;
	}

	@Override
	public BaseResponse process(BaseRequest request, boolean out) {
		YelpResponse yelpResponse = new YelpResponse();
		YelpRequest	yelpRequest = (YelpRequest)request;
		Token accessToken = new Token(yelpRequest.getToken(), yelpRequest.getTokenSecret());
		YelpService service = new YelpService();
		service.initService(yelpRequest.getConsumerKey(), yelpRequest.getConsumerSecret());
		yelpRequest.getRequest().addQuerystringParameter("ll", yelpRequest.getLatitude()+","+yelpRequest.getLongitude());
		yelpRequest.getRequest().addQuerystringParameter("category", yelpRequest.getCategory());
		yelpRequest.getRequest().addQuerystringParameter("term", yelpRequest.getSearchTerm());
		service.getAuthService().signRequest(accessToken, yelpRequest.getRequest());
		Response response = yelpRequest.getRequest().send();
		String rawData = response.getBody();
		if(out) {
			System.out.println(rawData);
		}
		
		YelpSearchResult places = null;
		try {
			places = new ObjectMapper().readValue(rawData, YelpSearchResult.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    yelpResponse.setResults(places);
		return yelpResponse;
	}
}
