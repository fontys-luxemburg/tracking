package lux.fontys.tracking.controller;

import lux.fontys.tracking.dto.TrackerDto;
import lux.fontys.tracking.dto.TripDto;
import lux.fontys.tracking.facade.LocationFacade;
import lux.fontys.tracking.facade.TrackerFacade;
import lux.fontys.tracking.facade.TripFacade;
import lux.fontys.tracking.messaging.Listener;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class Application extends javax.ws.rs.core.Application {
	@Inject
	TrackerFacade tf;
	@Inject
	TripFacade trF;
	@Inject
	Listener listener;
	@PostConstruct
	private void onInit(){
		for (int i = 0; i < 10; i++) {
			TrackerDto td = new TrackerDto();
			tf.save(td);
		}
		TripDto tripDto = new TripDto();
		tripDto.setTrip_ID_Man((long)1111);
		tripDto.setTrackerId((long) 1);
		trF.save(tripDto);
		listener.MyListener();
	}
}
