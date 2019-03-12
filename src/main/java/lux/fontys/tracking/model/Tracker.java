package lux.fontys.tracking.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "trackers")
public class Tracker extends BaseEntity {

    private String name;

    public Tracker() {
    }

    public Tracker(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
