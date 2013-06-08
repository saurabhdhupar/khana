package TripAdvisor;

public class TripAdvisorRestaurant {
	private String tripAdvisorLink;
	
	public String getTripAdvisorLink() {
		return tripAdvisorLink;
	}

	public void setTripAdvisorLink(String tripAdvisorLink) {
		this.tripAdvisorLink = tripAdvisorLink;
	}

	@Override
	public String toString() {
		return "TripAdvisorRestaurant [tripAdvisorLink=" + tripAdvisorLink
				+ "]";
	}	
}
