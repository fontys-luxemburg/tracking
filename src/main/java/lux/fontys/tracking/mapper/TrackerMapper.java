package lux.fontys.tracking.mapper;

import lux.fontys.tracking.dto.TrackerDto;
import lux.fontys.tracking.model.Tracker;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "tracker")
public interface TrackerMapper {

    List<TrackerDto> trackersToTrackerDtos(List<Tracker> trackers);
    TrackerDto trackerToTrackerDto(Tracker tracker);

    Tracker trackerDtoToTracker(TrackerDto trackerDto);
}
