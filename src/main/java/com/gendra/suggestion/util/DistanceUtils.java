package com.gendra.suggestion.util;

/**
 * Utility class for calculating distances between two geographical coordinates using the Haversine
 * formula.
 */
public class DistanceUtils {

  /**
   * Calculates the distance between two sets of latitude and longitude coordinates using the
   * Haversine formula.
   *
   * @param lat1 Latitude of the first point in degrees.
   * @param lon1 Longitude of the first point in degrees.
   * @param lat2 Latitude of the second point in degrees.
   * @param lon2 Longitude of the second point in degrees.
   * @return The distance in kilometers between the two coordinates.
   */
  public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
    final double R = 6371.0; // Earth's radius in kilometers

    double lat1Rad = Math.toRadians(lat1);
    double lon1Rad = Math.toRadians(lon1);
    double lat2Rad = Math.toRadians(lat2);
    double lon2Rad = Math.toRadians(lon2);

    double lat = lat2Rad - lat1Rad;
    double lon = lon2Rad - lon1Rad;
    double a =
        Math.sin(lat / 2) * Math.sin(lat / 2)
            + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.sin(lon / 2) * Math.sin(lon / 2);

    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    double distance = R * c;
    return distance;
  }
}
