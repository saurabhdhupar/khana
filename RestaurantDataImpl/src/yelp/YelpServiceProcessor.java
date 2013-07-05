package yelp;

import java.util.Map;

import operation.BaseOperation;
import consumer.BaseConsumer;
import processor.BaseServiceProcessor;
import request.BaseRequest;
import response.BaseResponse;

public class YelpServiceProcessor implements BaseServiceProcessor{
	
	@Override
	public BaseOperation getOperationType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateRequest(BaseRequest request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleResponse() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BaseConsumer getConsumer() {
		return new YelpConsumer();
	}

	@Override
	public BaseResponse exceute(Map<String, String> param, boolean out) { 
		YelpSearchOpeartion operation = new YelpSearchOpeartion();
		BaseRequest request = operation.prepareRequest(param);
		return operation.process(request, out);
	}

}
