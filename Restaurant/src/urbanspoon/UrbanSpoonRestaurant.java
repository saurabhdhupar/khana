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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result
				+ ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
		result = prime * result
				+ ((restaurantId == null) ? 0 : restaurantId.hashCode());
		result = prime * result
				+ ((restaurantName == null) ? 0 : restaurantName.hashCode());
		result = prime * result
				+ ((streetName == null) ? 0 : streetName.hashCode());
		result = prime
				* result
				+ ((urbanSpoonRestaurantURL == null) ? 0
						: urbanSpoonRestaurantURL.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UrbanSpoonRestaurant other = (UrbanSpoonRestaurant) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		if (restaurantId == null) {
			if (other.restaurantId != null)
				return false;
		} else if (!restaurantId.equals(other.restaurantId))
			return false;
		if (restaurantName == null) {
			if (other.restaurantName != null)
				return false;
		} else if (!restaurantName.equals(other.restaurantName))
			return false;
		if (streetName == null) {
			if (other.streetName != null)
				return false;
		} else if (!streetName.equals(other.streetName))
			return false;
		if (urbanSpoonRestaurantURL == null) {
			if (other.urbanSpoonRestaurantURL != null)
				return false;
		} else if (!urbanSpoonRestaurantURL
				.equals(other.urbanSpoonRestaurantURL))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}
	
	
}
