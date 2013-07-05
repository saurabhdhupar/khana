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

/**
 * This object represents a message stored/retrieved from an SQS queue. A
 * message has an id and content.
 */
public class QMessage {

    private String id;
    private String receiptHandle;
    private String md5OfBody;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMd5OfBody() {
        return md5OfBody;
    }

    public void setMd5OfBody(String md5OfBody) {
        this.md5OfBody = md5OfBody;
    }

    public String getReceiptHandle() {
        return receiptHandle;
    }

    public void setReceiptHandle(String receiptHandle) {
        this.receiptHandle = receiptHandle;
    }

    @Override
    public String toString() {
        return "{ id: " + id + ", content: \"" + content + "\" }";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof QMessage))
            return false;
        QMessage qmsg = (QMessage) obj;
        return id.equals(qmsg.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
