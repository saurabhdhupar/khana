package processor;

import java.util.Map;

import operation.BaseOperation;
import request.BaseRequest;
import response.BaseResponse;
import consumer.BaseConsumer;

public interface BaseServiceProcessor {
	public BaseOperation getOperationType();
	public void validateRequest(BaseRequest request);
	public BaseResponse exceute(Map<String,String> param);
	public void handleResponse();
	public BaseConsumer getConsumer();
}
