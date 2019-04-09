package lux.fontys.tracking.dto;

public class TripDto extends BaseDto {
    private Long trackerId;
    private Long trip_ID_Man;
    public Long getTrackerId() {
        return trackerId;
    }

    public Long getTrip_ID_Man() {
        return trip_ID_Man;
    }

    public void setTrip_ID_Man(Long trip_ID_Man) {
        this.trip_ID_Man = trip_ID_Man;
    }

    public void setTrackerId(Long trackerId) {
        this.trackerId = trackerId;
    }
}
