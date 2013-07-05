
package com.amazonaws.sqs.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Message element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="Message">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="MessageId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="ReceiptHandle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="MD5OfBody" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="Body" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "messageId",
    "receiptHandle",
    "md5OfBody",
    "body"
})
@XmlRootElement(name = "Message")
public class Message {

    @XmlElement(name = "MessageId", namespace = "http://queue.amazonaws.com/doc/2008-01-01/", required = true)
    protected String messageId;
    @XmlElement(name = "ReceiptHandle", namespace = "http://queue.amazonaws.com/doc/2008-01-01/", required = true)
    protected String receiptHandle;
    @XmlElement(name = "MD5OfBody", namespace = "http://queue.amazonaws.com/doc/2008-01-01/", required = true)
    protected String md5OfBody;
    @XmlElement(name = "Body", namespace = "http://queue.amazonaws.com/doc/2008-01-01/", required = true)
    protected String body;

    /**
     * Gets the value of the messageId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * Sets the value of the messageId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageId(String value) {
        this.messageId = value;
    }

    /**
     * Gets the value of the receiptHandle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiptHandle() {
        return receiptHandle;
    }

    /**
     * Sets the value of the receiptHandle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiptHandle(String value) {
        this.receiptHandle = value;
    }

    /**
     * Gets the value of the md5OfBody property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMD5OfBody() {
        return md5OfBody;
    }

    /**
     * Sets the value of the md5OfBody property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMD5OfBody(String value) {
        this.md5OfBody = value;
    }

    /**
     * Gets the value of the body property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets the value of the body property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBody(String value) {
        this.body = value;
    }

}
