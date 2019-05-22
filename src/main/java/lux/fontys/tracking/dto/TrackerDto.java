package lux.fontys.tracking.dto;

import lux.fontys.tracking.model.Trip;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TrackerDto extends BaseDto {

    private UUID trackerId;

    private Date destroyedDate;

    private List<Trip> trips = new ArrayList<>();

    private String vehicleID;

    private Date createdAt;

    public TrackerDto() {
    }

    public TrackerDto(UUID trackerId) {
        this.trackerId = trackerId;
    }

    public UUID getTrackerId() {
        return trackerId;
    }

    public void setTrackerId(UUID trackerId) {
        this.trackerId = trackerId;
    }

    public Date getDestroyedDate() {
        return destroyedDate;
    }

    public void setDestroyedDate(Date destroyedDate) {
        this.destroyedDate = destroyedDate;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
