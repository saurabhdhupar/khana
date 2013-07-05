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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class QueryStringToSign {

    /**
     * Gets the string to sign for signature version V1 From the Xino docs:
     * 
     * If the SignatureVersion is 1, then the string to sign is the
     * concatenation of all query string parameters (names and values) other
     * than Signature, ordered in case-insensitive alphabetical order by
     * parameter name. There are no separators in this concatenation.
     */
    public static String calculate(Map<String, String> queryParams) {

        List<String> sortedParams = getSortedQueryParams(queryParams);

        StringBuilder sb = new StringBuilder();

        for (String param : sortedParams) {
            if (!param.equalsIgnoreCase("signature")) {
                sb.append(param);
                sb.append(queryParams.get(param));
            }
        }

        return sb.toString();
    }

    private static List<String> getSortedQueryParams(Map<String, String> map) {
        ArrayList<String> params = new ArrayList<String>(map.keySet());
        Collections.sort(params, String.CASE_INSENSITIVE_ORDER);
        return params;
    }
}
