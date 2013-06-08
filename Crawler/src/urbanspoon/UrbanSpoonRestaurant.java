package urbanspoon;

public class UrbanSpoonRestaurant {
	private String restaurantId;
	private String restaurantName;
	private String phoneNumber;
	private String city;
	private String streetName;
	private String region;
	private String zipCode;
	private String urbanSpoonRestaurantURL;
	
	public String getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getUrbanSpoonRestaurantURL() {
		return urbanSpoonRestaurantURL;
	}
	public void setUrbanSpoonRestaurantURL(String urbanSpoonRestaurantURL) {
		this.urbanSpoonRestaurantURL = urbanSpoonRestaurantURL;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	@Override
	public String toString() {
		return "UrbanSpoonRestaurant [restaurantName=" + restaurantName
				+ ", phoneNumber=" + phoneNumber + ", city=" + city
				+ ", streetName=" + streetName + "]";
	}
	
}
