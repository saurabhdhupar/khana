package Googleplus;

public class GooglePlusRestaurant {
	private String restaurantName;
	private String city;
	private String region;
	private String country;
	private String streetAddress;
	private String googlePlusURL;
	
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getGooglePlusURL() {
		return googlePlusURL;
	}
	public void setGooglePlusURL(String googlePlusURL) {
		this.googlePlusURL = googlePlusURL;
	}
	@Override
	public String toString() {
		return "GooglePlusRestaurant [restaurantName=" + restaurantName
				+ ", city=" + city + ", region=" + region + ", country="
				+ country + ", streetAddress=" + streetAddress
				+ ", googlePlusURL=" + googlePlusURL + "]";
	}
	
	
}
