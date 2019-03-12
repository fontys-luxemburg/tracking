package lux.fontys.tracking.repository;

import lux.fontys.tracking.model.Tracker;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TrackerRepository implements CrudRepository<Tracker, Long> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Tracker> findById(Long id) {
        return Optional.of(em.find(Tracker.class, id));
    }

    @Override
    public List<Tracker> findAll() {
        return em.createQuery("select t from Tracker t", Tracker.class).getResultList();
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Tracker save(Tracker entity) {
        em.persist(entity);
        return entity;
    }
}
