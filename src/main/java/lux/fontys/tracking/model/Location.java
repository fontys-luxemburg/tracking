package lux.fontys.tracking.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "locations")
public class Location extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    @NotNull
    private double latitude;

    @NotNull
    private double longitude;

    private Date trackedAt;

    public Location() {}

    public Location(@NotNull double latitude, @NotNull double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
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
