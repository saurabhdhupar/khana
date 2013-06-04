/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dishminer.resturantsUtils;

/**
 *
 * @author agupta6
 */
public enum APIIDEnum implements EnumExtender{
    
    
  
    
    
         GOOGLE(1,"GOOGLE","GOOGLE"), 
	FACEBOOK(2,"FACEBOOK","FACEBOOK"),
	FOURSQUARE(3,"FOURSQUARE" ,"FOURSQUARE"),
	FACTUAL(4,"FACTUAL" ,"FACTUAL"),
        URBANSPOON(5,"URBANSPOON" ,"URBANSPOON"),
        TWITTER(6,"TWITTER","TWITTER"),
        WEBSITE(7,"WEBSITE","WEBSITE"),
        ;

	private int id;
	private String name;
	private String value;
	private int size;
	
	private static ReverseEnumLookup<APIIDEnum> enumLookup = new ReverseEnumLookup<APIIDEnum>(
			APIIDEnum.class);

	/**
	 * @param id
	 * @param name
	 */
	private APIIDEnum(int id ,String value, String name) {
		this.id = id;
		this.name = name;
		this.value= value;
		
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * @return the name
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setSize(int value) {
		this.size = value;
	}

	
	
	public static APIIDEnum getById(int id) {
		return enumLookup.getById(id);
	}

	
	public static APIIDEnum getByName(String name) {
		return enumLookup.getByName(name);
	}
	
	
    
}
