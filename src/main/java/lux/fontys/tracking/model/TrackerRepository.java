package lux.fontys.tracking.model;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
public class TrackerRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional(Transactional.TxType.REQUIRED)
    public void create(Tracker tracker) {
        em.persist(tracker);
    }
}
