package lux.fontys.tracking.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "trackers")
public class Tracker extends BaseEntity {

    @Column(unique = true)
    @NotNull
    private UUID trackerId;

    @OneToMany(mappedBy = "tracker", fetch = FetchType.LAZY)
    private List<Trip> trips = new ArrayList<>();

    @NotNull
    private String vehicle_registrationID;

    public Tracker() {
    }

    public UUID getTrackerId() {
        return trackerId;
    }

    public void setTrackerId(UUID trackerId) {
        this.trackerId = trackerId;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public String getVehicle_registrationID() {
        return vehicle_registrationID;
    }

    public void setVehicle_registrationID(String vehicle_registrationID) {
        this.vehicle_registrationID = vehicle_registrationID;
    }
}
