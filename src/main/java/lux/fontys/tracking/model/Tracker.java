package lux.fontys.tracking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "trackers")
public class Tracker {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Tracker() {
    }

    public Tracker(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
