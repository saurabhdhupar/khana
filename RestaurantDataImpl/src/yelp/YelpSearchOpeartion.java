package yelp;

import java.util.Map;

import operation.BaseOperation;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;

import request.BaseRequest;
import response.BaseResponse;

import com.google.gson.Gson;
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
	public BaseResponse process(BaseRequest request) {
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
		System.out.println(rawData);
	    YelpSearchResult places = new Gson().fromJson(rawData, YelpSearchResult.class);
	    yelpResponse.setResults(places);
		return yelpResponse;
	}
}
