package lux.fontys.tracking.model;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class RushRate {

    private Rate rate;
    private int dayOfWeek;

    private String startTime;
    private String endTime;

    private double multiplier;

    public RushRate() {
    }

    public RushRate(int dayOfWeek, String startTime, String endTime, double multiplier) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.multiplier = multiplier;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getStartTime() {
        return startTime;
    }

    public Date getStartTime(Date date) {
        return getDate(date, startTime);
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public Date getEndTime(Date date) {
        return getDate(date, endTime);
    }

    private Date getDate(Date date, String time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time.split(":")[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(time.split(":")[1]));

        return calendar.getTime();
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }
}
