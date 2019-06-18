package lux.fontys.tracking;

import lux.fontys.tracking.facade.LocationFacade;
import lux.fontys.tracking.facade.TrackerFacade;
import lux.fontys.tracking.facade.TripFacade;
import lux.fontys.tracking.model.Location;
import lux.fontys.tracking.model.Tracker;
import lux.fontys.tracking.model.Trip;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Startup
@Singleton
public class Seeds {

    @Inject
    private TrackerFacade trackerFacade;

    @Inject
    private TripFacade tripFacade;

    private Calendar cal;

    @PostConstruct
    private void init() {
        System.out.println("STARTED SEEDS");

        cal = Calendar.getInstance();

        Date today = new Date();

        //region Create trackers
        Date trackerDestroyed = GetDate(CalendarEnum.Days,1,today);
        
        Tracker tracker1 = new Tracker();
        tracker1.setVehicleID("AB1234");
        tracker1.setTrackerId(UUID.randomUUID());
        tracker1.setStartDate(today);
        tracker1.setDestroyedDate(trackerDestroyed);

        Tracker tracker2 = new Tracker();
        tracker2.setVehicleID("AB1234");
        tracker2.setTrackerId(UUID.randomUUID());
        tracker2.setStartDate(trackerDestroyed);
        trackerDestroyed = GetDate(CalendarEnum.Days, 2, trackerDestroyed);
        tracker2.setDestroyedDate(trackerDestroyed);

        Tracker tracker3 = new Tracker();
        tracker3.setVehicleID("AB1234");
        tracker3.setTrackerId(UUID.randomUUID());
        tracker3.setStartDate(trackerDestroyed);

        trackerFacade.saveTracker(tracker1);
        trackerFacade.saveTracker(tracker2);
        trackerFacade.saveTracker(tracker3);
        //endregion

        //region Create Trips
        Date newBeginDateTrips = GenerateTrips(3, tracker1, today);
        newBeginDateTrips = GetDate(CalendarEnum.Days, 1, newBeginDateTrips);
        newBeginDateTrips = GenerateTrips(2, tracker2, newBeginDateTrips);
        newBeginDateTrips = GetDate(CalendarEnum.Days, 2, newBeginDateTrips);
        GenerateTrips(1, tracker3, newBeginDateTrips);
        //endregion
    }

    private Date GetDate(CalendarEnum modifier, int amount, Date date) {
        cal.setTime(date);
        int mod = 0;
        
        switch (modifier) {
            case Days:
                mod = Calendar.DATE;
                break;
            case Hours:
                mod = Calendar.HOUR_OF_DAY;
                break;
            case Minutes:
                mod = Calendar.MINUTE;
                break;
            default:
                mod = Calendar.DATE;
                break;
        }

        cal.add(mod, amount);
        return cal.getTime();
    }

    private Date GenerateTrips(int amountOfTrips, Tracker tracker, Date begin) {
        Date end = new Date();
        for (int i = 0; i < amountOfTrips; i++) {
            if(i != 0) {
                begin = GetDate(CalendarEnum.Hours, 5, end);
            }
            end = GetDate(CalendarEnum.Minutes, 30, begin);

            Trip trip = new Trip();
            trip.setTracker(tracker);
            trip.setStartDate(begin);
            trip.setEndDate(end);

            Random r = new Random();
            double randomValue = 1 + (30 - 1) * r.nextDouble();

            trip.setDistanceTraveledKm(randomValue);
            tripFacade.saveTrip(trip);
        }
        return end;
    }
}