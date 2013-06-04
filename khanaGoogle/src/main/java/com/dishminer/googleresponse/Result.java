
package com.dishminer.googleresponse;

import com.domain.project.Address_components;
import com.domain.project.Photos;
import java.util.List;

public class Result{
   	private List<Address_components> address_components;
   	private String formatted_address;
   	private String formatted_phone_number;
   	private Geometry geometry;
   	private String icon;
   	private String id;
   	private String international_phone_number;
   	private String name;
   	private List<Photos> photos;
   	private String rating;
   	private String reference;
   	private List<String> reviews;
   	private List<String> types;
   	private String url;
   	private String utc_offset;
   	private String vicinity;
   	private String website;
        private String price_level;
        
        

 	public List<Address_components> getAddress_components(){
		return this.address_components;
	}
	public void setAddress_components(List<Address_components> address_components){
		this.address_components = address_components;
	}
 	public String getFormatted_address(){
		return this.formatted_address;
	}
	public void setFormatted_address(String formatted_address){
		this.formatted_address = formatted_address;
	}
 	public String getFormatted_phone_number(){
		return this.formatted_phone_number;
	}
	public void setFormatted_phone_number(String formatted_phone_number){
		this.formatted_phone_number = formatted_phone_number;
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
 	public String getInternational_phone_number(){
		return this.international_phone_number;
	}
	public void setInternational_phone_number(String international_phone_number){
		this.international_phone_number = international_phone_number;
	}
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
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
 	public List getReviews(){
		return this.reviews;
	}
	public void setReviews(List reviews){
		this.reviews = reviews;
	}
 	public List getTypes(){
		return this.types;
	}
	public void setTypes(List types){
		this.types = types;
	}
 	public String getUrl(){
		return this.url;
	}
	public void setUrl(String url){
		this.url = url;
	}
 	public String getUtc_offset(){
		return this.utc_offset;
	}
	public void setUtc_offset(String utc_offset){
		this.utc_offset = utc_offset;
	}
 	public String getVicinity(){
		return this.vicinity;
	}
	public void setVicinity(String vicinity){
		this.vicinity = vicinity;
	}
 	public String getWebsite(){
		return this.website;
	}
	public void setWebsite(String website){
		this.website = website;
	}

    /**
     * @return the price_level
     */
    public String getPrice_level() {
        return price_level;
    }

    /**
     * @param price_level the price_level to set
     */
    public void setPrice_level(String price_level) {
        this.price_level = price_level;
    }
}
