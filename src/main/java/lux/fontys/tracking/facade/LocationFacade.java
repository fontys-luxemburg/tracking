package lux.fontys.tracking.facade;

import lux.fontys.tracking.dto.LocationDto;
import lux.fontys.tracking.dto.TrackerDto;
import lux.fontys.tracking.mapper.LocationMapper;
import lux.fontys.tracking.mapper.TrackerMapper;
import lux.fontys.tracking.messaging.model.TripMessage;
import lux.fontys.tracking.model.Location;
import lux.fontys.tracking.model.Trip;
import lux.fontys.tracking.repository.LocationRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class LocationFacade implements BaseFacade<LocationDto, Long> {

    @Inject
    TripFacade tripFacade;

    @Inject
    TrackerFacade trackerFacade;

    @Inject
    LocationRepository locationRepository;

    @Inject
    LocationMapper locationMapper;

    @Inject
    TrackerMapper trackerMapper;

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

    public void saveFromMessaging(TripMessage tripMessage) {
        //region Trip
        long tripID = tripMessage.getTripID();
        Trip trip = tripFacade.findByIdTrip(tripID).get();
        if(trip == null) {
            trip = new Trip();
            trip.setId(tripID);
            TrackerDto trackerDto = trackerFacade.findbyUuid(tripMessage.getTrackerID()).get();
            trip.setTracker(trackerMapper.trackerDtoToTracker(trackerDto));
            tripFacade.saveTrip(trip);
        }
        //endregion

        //region location
        Location location = new Location(trip, tripMessage.getLatitude(), tripMessage.getLongitude(), tripMessage.getTrackedAt());
        locationRepository.save(location);
        //endregion
    }
}