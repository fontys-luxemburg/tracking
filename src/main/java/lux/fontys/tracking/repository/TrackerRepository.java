package lux.fontys.tracking.repository;

import lux.fontys.tracking.dto.TrackerDto;
import lux.fontys.tracking.model.Tracker;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
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
        Query query = entityManager.createQuery("select t from Tracker t where t.trackerId = :tracker_Id");
        query.setParameter("tracker_Id", uuid);
        query.setMaxResults(1);
        try{
            Tracker tracker = (Tracker) query.getResultList().get(0);
            return Optional.of(tracker);
        } catch (Exception e){
            return Optional.of(null);
        }
    }

    public List<Tracker> findAvailableTracker() {
        Query query = entityManager.createQuery("SELECT t FROM Tracker t WHERE t.id in (SELECT ti.tracker FROM Trip ti WHERE ti.id in (SELECT MAX(ti2.id) FROM Trip ti2 WHERE ti2.tracker = ti.tracker AND ti2.endDate IS NOT NULL)) AND t.destroyedDate IS null");
        try{
            return (List<Tracker>) query.getResultList();
        } catch (Exception e){
            return null;
        }
    }

    public List<Tracker> findByVehicleID(String vehicleID)
    {
        Query query = entityManager.createQuery("select t from Tracker t where t.vehicleID = :vehicleID", Tracker.class);
        query.setParameter("vehicleID", vehicleID);
        try{
            return (List<Tracker>) query.getResultList();
        } catch (Exception e){
            return null;
        }
    }

    public List<Tracker> findByVehicleIDBetweenDates(String vehicleID, Date begin, Date end)
    {
        Query query = entityManager.createQuery("select t from Tracker t where t.vehicleID = :vehicleID and (t.startDate between :beginDate and :endDate) or (t.startDate < :beginDate and t.destroyedDate = null )", Tracker.class);
        query.setParameter("vehicleID", vehicleID);
        query.setParameter("beginDate", begin, TemporalType.DATE);
        query.setParameter("endDate", end,TemporalType.DATE);
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
