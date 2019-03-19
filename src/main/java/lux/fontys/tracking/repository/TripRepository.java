package lux.fontys.tracking.repository;

import lux.fontys.tracking.model.Trip;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class TripRepository extends CrudRepository<Trip, Long> {

    @PostConstruct
    public void init() {
        setEntityClass(Trip.class);
    }

    public List<Trip> findAllForTracker(Long trackerId) {
        Query query = entityManager.createQuery("select t from Trip t where t.tracker.id = :tracker_id");
        query.setParameter("tracker_id", trackerId);
        return query.getResultList();
    }
}
