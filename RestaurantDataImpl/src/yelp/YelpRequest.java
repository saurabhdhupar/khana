package yelp;

import org.scribe.model.OAuthRequest;

import request.BaseRequest;
import type.RequestType;

public class YelpRequest implements BaseRequest{
	
	private OAuthRequest request;
	private String latitude;
	private String longitude;
	private String category;
	private String consumer_key;
	private String conseumer_secret;
	private String token;
	private String tokenSecret;
	private String term;
	private RequestType type;
	
	public OAuthRequest getRequest() {
		return request;
	}
	public void setRequest(OAuthRequest request) {
		this.request = request;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getConsumerKey() {
		return consumer_key;
	}
	public void setConsumerkey(String consumer_key) {
		this.consumer_key = consumer_key;
	}
	public String getConsumerSecret() {
		return conseumer_secret;
	}
	public void setConsumerSecret(String conseumer_secret) {
		this.conseumer_secret = conseumer_secret;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getTokenSecret() {
		return tokenSecret;
	}
	public void setTokenSecret(String tokenSecret) {
		this.tokenSecret = tokenSecret;
	}
	public void setRequestType(RequestType type) {
		this.type = type;
	}
	
	@Override
	public RequestType getRequestType() {
		return type;
	}
	public void setSearchTerm(String term) {
		this.term = term;
	}
	
	public String getSearchTerm() {
		return term;
	}
}
