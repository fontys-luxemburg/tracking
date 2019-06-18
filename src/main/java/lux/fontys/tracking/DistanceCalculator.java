package lux.fontys.tracking;

import lux.fontys.tracking.model.Location;

public class DistanceCalculator {
    private final double d2r = Math.PI / 180;

    public double distanceBetween(Location from, Location to) {
        double deltaLong = (to.getLongitude() - from.getLongitude()) * d2r;
        double deltaLat = (to.getLatitude() - from.getLatitude()) * d2r;

        double a = Math.pow(Math.sin(deltaLat / 2), 2)
                + Math.cos(from.getLatitude() * d2r)
                * Math.cos(to.getLatitude() * d2r)
                * Math.pow(Math.sin(deltaLong / 2), 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double d = 6367 * c;

        return d;
    }
}
