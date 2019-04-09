package lux.fontys.tracking.model;

import javax.persistence.*;

@Entity
@Table(name = "trips")
public class Trip extends BaseEntity {
    @Column(name="trip_ID_Man")
    private Long trip_ID_Man;

    @ManyToOne
    @JoinColumn(name = "tracker_id", nullable = false)
    private Tracker tracker;
    public Long getTrip_ID_Man() {
        return trip_ID_Man;
    }
    public void setTrip_ID_Man(Long manualId) {
        this.trip_ID_Man = manualId;
    }
    public Tracker getTracker() {
        return tracker;
    }
    public void setTracker(Tracker tracker) {
        this.tracker = tracker;
    }

}
