/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dishminer.resturantsUtils;


import com.dishminer.resturantsservice.APIMetadataBO;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


/**
 *
 * @author agupta6
 */
public class resturantsUtils {
    
    
    
 
 public static ArrayList<APIMetadataBO> getApis(){
     
     
     ArrayList<APIMetadataBO> apiMetaDataBOList = new ArrayList<APIMetadataBO>();
 
    try {
 
	File fXmlFile = new File("ResturantsApiMetadata.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("api");
 	for (int temp = 0; temp < nList.getLength(); temp++) {
                APIMetadataBO metaDataBO = new APIMetadataBO();
		Node nNode = nList.item(temp);
 
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
			Element eElement = (Element) nNode;
                        metaDataBO.setApiName(eElement.getElementsByTagName("apiName").item(0).getTextContent());
                        int iloop=0;
                        HashMap<String,String> apiUrls = new HashMap<String, String>();
                       // System.out.print(eElement.getElementsByTagName("apiURL").item(0).getTextContent());
                        NodeList urlNodeList = eElement.getElementsByTagName("apiURL");
                        while( urlNodeList.getLength() >iloop ){
                            Node urlNode = urlNodeList.item(iloop);
                            Element innerElement = (Element) urlNode;
                            apiUrls.put(innerElement.getAttribute("id"), urlNode.getFirstChild().getNodeValue());
                            iloop++;
                        }
                        metaDataBO.setApiUrl(apiUrls);
                        iloop=0;
                        HashMap<String,String> apiKeys = new HashMap<String, String>();
                        NodeList keysNodeList = eElement.getElementsByTagName("apiKey");
                        while(keysNodeList.getLength() >iloop){
                            Node keyNode = keysNodeList.item(iloop);
                            Element innerElement = (Element) keyNode;
                            apiKeys.put(innerElement.getAttribute("id"), keyNode.getFirstChild().getNodeValue());
                            iloop++;
                        }
                        metaDataBO.setKeys(apiKeys);
                        apiMetaDataBOList.add(metaDataBO);
			
		}
	}
    } catch (Exception e) {
	e.printStackTrace();
    }
        return apiMetaDataBOList;
  }
 
 
 
 
 
 public static ArrayList<String> getCities(){
     
     
     ArrayList<String> cityList = new ArrayList<String>();
 
    try {
 
	File fXmlFile = new File("BayAreaCities.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("city");
 	for (int temp = 0; temp < nList.getLength(); temp++) {
                String city = new String();
		Node nNode = nList.item(temp);
 
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
			Element eElement = (Element) nNode;
                          NodeList urlNodeList = eElement.getElementsByTagName("cityName");
                      int iloop=0;
                        while( urlNodeList.getLength() >iloop ){
                            Node urlNode = urlNodeList.item(iloop);
                            city= urlNode.getFirstChild().getNodeValue();
                           
                              
                        cityList.add(city);
                      
                                iloop++;
                        }
                       
			
		}
	}
    } catch (Exception e) {
	e.printStackTrace();
    }
        return cityList;
  }
 
 
 
 
}
    
    
    
    
    
    
  
