package lux.fontys.tracking.repository;

import lux.fontys.tracking.model.Trip;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

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

    public List<Trip> findAllForTrackerBetweendates(Long trackerId, Date begin, Date end) {
        Query query = entityManager.createQuery("select t from Trip t where t.tracker.id = :tracker_id and t.startDate between :begin and :end");
        query.setParameter("tracker_id", trackerId);
        query.setParameter("begin", begin);
        query.setParameter("end", end);
        return query.getResultList();
    }}
