package lux.fontys.tracking.repository;

import lux.fontys.tracking.model.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
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
		T t = null;//Optional<T> t;
		try {
			t = entityManager.find(entityClass, id);
		} catch (Exception e) {

			e.printStackTrace();
		}
		if (t == null) return Optional.empty();
		else return Optional.of(t);
	}
	@Transactional
	public void save(T entity) {
		try {
			if (entity.isNew()) {
				entityManager.persist(entity);
				entityManager.getTransaction().commit();
			} else {
				entityManager.merge(entity);
				entityManager.getTransaction().commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}