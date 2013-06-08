package urbanspoon;

public class UrbanSpoonCity {
	private String cityId;
	private String cityName;
	private String urbanSpoonCityURL;
	
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getUrbanSpoonCityURL() {
		return urbanSpoonCityURL;
	}
	public void setUrbanSpoonCityURL(String urbanSpoonCityURL) {
		this.urbanSpoonCityURL = urbanSpoonCityURL;
	}
	@Override
	public String toString() {
		return "UrbanSpoonCity [cityName=" + cityName + "]";
	}
}
