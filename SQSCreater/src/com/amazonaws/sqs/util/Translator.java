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
import java.util.List;

import com.amazonaws.sqs.generated.Attribute;
import com.amazonaws.sqs.generated.Message;

public class Translator {

    /**
     * Translates a list of JAXB-generated Messages into standard QMessages.
     * 
     * @param messages
     *            messages to translate
     * @return the translated messages
     */
    public static List<QMessage> translateMessages(List<Message> messages) {
        List<QMessage> newMessages = new ArrayList<QMessage>();

        for (Message message : messages) {
            QMessage temp = new QMessage();
            temp.setContent(message.getBody());
            temp.setId(message.getMessageId());
            temp.setReceiptHandle(message.getReceiptHandle());
            temp.setMd5OfBody(message.getMD5OfBody());
            newMessages.add(temp);
        }

        return newMessages;
    }

    /**
     * Translates a List of JAXB-generated Attributes into standard
     * QueueAttributes.
     * 
     * @param attributes
     *            attributes to translate
     * @return the translated attributes
     */
    public static List<QueueAttribute> translateAttributes(List<Attribute> attributes) {
        List<QueueAttribute> newAttributes = new ArrayList<QueueAttribute>();
        for (Attribute attribute : attributes) {
            QueueAttribute temp = new QueueAttribute();
            temp.setName(attribute.getName());
            temp.setValue(attribute.getValue());
            newAttributes.add(temp);
        }

        return newAttributes;
    }
}
