
package com.amazonaws.sqs.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * The ListQueues action returns a list of your queues.
 *           
 * 
 * <p>Java class for ListQueues element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="ListQueues">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="QueueNamePrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "queueNamePrefix"
})
@XmlRootElement(name = "ListQueues")
public class ListQueues {

    @XmlElement(name = "QueueNamePrefix", namespace = "http://queue.amazonaws.com/doc/2008-01-01/")
    protected String queueNamePrefix;

    /**
     * Gets the value of the queueNamePrefix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueueNamePrefix() {
        return queueNamePrefix;
    }

    /**
     * Sets the value of the queueNamePrefix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueueNamePrefix(String value) {
        this.queueNamePrefix = value;
    }

}
