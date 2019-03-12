package lux.fontys.tracking.facade;

import java.util.List;
import java.util.Optional;

public interface BaseFacade <T, ID> {

	Optional<T> findById(ID id);

	List<T> findAll();

	T save(T entity);
}
