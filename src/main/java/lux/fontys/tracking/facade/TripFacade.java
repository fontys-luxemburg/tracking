package lux.fontys.tracking.facade;

import lux.fontys.tracking.dto.TrackerDto;
import lux.fontys.tracking.dto.TripDto;
import lux.fontys.tracking.mapper.TrackerMapper;
import lux.fontys.tracking.mapper.TripMapper;
import lux.fontys.tracking.model.Trip;
import lux.fontys.tracking.repository.TripRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class TripFacade implements BaseFacade<TripDto, Long> {

    @Inject
    TripRepository tripRepository;

    @Inject
    TripMapper tripMapper;

    @Inject
    TrackerFacade trackerFacade;

    @Inject
    TrackerMapper trackerMapper;

    public List<TripDto> getAllFor(Long trackerId) {
        return tripMapper.tripsToTripDtos(tripRepository.findAllForTracker(trackerId));
    }

    @Override
    public Optional<TripDto> findById(Long id) {
        return Optional.of(tripMapper.tripToTripDto(tripRepository.findById(id).orElse(null)));
    }

    @Override
    public List<TripDto> findAll() {
        return tripMapper.tripsToTripDtos(tripRepository.findAll());
    }

    @Override
    public TripDto save(TripDto entity) {
        Trip trip = tripMapper.tripDtoToTrip(entity);
        tripRepository.save(trip);
        return entity;
    }

    public void saveTrip(Trip trip) {
        tripRepository.save(trip);
    }

    public List<TrackerDto> GetTripBetweenDates(List<TrackerDto> trackers, Date begin, Date end) {
        for (TrackerDto tracker : trackers) {
            List<TripDto> trackerTrips = tripMapper.tripsToTripDtos(tripRepository.findAllForTrackerBetweendates(tracker.getId(), begin, end));
            tracker.setTrips(trackerTrips);
        }
        return trackers;
    }

    Optional<Trip> findByIdTrip(Long id){
        Optional<Trip> trip = tripRepository.findById(id);
        if(trip.isPresent()) {
            return trip;
        }
        return Optional.empty();
    }

    public long getNewID(UUID trackerID) {
        try {
            Trip trip = new Trip();
            trip.setStartDate(new Date());
            TrackerDto trackerDto = trackerFacade.findbyUuid(trackerID).get();
            trip.setTracker(trackerMapper.trackerDtoToTracker(trackerDto));
            saveTrip(trip);
            return tripRepository.getNewID();
        }
        catch (Exception e) {
            throw e;
        }
    }
}
