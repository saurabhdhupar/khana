package com.ebay.app.raptor.controller;

public class KGreatPoints {
	
	private static Double EARTH_RADIUS = 6371.00; // Radius in Kilometers default
	
	private static double findDistance(GeoCodeDO point1, GeoCodeDO point2) {
		double x = (point2.getLongitude() - point1.getLongitude()) *
		Math.cos((point1.getLatitude() + point2.getLatitude())/2);
		double y = point2.getLatitude() - point1.getLatitude();
		return Math.sqrt(x * x + y * y) * EARTH_RADIUS;
	}
	
	public static void main(String args[]) {
		System.out.println(
				findDistance(new GeoCodeDO(-122.05025637738, 37.388455908915),
							 new GeoCodeDO(-122.03045437738, 37.388454908915)));
	}

}
