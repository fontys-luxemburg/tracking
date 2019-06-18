package lux.fontys.tracking.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "locations")
public class Location extends BaseEntity {

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    private double latitude;

    private double longitude;

    private Date trackedAt;

    public Location() {}

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
