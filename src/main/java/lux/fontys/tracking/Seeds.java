package lux.fontys.tracking;

import lux.fontys.tracking.dto.TrackerDto;
import lux.fontys.tracking.facade.TrackerFacade;
import lux.fontys.tracking.model.Tracker;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Startup
@Singleton
public class Seeds {

    @Inject
    TrackerFacade trackerFacade;

    @PostConstruct
    private void init() {
        System.out.println("STARTED SEEDS");

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -10);

        //Create trackers
        Tracker tracker1 = new Tracker();
        tracker1.setVehicleID("AB1234");
        tracker1.setTrackerId(UUID.randomUUID());
        tracker1.setDestroyedDate(cal.getTime());

        Tracker tracker2 = new Tracker();
        tracker2.setVehicleID("AB1234");
        tracker2.setTrackerId(UUID.randomUUID());
        tracker2.setDestroyedDate(cal.getTime());

        Tracker tracker3 = new Tracker();
        tracker3.setVehicleID("AB1234");
        tracker3.setTrackerId(UUID.randomUUID());

        trackerFacade.saveTracker(tracker1);
        trackerFacade.saveTracker(tracker2);
        trackerFacade.saveTracker(tracker3);
    }
}
