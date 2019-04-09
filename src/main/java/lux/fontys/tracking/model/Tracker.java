package lux.fontys.tracking.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "trackers")
public class Tracker extends BaseEntity {

    @Transient
    @OneToMany(mappedBy = "tracker", fetch = FetchType.LAZY)
    private List<Trip> trips = new ArrayList<>();

    public Tracker() {
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
}
