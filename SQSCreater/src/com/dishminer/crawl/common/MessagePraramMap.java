package com.dishminer.crawl.common;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MessagePraramMap {
	
	public static String getMessageContent() {
		Map<String,String> messageMap = new HashMap<String,String>();
		messageMap.put(MessageParam.URL, "URL");
		messageMap.put(MessageParam.RESOURCE_TYPE, ResourceType.MENU.toString());
		messageMap.put(MessageParam.WEBSITE_NAME, WebsiteName.YELP.toString());
		messageMap.put(MessageParam.OBJECT_KEY, "key");
		messageMap.put(MessageParam.NEXT_PAGE_TOKEN, "next-token");
		messageMap.put(MessageParam.LOCATION_ID, null);
		messageMap.put(MessageParam.JOB_TYPE, JobType.CRAWL.toString());
		messageMap.put(MessageParam.BUCKET_NAME, "bucketname");
		return messageMap.toString();
	}
	
	public static Map<String,String> getMessageParamMap(String messageContent) throws IOException {
		Properties props = new Properties();
		props.load(new StringReader(messageContent.substring(1, messageContent.length() - 1).replace(", ", "\n")));       
		Map<String, String> map2 = new HashMap<String, String>();
		for (Map.Entry<Object, Object> e : props.entrySet()) {
		    map2.put((String)e.getKey(), (String)e.getValue());
		}
		return map2;
	}	

}
