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

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AwsSignature {
    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

    public static String calculate(String stringToSign, String secretKey) throws Exception {
        // get an hmac_sha1 key from the raw key bytes
        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes(), HMAC_SHA1_ALGORITHM);

        // get an hmac_sha1 Mac instance and initialize with the signing key
        Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
        mac.init(signingKey);

        // compute the hmac on input data bytes
        byte[] rawHmac = mac.doFinal(stringToSign.getBytes());

        // base64-encode the hmac
        String result = new String(Base64.encodeBase64(rawHmac));
        return result;
    }
}
