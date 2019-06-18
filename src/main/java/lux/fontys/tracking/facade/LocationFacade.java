package lux.fontys.tracking.facade;

import lux.fontys.tracking.dto.LocationDto;
import lux.fontys.tracking.mapper.LocationMapper;
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
    LocationRepository locationRepository;

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

    public void saveFromMessaging(TripMessage tripMessage) {
        System.out.println("saving  " + tripMessage);
        //region Trip
        long tripID = tripMessage.getTripID();
        Trip trip = tripFacade.findByIdTrip(tripID).get();
        //endregion

        //region location
        Location location = new Location();
        location.setLatitude(tripMessage.getLatitude());
        location.setLongitude(tripMessage.getLongitude());
        location.setTrackedAt(tripMessage.getTrackedAt());
        location.setTrip(trip);
        locationRepository.save(location);
        //endregion
    }
}