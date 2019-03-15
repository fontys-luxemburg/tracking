package lux.fontys.tracking.repository;

import lux.fontys.tracking.model.Location;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class LocationRepository extends CrudRepository<Location, Long> {

    @PostConstruct
    private void init() {
        setEntityClass(Location.class);
    }

    public List<Location> findAllFor(Long tripId) {
        Query query = entityManager.createQuery("select l from Location l where l.trip.id = :trip_id", Location.class);
        query.setParameter("trip_id", tripId);
        return query.getResultList();
    }
}
