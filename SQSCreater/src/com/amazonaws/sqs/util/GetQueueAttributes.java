package com.amazonaws.sqs.util;

import java.util.ArrayList;
import java.util.List;

public class GetQueueAttributes {
	private static String DELAY_SECONDS = "Attribute.DelaySeconds"; 
	private static String MAXIMUM_MESSAGE_SIZE = "Attribute.MaximumMessageSize";
	private static String MESSAGE_RETENTION_PERIOD = "Attribute.MessageRetentionPeriod";
	private static String RECEIVE_MESSAGE_WAIT_TIME_SECONDS = "Attribute.ReceiveMessageWaitTimeSeconds"; 
	private static String VISIBILITY_TIMEOUT = "Attribute.VisibilityTimeout";
	
	public static List<QueueAttribute> getQueueAttributes() {
		List<QueueAttribute> attributes = new ArrayList<QueueAttribute>();
		attributes.add(new QueueAttribute(DELAY_SECONDS, SQSConfig.DELAY_SECONDS));
		attributes.add(new QueueAttribute(MAXIMUM_MESSAGE_SIZE, SQSConfig.MAXIMUM_MESSAGE_SIZE));
		attributes.add(new QueueAttribute(MESSAGE_RETENTION_PERIOD, SQSConfig.MESSAGE_RETENTION_PERIOD));
		attributes.add(new QueueAttribute(RECEIVE_MESSAGE_WAIT_TIME_SECONDS, SQSConfig.RECEIVE_MESSAGE_WAIT_TIME_SECONDS));
		attributes.add(new QueueAttribute(VISIBILITY_TIMEOUT, SQSConfig.VISIBILITY_TIMEOUT));
		return attributes;
	}

}
