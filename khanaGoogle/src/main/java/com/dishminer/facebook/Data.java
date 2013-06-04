
package com.dishminer.facebook;

import java.util.List;

public class Data{
   	private String category;
   	private List<Category_list> category_list;
   	private String id;
   	private Location location;
   	private String name;

 	public String getCategory(){
		return this.category;
	}
	public void setCategory(String category){
		this.category = category;
	}
 	public List<Category_list> getCategory_list(){
		return this.category_list;
	}
	public void setCategory_list(List<Category_list> category_list){
		this.category_list = category_list;
	}
 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
 	public Location getLocation(){
		return this.location;
	}
	public void setLocation(Location location){
		this.location = location;
	}
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
}
