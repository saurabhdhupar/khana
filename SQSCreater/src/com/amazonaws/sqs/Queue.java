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
package com.amazonaws.sqs;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.sqs.generated.CreateQueueResponse;
import com.amazonaws.sqs.generated.DeleteMessageResponse;
import com.amazonaws.sqs.generated.DeleteQueueResponse;
import com.amazonaws.sqs.generated.ErrorResponse;
import com.amazonaws.sqs.generated.Error;
import com.amazonaws.sqs.generated.GetQueueAttributesResponse;
import com.amazonaws.sqs.generated.ListQueuesResponse;
import com.amazonaws.sqs.generated.ReceiveMessageResponse;
import com.amazonaws.sqs.generated.SendMessageResponse;
import com.amazonaws.sqs.generated.SetQueueAttributesResponse;
import com.amazonaws.sqs.util.QMessage;
import com.amazonaws.sqs.util.QueueAttribute;
import com.amazonaws.sqs.util.QueueException;
import com.amazonaws.sqs.util.SQSClient;
import com.amazonaws.sqs.util.SQSConfig;
import com.amazonaws.sqs.util.Translator;

public class Queue {

    final private String ApproximateNumberOfMessagesAttr = "ApproximateNumberOfMessages";
    final private String VisibilityTimeoutAttr = "VisibilityTimeout";
    final private String AllAttr = "All";

    private String queueEndpoint;

    public String getQueueEndpoint() {
        return queueEndpoint;
    }

    public void setQueueEndpoint(String queueEndpoint) {
        this.queueEndpoint = queueEndpoint;
    }

    public Queue(String queueEndpoint) {
        this.queueEndpoint = queueEndpoint;
    }

    static public Queue createQueue(String name) throws Exception {
        SQSClient sqsClient = new SQSClient(SQSConfig.QueueServiceURL);

        sqsClient.setAction("CreateQueue");
        if (name != null) {
            sqsClient.addQueryParam("QueueName", name);
        }

        Object response = sqsClient.callAndUnmarshal();
        CreateQueueResponse cqr = null;
        try {
            cqr = (CreateQueueResponse) response;
        } catch (ClassCastException e) {
            /*
             * what we got back wasn't a success response, must have been an
             * error try casting it to an object that represents an error
             * returned by SQS
             */
            ErrorResponse errorResponse = (ErrorResponse) response;
            Error error = errorResponse.getError().get(0);
            QueueException qException = new QueueException(error.getCode());
            throw qException;
        }

        Queue newQueue = new Queue(cqr.getCreateQueueResult().getQueueUrl());
        System.out.println("Created a new queue.  Queue url: " + newQueue.getQueueEndpoint());
        return newQueue;
    }

    static public List<Queue> listQueues(String prefix) throws Exception {
        SQSClient sqsClient = new SQSClient(SQSConfig.QueueServiceURL);

        sqsClient.setAction("ListQueues");
        if (prefix != null && !prefix.equals("")) {
            sqsClient.addQueryParam("QueueNamePrefix", prefix);
        }

        Object response = sqsClient.callAndUnmarshal();
        ListQueuesResponse lqr = null;
        try {
            lqr = (ListQueuesResponse) response;
        } catch (ClassCastException e) {
            /*
             * what we got back wasn't a success response, must have been an
             * error try casting it to an object that represents an error
             * returned by SQS
             */
            ErrorResponse errorResponse = (ErrorResponse) response;
            Error error = errorResponse.getError().get(0);
            QueueException qException = new QueueException(error.getCode());
            throw qException;
        }

        List<Queue> queues = new ArrayList<Queue>();
        if (lqr.getListQueuesResult() != null && lqr.getListQueuesResult().getQueueUrl() != null) {
            for (String queueUrl : lqr.getListQueuesResult().getQueueUrl()) {
                queues.add(new Queue(queueUrl));
            }
        }
        return queues;
    }

    public void deleteMessage(String receiptHandle) throws Exception {
        SQSClient sqsClient = new SQSClient(queueEndpoint);
        sqsClient.setAction("DeleteMessage");
        if (receiptHandle != null) {
            sqsClient.addQueryParam("ReceiptHandle", receiptHandle);
        }

        // make the call
        Object response = sqsClient.callAndUnmarshal();
        DeleteMessageResponse dmr = null;
        try {
            dmr = (DeleteMessageResponse) response;
        } catch (ClassCastException e) {
            /*
             * what we got back wasn't a success response, must have been an
             * error try casting it to an object that represents an error
             * returned by SQS
             */
            ErrorResponse errorResponse = (ErrorResponse) response;
            Error error = errorResponse.getError().get(0);
            QueueException qException = new QueueException(error.getCode());
            throw qException;
        }

    }

