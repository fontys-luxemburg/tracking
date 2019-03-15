package lux.fontys.tracking.repository;

import lux.fontys.tracking.model.Trip;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class TripRepository implements CrudRepository<Trip, Long> {

    @PersistenceContext
    EntityManager em;

    @Override
    public Optional<Trip> findById(Long id) {
        return Optional.of(em.find(Trip.class, id));
    }

    public List<Trip> findAllForTracker(Long trackerId) {
        Query query = em.createQuery("select t from Trip t where t.tracker.id = :tracker_id");
        query.setParameter("tracker_id", trackerId);
        return query.getResultList();
    }

    @Override
    public List<Trip> findAll() {
        return em.createQuery("select t from Trip t", Trip.class).getResultList();
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Trip save(Trip entity) {
        em.persist(entity);
        return entity;
    }
}
