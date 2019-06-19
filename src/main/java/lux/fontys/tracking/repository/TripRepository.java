package lux.fontys.tracking.repository;

import lux.fontys.tracking.model.Trip;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
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
    public List<Trip> findAllForTracker(UUID trackerId) {
        Query query = entityManager.createQuery("select t from Trip t where t.tracker.trackerId = :tracker_id");
        query.setParameter("tracker_id", trackerId);
        return query.getResultList();
    }
    public List<Trip> findAllTripsForTrackerFromDate(UUID trackerId, Date startDate,Date endDate) {
        Query query = entityManager.createQuery("select t from Trip t where (t.tracker.trackerId = :tracker_id and t.createdAt >= :startDate" +
                " and t.endDate = null) or (t.tracker.trackerId = :tracker_id and t.createdAt>= :startDate and t.endDate <= :endDate )");
        query.setParameter("tracker_id", trackerId);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }

    public List<Trip> findAllForTrackerBetweendates(Long trackerId, Date begin, Date end) {
        Query query = entityManager.createQuery("select t from Trip t where t.tracker.id = :tracker_id and t.startDate between :beginDate and :endDate");
        query.setParameter("tracker_id", trackerId);
        query.setParameter("beginDate", begin);
        query.setParameter("endDate", end);
        return query.getResultList();
    }

    public long getNewID() {
        try {
            Query q = entityManager.createQuery("select max(t.id) from Trip t");
            return (long)q.getSingleResult();
        }
        catch (Exception e) {
            throw e;
        }
    }
    public boolean tripExists(Long id){
        Query query = entityManager.createQuery("select t from Trip t where t.id = :id");
        query.setParameter("id", id);
        return query.getResultList().size()==1;

    }
}
