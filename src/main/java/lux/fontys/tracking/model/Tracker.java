package lux.fontys.tracking.model;

import lombok.Getter;
import lombok.Setter;

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
    private String name;

    @OneToMany(mappedBy = "tracker", fetch = FetchType.LAZY)
    private List<Trip> trips = new ArrayList<>();

    public Tracker() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
