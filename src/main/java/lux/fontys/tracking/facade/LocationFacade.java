package lux.fontys.tracking.facade;

import lux.fontys.tracking.dto.LocationDto;
import lux.fontys.tracking.dto.TrackerDto;
import lux.fontys.tracking.dto.TripDto;
import lux.fontys.tracking.mapper.LocationMapper;
import lux.fontys.tracking.mapper.TripMapperImpl;
import lux.fontys.tracking.messaging.model.Trip_Message;
import lux.fontys.tracking.model.Location;
import lux.fontys.tracking.repository.LocationRepository;

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
    TrackerFacade trackerFacade;
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

    public void saveFromMessaging(Trip_Message tm){
        Optional<TrackerDto> trackerDto = trackerFacade.findByUUID(UUID.fromString(tm.getTrackerID()));
        if (!trackerDto.isPresent()){
            trackerDto = Optional.of(new TrackerDto());
            trackerDto.get().setTrackerId(UUID.fromString(tm.getTrackerID()));
            trackerDto = Optional.ofNullable(trackerFacade.save(trackerDto.get()));
        }
        Optional<TripDto> tripDto = tripFacade.findById(Long.valueOf(tm.getTripID()));
        if(!tripDto.isPresent()){
            TripDto td = new TripDto();
            td.setTrackerId(trackerDto.get().getId());
            tripFacade.save(td);
        }
        Location location = new Location();
        location.setLatitude(Double.valueOf(tm.getLatitude()));
        location.setLongitude(Double.valueOf(tm.getLongitude()));
        //location.setTrackedAt(tm.getTrackedAt());
        location.setTrip( tripMapper.tripDtoToTrip(tripDto.get()));
        locationRepository.save(location);
    }
}
