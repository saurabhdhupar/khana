package common;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

/*
 * Copyright 2010-2013 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

/**
 * This sample demonstrates how to make basic requests to Amazon S3 using
 * the AWS SDK for Java.
 * <p>
 * <b>Prerequisites:</b> You must have a valid Amazon Web Services developer
 * account, and be signed up to use Amazon S3. For more information on
 * Amazon S3, see http://aws.amazon.com/s3.
 * <p>
 * <b>Important:</b> Be sure to fill in your AWS access credentials in the
 *                   AwsCredentials.properties file before you try to run this
 *                   sample.
 * http://aws.amazon.com/security-credentials
 * 
 * 
 * 
 */
public class S3Processor {
	
	private static String BUCKET_NAME = "resturant.dishminer.temp";
	private static AmazonS3 s3= new AmazonS3Client(
			new ClasspathPropertiesFileCredentialsProvider());
	
	public static List<String> getObjectList() {
		List<String> restaurantObjecName = new ArrayList<String>();
		ListObjectsRequest request = new ListObjectsRequest();
        request.setBucketName(BUCKET_NAME);
        ObjectListing objectListing =  s3.listObjects(request); 
        for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) { 
        	restaurantObjecName.add(objectSummary.getKey());
        }
        return restaurantObjecName;
    }
	
	public static RestaurantDO getObject(String key) {
		GetObjectRequest request = new GetObjectRequest(BUCKET_NAME, key);
		S3Object object = s3.getObject(request);
		ObjectMapper mapper = new ObjectMapper();
        RestaurantDO actualObj;
    try {
        actualObj = mapper.readValue(object.getObjectContent(),  RestaurantDO.class);
        return actualObj;
		} catch (Exception e){
			
		}
    	return null;
	}
}