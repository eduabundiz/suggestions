package com.gendra.suggestion.util;

public class DistanceUtils {

  public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
    final double R = 6371.0;

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
