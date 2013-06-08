package operation;

import java.util.Map;

import request.BaseRequest;
import response.BaseResponse;

public interface BaseOperation {
	public BaseRequest prepareRequest(Map<String,String> requestParam);
	public BaseResponse process(BaseRequest request);	
}
