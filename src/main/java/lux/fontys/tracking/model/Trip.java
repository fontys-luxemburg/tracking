package lux.fontys.tracking.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "trips")
public class Trip extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "trackerId", nullable = false)
    private Tracker tracker;

    private Date startDate;

    private Date endDate;

    private double distanceTraveledKm;

    public Tracker getTracker() {
        return tracker;
    }

    public void setTracker(Tracker tracker) {
        this.tracker = tracker;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getDistanceTraveledKm() {
        return distanceTraveledKm;
    }

    public void setDistanceTraveledKm(double distanceTraveledKm) {
        this.distanceTraveledKm = distanceTraveledKm;
    }

}
