package consumer;

import java.util.Map;

public interface BaseConsumer {
	String getEndPoint();
	Map<String,String> getHeaders();
}
