
package com.dishminer.googleresponse;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
 @XmlRootElement
public class GooglePlaceDetailDO{
   	private List html_attributions;
   	private Result result;
   	private String status;

 	public List getHtml_attributions(){
		return this.html_attributions;
	}
	public void setHtml_attributions(List html_attributions){
		this.html_attributions = html_attributions;
	}
 	public Result getResult(){
		return this.result;
	}
	public void setResult(Result result){
		this.result = result;
	}
 	public String getStatus(){
		return this.status;
	}
	public void setStatus(String status){
		this.status = status;
	}
}
