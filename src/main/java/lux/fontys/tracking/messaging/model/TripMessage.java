package lux.fontys.tracking.messaging.model;

import java.util.Date;
import java.util.UUID;

public class TripMessage {

    UUID trackerID;
    Long tripID;
    double longitude;
    double latitude;
    Date trackedAt;

    public UUID getTrackerID() {
        return trackerID;
    }

    public void setTrackerID(UUID trackerID) {
        this.trackerID = trackerID;
    }

    public Long getTripID() {
        return tripID;
    }

    public void setTripID(Long tripID) {
        this.tripID = tripID;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Date getTrackedAt() {
        return trackedAt;
    }

    public void setTrackedAt(Date trackedAt) {
        this.trackedAt = trackedAt;
    }
}
