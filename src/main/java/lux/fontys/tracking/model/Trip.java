package lux.fontys.tracking.model;

import lux.fontys.tracking.DistanceCalculator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trips")
public class Trip extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "trackerId", nullable = false)
    private Tracker tracker;

    @OneToMany(mappedBy = "trip", fetch = FetchType.EAGER)
    private List<Location> locations = new ArrayList<>();

    private Date startDate;

    private Date endDate;

    private double distanceTraveledKm;

    private double totalPrice;

    @Transient
    private DistanceCalculator calculator = new DistanceCalculator();

    public Tracker getTracker() {
        return tracker;
    }

    public void setTracker(Tracker tracker) {
        this.tracker = tracker;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getDistanceTraveledKm() {
        return distanceTraveledKm;
    }

    public void setDistanceTraveledKm(double distanceTraveledKm) {
        this.distanceTraveledKm = distanceTraveledKm;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public void addLocation(Location location) {
        location.setTrip(this);
        locations.add(location);
    }

    public void calculateDistance() {

        double totalDistance = 0;

        for (int i = 0; i < locations.size() - 1; i++) {
            Location first = locations.get(i);
            Location second = locations.get(i + 1);

            totalDistance += calculator.distanceBetween(first, second);
        }

        setDistanceTraveledKm(totalDistance);
    }
    public void setTotalPrice(double price){
        totalPrice=price;
    }
    public double calculatePrice(Rate rate) {
        double price = 0.0;

        for (int i = 0; i < locations.size() - 1; i++) {
            Location first = locations.get(i);
            Location second = locations.get(i + 1);

            double distance = calculator.distanceBetween(first, second);
            double multiplier = 1.0;

            if (rate.getRushRates().size() > 0) {
                RushRate rushRate = rate.getRushRateForDateTime(first.getTrackedAt());
                multiplier = rushRate != null ? rushRate.getMultiplier() : 1.0;
            }

            price += (distance * rate.getCarRate()) * multiplier;
        }

        this.totalPrice = price;
        return totalPrice;
    }
}
