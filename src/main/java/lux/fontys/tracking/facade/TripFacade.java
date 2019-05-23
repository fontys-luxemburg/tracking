package lux.fontys.tracking.facade;

import lux.fontys.tracking.dto.TripDto;
import lux.fontys.tracking.mapper.TripMapper;
import lux.fontys.tracking.model.Tracker;
import lux.fontys.tracking.model.Trip;
import lux.fontys.tracking.repository.TrackerRepository;
import lux.fontys.tracking.repository.TripRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.swing.text.html.Option;
import java.util.ArrayList;
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
}
