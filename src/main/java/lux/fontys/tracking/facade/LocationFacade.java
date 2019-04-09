package lux.fontys.tracking.facade;

import lux.fontys.tracking.dto.LocationDto;
import lux.fontys.tracking.dto.TrackerDto;
import lux.fontys.tracking.dto.TripDto;
import lux.fontys.tracking.mapper.LocationMapper;
import lux.fontys.tracking.mapper.TripMapperImpl;
import lux.fontys.tracking.messaging.model.Trip_Message;
import lux.fontys.tracking.model.Location;
import lux.fontys.tracking.model.Trip;
import lux.fontys.tracking.repository.LocationRepository;
import lux.fontys.tracking.repository.TrackerRepository;
import lux.fontys.tracking.repository.TripRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class LocationFacade implements BaseFacade<LocationDto, Long> {

	@Inject
	LocationRepository locationRepository;
	@Inject
	TripRepository tripRepository;
	@Inject
	TrackerRepository trackerRepository;
	@Inject
	TripFacade tripFacade;
	@Inject
	TripMapperImpl tripMapper;
	@Inject
	LocationMapper locationMapper;

	@Override
	public Optional<LocationDto> findById(Long id) {
		Optional<Location> location = locationRepository.findById(id);

		return location.map(i -> locationMapper.locationToLocationDto(i));
	}

	@Override
	public List<LocationDto> findAll() {
		return locationMapper.locationsToLocationDtos(locationRepository.findAll());
	}

	@Override
	public LocationDto save(LocationDto entity) {
		locationRepository.save(locationMapper.locationDtoToLocation(entity));
		return entity;
	}

	public List<LocationDto> getAllFor(Long tripId) {
		return locationMapper.locationsToLocationDtos(locationRepository.findAllFor(tripId));
	}

	public void saveFromMessaging(Trip_Message tm) {
		Trip t = new Trip();
		try {
			t = tripFacade.findByIdTrip(tm.getTripID()).get();
		} catch (Exception e) {
			t.setTrip_ID_Man(tm.getTripID());
			tripFacade.save(tripMapper.tripToTripDto(t));
		}
		t = tripFacade.findByIdTrip(tm.getTripID()).get();
		t.setTracker(trackerRepository.findById(tm.getTripID()).get());
		t.setTrip_ID_Man(tm.getTripID());
		tripRepository.save(t);
		Location location = new Location();
		location.setLatitude(tm.getLatitude());
		location.setLongitude(tm.getLongitude());
		location.setTrackedAt(tm.getTrackedAt());
		location.setTrip(t);
		locationRepository.save(location);
	}
}
