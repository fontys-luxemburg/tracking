package lux.fontys.tracking.repository;

import lux.fontys.tracking.model.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public abstract class CrudRepository<T extends BaseEntity, ID> {

    @PersistenceContext
    EntityManager entityManager;

    private Class<T> entityClass;

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public List<T> findAll() {
        return entityManager.createQuery("from " + entityClass.getName(), entityClass).getResultList();
    }

    public Optional<T> findById(ID id) {
        return Optional.of(entityManager.find(entityClass, id));
    }

    public void save(T entity) {
        try {
            entityManager.getTransaction().begin();
            if (entity.isNew()) {
                entityManager.persist(entity);
                entityManager.flush();
            } else {
                entityManager.merge(entity);
            }
        entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
        }
    }
}