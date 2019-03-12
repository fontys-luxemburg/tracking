package lux.fontys.tracking.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {

    Optional<T> findById(ID id);

    List<T> findAll();

    T save(T entity);
}
