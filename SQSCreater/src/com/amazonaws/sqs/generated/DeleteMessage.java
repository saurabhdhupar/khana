
package com.amazonaws.sqs.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * The DeleteMessage action unconditionally removes the specified message from the specified queue. Even if the message is locked by another reader due to the visibility timeout setting, it is still deleted from the queue.
 *           
 * 
 * <p>Java class for DeleteMessage element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="DeleteMessage">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="ReceiptHandle" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "receiptHandle"
})
@XmlRootElement(name = "DeleteMessage")
public class DeleteMessage {

    @XmlElement(name = "ReceiptHandle", namespace = "http://queue.amazonaws.com/doc/2008-01-01/", required = true)
    protected String receiptHandle;

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

}
