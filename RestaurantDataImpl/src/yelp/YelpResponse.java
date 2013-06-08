package yelp;

import response.BaseResponse;
import type.ResponseType;

import com.yelp.v2.YelpSearchResult;

public class YelpResponse implements BaseResponse{
	private YelpSearchResult results;
	
	public YelpSearchResult getResults() {
		return results;
	}

	public void setResults(YelpSearchResult results) {
		this.results = results;
	}

	@Override
	public ResponseType getResponseType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
