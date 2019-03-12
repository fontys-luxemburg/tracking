package lux.fontys.tracking.mapper;

import lux.fontys.tracking.dto.TrackerDto;
import lux.fontys.tracking.model.Tracker;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TrackerMapper {
    TrackerMapper INSTANCE = Mappers.getMapper( TrackerMapper.class );

    List<TrackerDto> trackersToTrackerDtos(List<Tracker> trackers);
    TrackerDto trackerToTrackerDto(Tracker tracker);
}
