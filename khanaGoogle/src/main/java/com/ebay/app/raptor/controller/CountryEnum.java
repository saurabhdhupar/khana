package com.ebay.app.raptor.controller;

public enum CountryEnum {
	
	UNITED_STATES("us"),
	NONE("none")
	;

	
	private CountryEnum(String country) {
		this.country = country;
	}
	
	public String getCountry() {
		return country;
	}
	
	private String country;



	public static CountryEnum getEnum(String country) {
		CountryEnum[] countries =  CountryEnum.values();
		for (CountryEnum country_enum : countries) {
			if(country_enum.getCountry().equals(country)) {
				return country_enum;
			}
		}
		return CountryEnum.NONE;
	}
}
