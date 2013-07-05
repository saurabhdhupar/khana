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

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpMethod;

public class HttpResponse {

    private String body;
    private int httpStatusCode;

    private HttpResponse(String body, int httpStatusCode) {
        this.body = body;
        this.httpStatusCode = httpStatusCode;
    }

    public static HttpResponse parseMethod(HttpMethod method) throws IOException {
        int httpStatusCode = method.getStatusCode();
        InputStream in = method.getResponseBodyAsStream();

        int numRead = -1;
        byte[] buf = new byte[4 * 1024];

        String responseBody = new String("");

        while ((numRead = in.read(buf)) != -1) {
            String str = new String(buf, 0, numRead);
            responseBody = responseBody + str;
        }
        method.releaseConnection();
        return new HttpResponse(responseBody, httpStatusCode);
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getBody() {
        return body;
    }
}