    public void deleteQueue() throws Exception {
        SQSClient sqsClient = new SQSClient(queueEndpoint);
        sqsClient.setAction("DeleteQueue");

        // make the call
        Object response = sqsClient.callAndUnmarshal();
        DeleteQueueResponse dqr = null;
        try {
            dqr = (DeleteQueueResponse) response;
        } catch (ClassCastException e) {
            /*
             * what we got back wasn't a success response, must have been an
             * error try casting it to an object that represents an error
             * returned by SQS
             */
            ErrorResponse errorResponse = (ErrorResponse) response;
            Error error = errorResponse.getError().get(0);
            QueueException qException = new QueueException(error.getCode());
            throw qException;
        }

    }

    public List<QMessage> receiveMessage(Integer numMessages) throws Exception {
        SQSClient sqsClient = new SQSClient(queueEndpoint);
        sqsClient.setAction("ReceiveMessage");
        if (numMessages != null) {
            sqsClient.addQueryParam("MaxNumberOfMessages", String.valueOf(numMessages));
        }

        // make the call
        Object response = sqsClient.callAndUnmarshal();
        ReceiveMessageResponse rmr = null;
        try {
            rmr = (ReceiveMessageResponse) response;
        } catch (ClassCastException e) {
            /*
             * what we got back wasn't a success response, must have been an
             * error try casting it to an object that represents an error
             * returned by SQS
             */
            ErrorResponse errorResponse = (ErrorResponse) response;
            Error error = errorResponse.getError().get(0);
            QueueException qException = new QueueException(error.getCode());
            throw qException;
        }

        // parse results
        if (rmr.getReceiveMessageResult() != null) {
            return Translator.translateMessages(rmr.getReceiveMessageResult().getMessage());
        } else {
            return new ArrayList<QMessage>();
        }
    }

    public QMessage sendMessage(String messageBody) throws Exception {
        SQSClient sqsClient = new SQSClient(queueEndpoint);
        sqsClient.setAction("SendMessage");
        if (messageBody != null) {
            sqsClient.addQueryParam("MessageBody", messageBody);
        }

        // make the call
        Object response = sqsClient.callAndUnmarshal();
        SendMessageResponse smr = null;
        try {
            smr = (SendMessageResponse) response;
        } catch (ClassCastException e) {
            /*
             * what we got back wasn't a success response, must have been an
             * error try casting it to an object that represents an error
             * returned by SQS
             */
            ErrorResponse errorResponse = (ErrorResponse) response;
            Error error = errorResponse.getError().get(0);
            QueueException qException = new QueueException(error.getCode());
            throw qException;
        }

        // parse results
        QMessage result = new QMessage();
        result.setId(smr.getSendMessageResult().getMessageId());
        result.setContent(messageBody);
        result.setMd5OfBody(smr.getSendMessageResult().getMD5OfMessageBody());

        return result;
    }

    public List<QueueAttribute> getQueueAttributes(List<QueueAttribute> attributes)
            throws Exception {
        SQSClient sqsClient = new SQSClient(queueEndpoint);
        sqsClient.setAction("GetQueueAttributes");
        if (attributes != null) {
            for (QueueAttribute attribute : attributes) {
                sqsClient.addQueryParam("AttributeName", attribute.getName());
            }
        }

        // make the call
        Object response = sqsClient.callAndUnmarshal();
        GetQueueAttributesResponse gqar = null;
        try {
            gqar = (GetQueueAttributesResponse) response;
        } catch (ClassCastException e) {
            /*
             * what we got back wasn't a success response, must have been an
             * error try casting it to an object that represents an error
             * returned by SQS
             */
            ErrorResponse errorResponse = (ErrorResponse) response;
            Error error = errorResponse.getError().get(0);
            QueueException qException = new QueueException(error.getCode());
            throw qException;
        }

        // parse results
        return Translator.translateAttributes(gqar.getGetQueueAttributesResult().getAttribute());
    }

    public void setQueueAttributes(List<QueueAttribute> attributes) throws Exception {
        SQSClient sqsClient = new SQSClient(queueEndpoint);
        sqsClient.setAction("SetQueueAttributes");
        if (attributes != null) {
            for (QueueAttribute attribute : attributes) {
                sqsClient.addQueryParam(attribute.getName(), attribute.getValue());
            }
        }
        // make the call
        Object response = sqsClient.callAndUnmarshal();
        SetQueueAttributesResponse sqar = null;
        try {
            sqar = (SetQueueAttributesResponse) response;
        } catch (ClassCastException e) {
            /*
             * what we got back wasn't a success response, must have been an
             * error try casting it to an object that represents an error
             * returned by SQS
             */
            ErrorResponse errorResponse = (ErrorResponse) response;
            Error error = errorResponse.getError().get(0);
            QueueException qException = new QueueException(error.getCode());
            throw qException;
        }
    }

    public String getApproximateNumberOfMessages() throws Exception {
        QueueAttribute countAttr = new QueueAttribute();
        countAttr.setName(ApproximateNumberOfMessagesAttr);

        List<QueueAttribute> attributesRequest = new ArrayList<QueueAttribute>();
        attributesRequest.add(countAttr);
        List<QueueAttribute> attributes = getQueueAttributes(attributesRequest);
        countAttr = attributes.get(0);
        String numMessages = countAttr.getValue();

        return numMessages;

    }

}
