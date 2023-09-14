package com.gendra.suggestion.util;

public class DistanceUtils {

    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // Radio de la Tierra en kilómetros
        final double R = 6371.0; // Puedes usar 3958.8 para millas

        // Convierte las latitudes y longitudes de grados a radianes
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Diferencias entre latitudes y longitudes
        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;

        // Fórmula de Haversine
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Distancia en kilómetros
        double distance = R * c;

        return distance;
    }

}
