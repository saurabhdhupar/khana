
package foursquare;

import java.util.List;

public class Items{
   	private String description;
   	private Entries entries;
   	private String menuId;
   	private String name;

 	public String getDescription(){
		return this.description;
	}
	public void setDescription(String description){
		this.description = description;
	}
 	public Entries getEntries(){
		return this.entries;
	}
	public void setEntries(Entries entries){
		this.entries = entries;
	}
 	public String getMenuId(){
		return this.menuId;
	}
	public void setMenuId(String menuId){
		this.menuId = menuId;
	}
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
}
