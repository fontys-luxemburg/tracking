package lux.fontys.tracking.dto;

import java.util.UUID;

public class TrackerDto extends BaseDto {

    private String name;

    private UUID trackerId;

    public UUID getTrackerId() {
        return trackerId;
    }

    public void setTrackerId(UUID trackerId) {
        this.trackerId = trackerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
