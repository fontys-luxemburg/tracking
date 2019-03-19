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
    private Long latitude;

    @NotNull
    private Long longitude;

    @NotNull
    private Date trackedAt;

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    public Date getTrackedAt() {
        return trackedAt;
    }

    public void setTrackedAt(Date trackedAt) {
        this.trackedAt = trackedAt;
    }
}
