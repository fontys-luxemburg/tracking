package lux.fontys.tracking;

import lux.fontys.tracking.dto.TrackerDto;
import lux.fontys.tracking.facade.TrackerFacade;

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
        TrackerDto trackerDto1 = new TrackerDto();
        trackerDto1.setVehicle_registrationID("AB1234");
        trackerDto1.setTrackerId(UUID.randomUUID());
        trackerDto1.setDestroyedDate(cal.getTime());

        TrackerDto trackerDto2 = new TrackerDto();
        trackerDto2.setVehicle_registrationID("AB1234");
        trackerDto2.setTrackerId(UUID.randomUUID());
        trackerDto2.setDestroyedDate(cal.getTime());

        TrackerDto trackerDto3 = new TrackerDto();
        trackerDto3.setVehicle_registrationID("AB1234");
        trackerDto3.setTrackerId(UUID.randomUUID());

        trackerFacade.save(trackerDto1);
        trackerFacade.save(trackerDto2);
        trackerFacade.save(trackerDto3);
    }
}
