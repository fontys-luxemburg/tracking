package lux.fontys.tracking.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "trips")
public class Trip extends BaseEntity {

    public Tracker getTracker() {
        return tracker;
    }

    public void setTracker(Tracker tracker) {
        this.tracker = tracker;
    }

    @ManyToOne
    @JoinColumn(name = "tracker_id", nullable = false)
    private Tracker tracker;

}
