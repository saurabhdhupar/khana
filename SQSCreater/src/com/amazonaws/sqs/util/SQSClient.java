//*********************************************************************************************************************
// Copyright 2008 Amazon Technologies, Inc.  
// Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in 
// compliance with the License. 
//
// You may obtain a copy of the License at:http://aws.amazon.com/apache2.0  This file is distributed on 
// an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
//
// See the License for the specific language governing permissions and limitations under the License. 
//*********************************************************************************************************************
package com.amazonaws.sqs.util;

import java.io.StringReader;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SimpleTimeZone;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

public class SQSClient {
    private JAXBContext jaxbContext;
    private String action;

    protected String endpoint;
    protected Map<String, String> queryParams;
    protected String postMessageBody;

    public SQSClient(String endpoint) {
        this.endpoint = endpoint;
        this.queryParams = new HashMap<String, String>();
        try {
            this.jaxbContext = JAXBContext.newInstance("com.amazonaws.sqs.generated");
        } catch (JAXBException e) {
            throw new RuntimeException("Unexpected exception while creating JAXBContext", e);
        }
    }

    /**
     * This function sets up the call to SQS and returns the raw response.
     * 
     * @return the raw response from SQS
     * @throws QueueException
     */
    private HttpResponse makeCall() throws Exception {

        HttpMethod method = sendRequest();
        HttpResponse response = HttpResponse.parseMethod(method);

        return response;
    }

    /**
     * Constructs the entire URL to use to make the request to SQS
     * 
     * http:// <hostname> / <Query String>
     */
    private String getUrl() {
        StringBuilder sb = new StringBuilder(endpoint);
        sb.append(getQueryString());
  //      System.out.println("Sending request: " + sb.toString());
        return sb.toString();
    }

    /**
     * Constructs the query string from the queryParams hashmap <key, value>
     */
    private String getQueryString() {

        if (queryParams == null || queryParams.size() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder("?");

        Iterator<String> iter = queryParams.keySet().iterator();

        while (iter.hasNext()) {

            String paramKey = iter.next();
            String paramVal = queryParams.get(paramKey);

            if (paramVal != null) {
                paramVal = URLEncoder.encode(paramVal);
            }

            sb.append(paramKey).append("=");
            sb.append(paramVal);

            if (iter.hasNext()) {
                sb.append("&");
            }
        }
        return sb.toString();
    }

    private HttpMethod sendRequest() throws Exception {
        prepareHttpRequest();
        HttpMethod method = new GetMethod(getUrl());
        HttpClient client = new HttpClient();
        client.executeMethod(method);

        return method;
    }

    /**
     * This method calls SQS and unmarshalls the response based on the
     * JAXBContext provided in the constructor.
     * 
     * @return an object corresponding to the response from SQS
     * @throws QueueException
     */
    public Object callAndUnmarshal() throws Exception {
        int numRetriesLeft = SQSConfig.maxRetries;
        Object obj = null;
        do {
            try {
                HttpResponse response = makeCall();
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
     //           System.out.println("Got a response from SQS: " + response.getBody());
                obj = unmarshaller.unmarshal(new StringReader(response.getBody()));
               break;
            } catch (Exception e) {
                if(numRetriesLeft <= 0) {
                    throw e;
                }
                // else -- sleep for a bit before trying again
                Thread.sleep(SQSConfig.threadSleepDuration * (SQSConfig.maxRetries - numRetriesLeft));
            }
        } while (numRetriesLeft-- > 0);
        return obj;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void addQueryParam(String key, String value) {
        queryParams.put(key, value);
    }

    private void prepareHttpRequest() throws Exception {
        queryParams.put("AWSAccessKeyId", SQSConfig.accessKeyId);
        queryParams.put("Version", "2008-01-01");
        queryParams.put("Action", action);
        queryParams.put("Expires", getExpires());
        queryParams.put("SignatureVersion", "1");
        queryParams.put("Signature", calculateSignature(calculateStringToSign()));
    }

    private String calculateStringToSign() {
        return QueryStringToSign.calculate(queryParams);
    }

    private String calculateSignature(String stringToSign) throws Exception {
        return AwsSignature.calculate(stringToSign, SQSConfig.secretAccessKey);
    }
    
	private static String getExpires() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, 60);

		SimpleTimeZone timeZone = new SimpleTimeZone(0, "PST");

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
		format.setTimeZone(timeZone);
		String timestampStr = format.format(cal.getTime());

		return timestampStr;
	}

}
