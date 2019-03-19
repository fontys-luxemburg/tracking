package lux.fontys.tracking.dto;

import java.io.Serializable;
import java.util.UUID;

public abstract class BaseDto implements Serializable {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
