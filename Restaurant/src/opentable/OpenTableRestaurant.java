package opentable;

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
	
	public int hashCode() {
		final int prime = 17;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
		result = prime * result
				+ ((restaurantName == null) ? 0 : restaurantName.hashCode());
		result = prime * result
				+ ((restaurantURL == null) ? 0 : restaurantURL.hashCode());
		result = prime * result
				+ ((streetName1 == null) ? 0 : streetName1.hashCode());
		result = prime * result
				+ ((streetName2 == null) ? 0 : streetName2.hashCode());
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
		OpenTableRestaurant other = (OpenTableRestaurant) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		if (restaurantName == null) {
			if (other.restaurantName != null)
				return false;
		} else if (!restaurantName.equals(other.restaurantName))
			return false;
		if (restaurantURL == null) {
			if (other.restaurantURL != null)
				return false;
		} else if (!restaurantURL.equals(other.restaurantURL))
			return false;
		if (streetName1 == null) {
			if (other.streetName1 != null)
				return false;
		} else if (!streetName1.equals(other.streetName1))
			return false;
		if (streetName2 == null) {
			if (other.streetName2 != null)
				return false;
		} else if (!streetName2.equals(other.streetName2))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}
	
	
	
}
	

