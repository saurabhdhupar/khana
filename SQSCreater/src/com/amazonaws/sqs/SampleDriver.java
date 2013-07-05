//*********************************************************************************************************************
//Copyright 2008 Amazon Technologies, Inc.  
//Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in 
//compliance with the License. 

//You may obtain a copy of the License at:http://aws.amazon.com/apache2.0  This file is distributed on 
//an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 

//See the License for the specific language governing permissions and limitations under the License. 
//*********************************************************************************************************************
package com.amazonaws.sqs;

import java.util.List;

import com.amazonaws.sqs.util.GetQueueAttributes;
import com.amazonaws.sqs.util.QMessage;
import com.amazonaws.sqs.util.QueueException;
import com.amazonaws.sqs.util.SQSConfig;
import com.dishminer.crawl.common.MessagePraramMap;

public class SampleDriver {

    public static void main(String[] args) throws Exception {
        System.out.println("Amazon Sample SQS Java application");
        System.out.println("  - SQS WSDL 2008-01-01");
        System.out.println("For demonstration purposes only");
        System.out.println("--------------------------------------");

        // Before running this sample, you need to paste in your own accessKeyId
        // and your own accessKey
        if (SQSConfig.accessKeyId.equals("") || SQSConfig.secretAccessKey.equals("")) {
            System.out
            .println("Please paste the values for your accessKey and your accessKeyId into the program before running the sample.");
            System.exit(1);
        }

        Queue testQueue = null;

        // try out queue operations
        // create a queue
        boolean retry = false;
        do {
            retry = false;
            try {
                testQueue = Queue.createQueue(SQSConfig.queueName);
                System.out.println("Queue created: " + testQueue.getQueueEndpoint());              
            } catch (QueueException e) {
                System.out.println("CreateQueue failed with error: " + e.getErrorCode());
                if (e.getErrorCode().equals("AWS.SimpleQueueService.QueueDeletedRecently")) {
                    // let's wait 60 seconds before re-creating the queue
                    System.out.println("Recently Deleted Queue, wait 60 seconds");
                    Thread.sleep(60 * 1000); // wait 60 seconds
                    retry = true;
                } else {
                    // some other exception, rethrow
                    throw e;
                }
            }
        } while (retry);

        // list all my queues, verify our queue exists
        do {
            retry = true;
            List<Queue> queues = Queue.listQueues(SQSConfig.queueName);
            System.out.println("Looking for queue " + testQueue.getQueueEndpoint());
            
            for(Queue queue : queues) {
                if(queue.getQueueEndpoint().equals(testQueue.getQueueEndpoint())) {
                    System.out.println("Queue found");
                    retry = false; 
                }
            }
            if(retry == true) {
                // If we didn't find the queue, give SQS a chance to
                // propagate...
                System.out.println("Queue not available yet - keep polling\r");
                Thread.sleep(10 * 1000);    // wait 10 seconds
            }
        } while(retry);
        
        //testQueue.setQueueAttributes(GetQueueAttributes.getQueueAttributes());

        // send a message
        String msgId = testQueue.sendMessage(MessagePraramMap.getMessageContent()).getId();
        System.out.println("Message sent, message id: " + msgId);

        // Get Approximate Queue Count...
        // Since SQS is a distributed system, the count may not be accurate.
        String qCount = testQueue.getApproximateNumberOfMessages();
        System.out.println("Approximate Number of Messages: " + qCount);

        // now receive a message
        // because SQS is a distributed system, we need to poll until we get
        // the message
        List<QMessage> messages = testQueue.receiveMessage(1);
        do {
            Thread.sleep(1000);  // wait for a second
            messages = testQueue.receiveMessage(1);
        } while (messages.size() == 0);


        QMessage message = messages.get(0);

        System.out.println("\nMessage received");
        System.out.println("  message id:     " + message.getId());
        System.out.println("  receipt handle: " + message.getReceiptHandle());
        System.out.println(" message content: " + message.getContent());

        testQueue.deleteMessage(message.getReceiptHandle());
        System.out.println("Deleted the message.");
    }
}
