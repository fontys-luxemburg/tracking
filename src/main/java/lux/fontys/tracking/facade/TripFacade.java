package lux.fontys.tracking.facade;

import lux.fontys.tracking.DistanceCalculator;
import lux.fontys.tracking.dto.TrackerDto;
import lux.fontys.tracking.dto.TripDto;
import lux.fontys.tracking.mapper.TrackerMapper;
import lux.fontys.tracking.mapper.TripMapper;
import lux.fontys.tracking.model.Location;
import lux.fontys.tracking.model.Rate;
import lux.fontys.tracking.model.Trip;
import lux.fontys.tracking.repository.TripRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    public List<TripDto> getAllFor(UUID trackerId) {
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

    public void finishTrip(Trip trip) {
        try {
            trip.setEndDate(new Date());
            trip.calculateDistance();

            calculatePriceForTrip(trip);

            tripRepository.save(trip);
        }
        catch (Exception e) {
            throw e;
        }
    }

    public void calculatePriceForTrip(Trip trip) {
        Client client = ClientBuilder.newBuilder().build();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        WebTarget webTarget = client.target("http://178.62.217.247:9060/government/api/rates")
                .queryParam("date", formatter.format(trip.getStartDate()));

        Response response = webTarget.request().get();

        Rate rate = response.readEntity(Rate.class);

        trip.calculatePrice(rate);
    }

    public List<TrackerDto> GetTripBetweenDates(List<TrackerDto> trackers, Date begin, Date end) {
        for (TrackerDto tracker : trackers) {
            List<TripDto> trackerTrips = tripMapper.tripsToTripDtos(tripRepository.findAllForTrackerBetweendates(tracker.getId(), begin, end));
            tracker.setTrips(trackerTrips);
        }
        return trackers;
    }

    public Optional<Trip> findByIdTrip(Long id){
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
