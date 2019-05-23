package lux.fontys.tracking.facade;

import lux.fontys.tracking.dto.TrackerDto;
import lux.fontys.tracking.dto.TripDto;
import lux.fontys.tracking.mapper.TripMapper;
import lux.fontys.tracking.model.Trip;
import lux.fontys.tracking.repository.TripRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TripFacade implements BaseFacade<TripDto, Long> {

    @Inject
    TripRepository tripRepository;

    @Inject
    TripMapper tripMapper;

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
            List<Trip> trackerTrips = tripRepository.findAllForTrackerBetweendates(tracker.getId(), begin, end);
            if(trackerTrips != null)
            {
                tracker.setTrips(trackerTrips);
            }
        }
        return trackers;
    }
}
