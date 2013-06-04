
package com.domain.project;

import java.util.List;

public class Reviews{
   	private List aspects;
   	private String author_name;
   	private String author_url;
   	private String text;
   	private Number time;

 	public List getAspects(){
		return this.aspects;
	}
	public void setAspects(List aspects){
		this.aspects = aspects;
	}
 	public String getAuthor_name(){
		return this.author_name;
	}
	public void setAuthor_name(String author_name){
		this.author_name = author_name;
	}
 	public String getAuthor_url(){
		return this.author_url;
	}
	public void setAuthor_url(String author_url){
		this.author_url = author_url;
	}
 	public String getText(){
		return this.text;
	}
	public void setText(String text){
		this.text = text;
	}
 	public Number getTime(){
		return this.time;
	}
	public void setTime(Number time){
		this.time = time;
	}
}
