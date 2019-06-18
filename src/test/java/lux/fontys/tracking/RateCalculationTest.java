package lux.fontys.tracking;

import lux.fontys.tracking.model.Location;
import lux.fontys.tracking.model.Rate;
import lux.fontys.tracking.model.RushRate;
import lux.fontys.tracking.model.Trip;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static junit.framework.TestCase.assertEquals;

public class RateCalculationTest {

    @Test
    public void calculate_distance_between_two_coordinates_test() {
        DistanceCalculator distanceCalculator = new DistanceCalculator();

        Location from = new Location();
        from.setLatitude(0.0);
        from.setLongitude(0.0);

        Location to = new Location();
        to.setLatitude(1.0);
        to.setLongitude(1.0);

        assertEquals("Kilometers",157, Math.round(distanceCalculator.distanceBetween(from, to)));
    }

    @Test
    public void calculate_total_distance_for_collection_of_locations_test() {
        Trip trip = new Trip();

        trip.addLocation(new Location(0.0, 0.0));
        trip.addLocation(new Location(1.0, 1.0));
        trip.addLocation(new Location(0.0, 0.0));

        trip.calculateDistance();

        assertEquals("Total distance should equal", 314, Math.round(trip.getDistanceTraveledKm()));
    }

    @Test
    public void calculate_price_for_trip_with_one_rate() {
        Rate rate = new Rate();
        rate.setCarRate(2.0);

        Trip trip = new Trip();
        trip.addLocation(new Location(0.0, 0.0));
        trip.addLocation(new Location(1.0, 1.0));

        trip.calculatePrice(rate);

        assertEquals("Total price should equal", 314, Math.round(trip.getTotalPrice()));
    }

    @Test
    public void calculate_price_for_trip_with_rush_rate() throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Rate rate = new Rate();
        rate.setCarRate(2.0);

        RushRate rushRate = new RushRate(2, "12:00", "13:00", 1.5);
        rushRate.setRate(rate);

        rate.addRushRate(rushRate);

        Trip trip = new Trip();
        Location location1 = new Location(0.0, 0.0);
        location1.setTrackedAt(formatter.parse("2019-06-17 10:00"));

        Location location2 = new Location(1.0, 1.0);
        location2.setTrackedAt(formatter.parse("2019-06-17 12:30"));

        Location location3 = new Location(0.0, 0.0);
        location3.setTrackedAt(formatter.parse("2019-06-17 13:00"));

        trip.addLocation(location1);
        trip.addLocation(location2);
        trip.addLocation(location3);

        trip.calculatePrice(rate);

        assertEquals("Total price should equal", 786, Math.round(trip.getTotalPrice()));
    }
}
