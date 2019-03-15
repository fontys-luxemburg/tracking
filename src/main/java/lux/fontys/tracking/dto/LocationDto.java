package lux.fontys.tracking.dto;

import java.util.Date;

public class LocationDto extends BaseDto {

    private Long tripId;
    private Long latitude;
    private Long longitude;
    private Date trackedAt;

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
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
