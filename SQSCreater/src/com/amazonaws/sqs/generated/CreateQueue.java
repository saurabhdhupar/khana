
package com.amazonaws.sqs.generated;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * The CreateQueue action creates a new queue. You must provide a queue name that is unique within the scope of the queues you own. The queue is assigned a queue URL; you must use this URL when performing actions on the queue.  When you create a queue, if a queue with the same name already exists, CreateQueue returns the queue URL with an error indicating that the queue already exists.
 *           
 * 
 * <p>Java class for CreateQueue element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="CreateQueue">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="QueueName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="DefaultVisibilityTimeout" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/restriction>
 *     &lt;/complexContent>
 *   &lt;/complexType>
 * &lt;/element>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "queueName",
    "defaultVisibilityTimeout"
})
@XmlRootElement(name = "CreateQueue")
public class CreateQueue {

    @XmlElement(name = "QueueName", namespace = "http://queue.amazonaws.com/doc/2008-01-01/", required = true)
    protected String queueName;
    @XmlElement(name = "DefaultVisibilityTimeout", namespace = "http://queue.amazonaws.com/doc/2008-01-01/")
    protected BigInteger defaultVisibilityTimeout;

    /**
     * Gets the value of the queueName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueueName() {
        return queueName;
    }

    /**
     * Sets the value of the queueName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueueName(String value) {
        this.queueName = value;
    }

    /**
     * Gets the value of the defaultVisibilityTimeout property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDefaultVisibilityTimeout() {
        return defaultVisibilityTimeout;
    }

    /**
     * Sets the value of the defaultVisibilityTimeout property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDefaultVisibilityTimeout(BigInteger value) {
        this.defaultVisibilityTimeout = value;
    }

}
