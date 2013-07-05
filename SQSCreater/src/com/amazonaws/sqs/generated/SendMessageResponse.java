
package com.amazonaws.sqs.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SendMessageResponse element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="SendMessageResponse">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element ref="{http://queue.amazonaws.com/doc/2008-01-01/}SendMessageResult"/>
 *           &lt;element ref="{http://queue.amazonaws.com/doc/2008-01-01/}ResponseMetadata"/>
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
    "sendMessageResult",
    "responseMetadata"
})
@XmlRootElement(name = "SendMessageResponse")
public class SendMessageResponse {

    @XmlElement(name = "SendMessageResult", namespace = "http://queue.amazonaws.com/doc/2008-01-01/", required = true)
    protected SendMessageResult sendMessageResult;
    @XmlElement(name = "ResponseMetadata", namespace = "http://queue.amazonaws.com/doc/2008-01-01/", required = true)
    protected ResponseMetadata responseMetadata;

    /**
     * Gets the value of the sendMessageResult property.
     * 
     * @return
     *     possible object is
     *     {@link SendMessageResult }
     *     
     */
    public SendMessageResult getSendMessageResult() {
        return sendMessageResult;
    }

    /**
     * Sets the value of the sendMessageResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link SendMessageResult }
     *     
     */
    public void setSendMessageResult(SendMessageResult value) {
        this.sendMessageResult = value;
    }

    /**
     * Gets the value of the responseMetadata property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseMetadata }
     *     
     */
    public ResponseMetadata getResponseMetadata() {
        return responseMetadata;
    }

    /**
     * Sets the value of the responseMetadata property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseMetadata }
     *     
     */
    public void setResponseMetadata(ResponseMetadata value) {
        this.responseMetadata = value;
    }

}
