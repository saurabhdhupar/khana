
package com.amazonaws.sqs.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ListQueuesResponse element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="ListQueuesResponse">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element ref="{http://queue.amazonaws.com/doc/2008-01-01/}ListQueuesResult"/>
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
    "listQueuesResult",
    "responseMetadata"
})
@XmlRootElement(name = "ListQueuesResponse")
public class ListQueuesResponse {

    @XmlElement(name = "ListQueuesResult", namespace = "http://queue.amazonaws.com/doc/2008-01-01/", required = true)
    protected ListQueuesResult listQueuesResult;
    @XmlElement(name = "ResponseMetadata", namespace = "http://queue.amazonaws.com/doc/2008-01-01/", required = true)
    protected ResponseMetadata responseMetadata;

    /**
     * Gets the value of the listQueuesResult property.
     * 
     * @return
     *     possible object is
     *     {@link ListQueuesResult }
     *     
     */
    public ListQueuesResult getListQueuesResult() {
        return listQueuesResult;
    }

    /**
     * Sets the value of the listQueuesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListQueuesResult }
     *     
     */
    public void setListQueuesResult(ListQueuesResult value) {
        this.listQueuesResult = value;
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
