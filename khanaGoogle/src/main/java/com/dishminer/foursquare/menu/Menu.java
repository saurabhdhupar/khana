
package com.dishminer.foursquare.menu;

import java.util.List;

public class Menu{
   	private Menus menus;
   	private String provider;

 	public Menus getMenus(){
		return this.menus;
	}
	public void setMenus(Menus menus){
		this.menus = menus;
	}
 	public String getProvider(){
		return this.provider;
	}
	public void setProvider(String provider){
		this.provider = provider;
	}
}
