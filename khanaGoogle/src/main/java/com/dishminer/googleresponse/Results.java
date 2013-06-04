
package com.dishminer.googleresponse;

import com.domain.project.Address_components;
import com.domain.project.Opening_hours;
import com.domain.project.Photos;
import java.util.List;

public class Results{
        private String formatted_address;
        private Geometry geometry;
   	private String icon;
   	private String id;
   	private String name;
   	private Opening_hours opening_hours;
   	private List<Photos> photos;
   	private String rating;
   	private String reference;
   	private List types;

 	public String getFormatted_address(){
		return this.formatted_address;
	}
	public void setFormatted_address(String formatted_address){
		this.formatted_address = formatted_address;
	}
 	public Geometry getGeometry(){
		return this.geometry;
	}
	public void setGeometry(Geometry geometry){
		this.geometry = geometry;
	}
 	public String getIcon(){
		return this.icon;
	}
	public void setIcon(String icon){
		this.icon = icon;
	}
 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
 	public Opening_hours getOpening_hours(){
		return this.opening_hours;
	}
	public void setOpening_hours(Opening_hours opening_hours){
		this.opening_hours = opening_hours;
	}
 	public List getPhotos(){
		return this.photos;
	}
	public void setPhotos(List photos){
		this.photos = photos;
	}
 	public String getRating(){
		return this.rating;
	}
	public void setRating(String rating){
		this.rating = rating;
	}
 	public String getReference(){
		return this.reference;
	}
	public void setReference(String reference){
		this.reference = reference;
	}
 	public List getTypes(){
		return this.types;
	}
	public void setTypes(List types){
		this.types = types;
	}
}
