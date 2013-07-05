package com.amazonaws.sqs.util;

public class SQSConfig {
	public static final String accessKeyId = "AKIAJRIKBK4PS2JODPJQ";
	public static final String secretAccessKey = "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";
	public static final String QueueServiceURL = "http://queue.amazonaws.com/";
	public static final String queueName = "dishminer-crawljob-queue";
	public static final String DELAY_SECONDS = "0";
	public static final String MAXIMUM_MESSAGE_SIZE = "262144";
	public static final int maxRetries = 5;
	public static final int threadSleepDuration = 1000;
	public static final String MESSAGE_RETENTION_PERIOD = "1209600";
	public static final String RECEIVE_MESSAGE_WAIT_TIME_SECONDS = "1";
	public static final String VISIBILITY_TIMEOUT = "500";
}
