package lux.fontys.tracking.repository;

import lux.fontys.tracking.model.Tracker;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class TrackerRepository extends CrudRepository<Tracker, Long> {

    @PostConstruct
    public void init() {
        setEntityClass(Tracker.class);
    }


    public Optional<Tracker> findByUuid(UUID uuid) {
        Query query = entityManager.createQuery("select t from Tracker t where t.trackerId = :tracker_Id", Tracker.class);
        query.setParameter("tracker_Id", uuid);
        try{
            Tracker tracker = (Tracker) query.getSingleResult();
            return Optional.of(tracker);
        } catch (Exception e){
            return Optional.of(null);
        }
    }
}
