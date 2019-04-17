package lux.fontys.tracking.dto;

import java.util.UUID;

public class TrackerDto extends BaseDto {

    private UUID trackerId;

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
}
