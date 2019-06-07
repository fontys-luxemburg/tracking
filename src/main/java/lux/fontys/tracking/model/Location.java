package lux.fontys.tracking.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "locations")
public class Location extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    @NotNull
    private double latitude;

    @NotNull
    private double longitude;

    @NotNull
    private Date trackedAt;

    public Location() {}

    public Location(Trip trip, double latitude, double longitude, Date trackedAt) {
        this.trip = trip;
        this.latitude = latitude;
        this.longitude = longitude;
        this.trackedAt = trackedAt;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Date getTrackedAt() {
        return trackedAt;
    }

    public void setTrackedAt(Date trackedAt) {
        this.trackedAt = trackedAt;
    }
}
