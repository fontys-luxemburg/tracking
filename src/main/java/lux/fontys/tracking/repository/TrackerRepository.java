package lux.fontys.tracking.repository;

import lux.fontys.tracking.dto.TrackerDto;
import lux.fontys.tracking.model.Tracker;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Date;
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
        Query query = entityManager.createQuery("select t.id, t.trackerId, t.vehicleID, t.startDate, t.destroyedDate, t.createdAt, t.updatedAt from Tracker t where t.trackerId = :tracker_Id", Tracker.class);
        query.setParameter("tracker_Id", uuid);
        try{
            Tracker tracker = (Tracker) query.getSingleResult();
            return Optional.of(tracker);
        } catch (Exception e){
            return Optional.of(null);
        }
    }

    public List<Tracker> findByVehicleID(String vehicleID)
    {
        Query query = entityManager.createQuery("select t.id, t.trackerId, t.vehicleID, t.startDate, t.destroyedDate, t.createdAt, t.updatedAt from Tracker t where t.vehicleID = :vehicleID", Tracker.class);
        query.setParameter("vehicleID", vehicleID);
        try{
            return (List<Tracker>) query.getResultList();
        } catch (Exception e){
            return null;
        }
    }

    public List<Tracker> findByVehicleIDBetweenDates(String vehicleID, Date begin, Date end)
    {
        Query query = entityManager.createQuery("select t from Tracker t where t.vehicleID = :vehicleID and t.startDate between :beginDate and :endDate", Tracker.class);
        query.setParameter("vehicleID", vehicleID);
        query.setParameter("beginDate", begin);
        query.setParameter("endDate", end);
        try{
            return (List<Tracker>) query.getResultList();
        } catch (Exception e){
            return null;
        }
    }

    public Tracker findLastTrackerByVehicle(String vehicleId) {
        Query query = entityManager.createQuery("select t.id, t.trackerId, t.vehicleID, t.startDate, t.destroyedDate, t.createdAt, t.updatedAt from Tracker t where t.id = (select max(t2.id) from Tracker t2 where t2.vehicleID = :vehicleId)");
        query.setParameter("vehicleId", vehicleId);

        try{
            return (Tracker) query.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }
}
