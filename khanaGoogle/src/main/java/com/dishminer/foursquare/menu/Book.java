
package com.dishminer.foursquare.menu;

import java.util.List;

public class Book{
   	private Meta meta;
   	private List notifications;
   	private Response response;

 	public Meta getMeta(){
		return this.meta;
	}
	public void setMeta(Meta meta){
		this.meta = meta;
	}
 	public List getNotifications(){
		return this.notifications;
	}
	public void setNotifications(List notifications){
		this.notifications = notifications;
	}
 	public Response getResponse(){
		return this.response;
	}
	public void setResponse(Response response){
		this.response = response;
	}
}
