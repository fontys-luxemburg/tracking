package lux.fontys.tracking.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public abstract class BaseDto implements Serializable {
    private Long id;

    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
