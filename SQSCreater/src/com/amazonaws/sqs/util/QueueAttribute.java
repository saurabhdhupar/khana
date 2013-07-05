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

public class QueueAttribute {

    private String name;
    private String value;

    public QueueAttribute() {
    }

    public QueueAttribute(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String toString() {
        return "Name: " + name + ", Value: " + value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
