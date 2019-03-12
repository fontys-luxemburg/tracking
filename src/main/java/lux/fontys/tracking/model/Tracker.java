package lux.fontys.tracking.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trackers")
public class Tracker extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "tracker", fetch = FetchType.LAZY)
    @Getter @Setter private List<Trip> trips = new ArrayList<>();

    public Tracker() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
