
package com.domain.project;

import java.util.List;

public class Photos{
   	private String height;
   	private List html_attributions;
   	private String photo_reference;
   	private String width;

 	public String getHeight(){
		return this.height;
	}
	public void setHeight(String height){
		this.height = height;
	}
 	public List getHtml_attributions(){
		return this.html_attributions;
	}
	public void setHtml_attributions(List html_attributions){
		this.html_attributions = html_attributions;
	}
 	public String getPhoto_reference(){
		return this.photo_reference;
	}
	public void setPhoto_reference(String photo_reference){
		this.photo_reference = photo_reference;
	}
 	public String getWidth(){
		return this.width;
	}
	public void setWidth(String width){
		this.width = width;
	}
}
