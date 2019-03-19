package lux.fontys.tracking.repository;

import lux.fontys.tracking.dto.TrackerDto;
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
    public Optional<Tracker>findByUUID(UUID id){
        List<Tracker> restult = entityManager.createQuery("SELECT t FROM Tracker t WHERE t.trackerId LIKE :tid", Tracker.class).setParameter("tid", id.toString()).getResultList();
        if(restult.size() == 0) return Optional.empty();
        return Optional.of(restult.get(1));
    }

}
