package OpenTable;

public class OpenTableRestaurant {
	private String restaurantName;
	private String restaurantURL;
	private String streetName1;
	private String streetName2;
	private String city;
	private String zipCode;
	private String region;
	
	
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getRestaurantURL() {
		return restaurantURL;
	}
	public void setRestaurantURL(String restaurantURL) {
		this.restaurantURL = restaurantURL;
	}
	public String getStreetName1() {
		return streetName1;
	}
	public void setStreetName1(String streetName1) {
		this.streetName1 = streetName1;
	}
	public String getStreetName2() {
		return streetName2;
	}
	public void setStreetName2(String streetName2) {
		this.streetName2 = streetName2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	@Override
	public String toString() {
		return "OpenTableRestaurant [restaurantName=" + restaurantName
				+ ", restaurantURL=" + restaurantURL + ", streetName1="
				+ streetName1 + ", streetName2=" + streetName2 + ", city="
				+ city + ", zipCode=" + zipCode + ", region=" + region + "]";
	}
	
}
	

