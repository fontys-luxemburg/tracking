package lux.fontys.tracking.message;

import com.google.gson.Gson;
import lux.fontys.tracking.dto.TrackerDto;
import lux.fontys.tracking.dto.TripDto;
import lux.fontys.tracking.model.Tracker;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class Converter {
	public TripDto toTripDto (String json){
		Gson g = new Gson();
		MessaginTracker m = g.fromJson(json,MessaginTracker.class);
		TripDto td = new TripDto();

		return null;
	}
}
