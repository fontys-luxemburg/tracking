package lux.fontys.tracking.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Rate extends BaseEntity {

    private double carRate;
    private double truckRate;
    private double busRate;

    private List<RushRate> rushRates = new ArrayList<>();

    public Rate() {
    }

    public double getCarRate() {
        return carRate;
    }

    public void setCarRate(double carRate) {
        this.carRate = carRate;
    }

    public double getTruckRate() {
        return truckRate;
    }

    public void setTruckRate(double truckRate) {
        this.truckRate = truckRate;
    }

    public double getBusRate() {
        return busRate;
    }

    public void setBusRate(double busRate) {
        this.busRate = busRate;
    }

    public List<RushRate> getRushRates() {
        return rushRates;
    }

    public void addRushRate(RushRate rushRate) {
        rushRate.setRate(this);
        rushRates.add(rushRate);
    }

    public RushRate getRushRateForDateTime(Date date) {
        List<RushRate> rushRates = rushRatesOnDayOfWeek(dayOfWeek(date));

        for (RushRate rate: rushRates) {
            Date startTime = rate.getStartTime(date);
            Date endTime = rate.getEndTime(date);
            if (date.after(startTime) && date.before(endTime)) {
                return rate;
            }
        }

        return null;
    }

    private List<RushRate> rushRatesOnDayOfWeek(int dayOfWeek) {
        List<RushRate> rates = new ArrayList<>();

        for (RushRate rate: rushRates) {
            if (rate.getDayOfWeek() == dayOfWeek) {
                rates.add(rate);
            }
        }

        return rates;
    }

    private int dayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public double rateForVehicle(String type) {
        switch (type) {
            case "truck":
                return getTruckRate();
            case "bus":
                return getBusRate();
            default:
                return getCarRate();
        }
    }
}
