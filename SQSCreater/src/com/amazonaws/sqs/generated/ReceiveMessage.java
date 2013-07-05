
package com.amazonaws.sqs.generated;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * Retrieves one or more messages from the specified queue, including the message body and message ID of each message. Messages returned by this action stay in the queue until you delete them. However, once a message is returned to a ReceiveMessage request, it is not returned on subsequent ReceiveMessage requests for the duration of the VisibilityTimeout. If you do not specify a VisibilityTimeout in the request, the overall visibility timeout for the queue is used for the returned messages.
 *           
 * 
 * <p>Java class for ReceiveMessage element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="ReceiveMessage">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="MaxNumberOfMessages" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *           &lt;element name="VisibilityTimeout" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
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
    "maxNumberOfMessages",
    "visibilityTimeout"
})
@XmlRootElement(name = "ReceiveMessage")
public class ReceiveMessage {

    @XmlElement(name = "MaxNumberOfMessages", namespace = "http://queue.amazonaws.com/doc/2008-01-01/")
    protected BigInteger maxNumberOfMessages;
    @XmlElement(name = "VisibilityTimeout", namespace = "http://queue.amazonaws.com/doc/2008-01-01/")
    protected BigInteger visibilityTimeout;

    /**
     * Gets the value of the maxNumberOfMessages property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxNumberOfMessages() {
        return maxNumberOfMessages;
    }

    /**
     * Sets the value of the maxNumberOfMessages property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxNumberOfMessages(BigInteger value) {
        this.maxNumberOfMessages = value;
    }

    /**
     * Gets the value of the visibilityTimeout property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getVisibilityTimeout() {
        return visibilityTimeout;
    }

    /**
     * Sets the value of the visibilityTimeout property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setVisibilityTimeout(BigInteger value) {
        this.visibilityTimeout = value;
    }

}
