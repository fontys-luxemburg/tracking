package lux.fontys.tracking.mapper;

import lux.fontys.tracking.dto.LocationDto;
import lux.fontys.tracking.model.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "location")
public interface LocationMapper {

    @Mapping(source = "trip.id", target = "tripId")
    LocationDto locationToLocationDto(Location location);
    List<LocationDto> locationsToLocationDtos(List<Location> locations);

    @Mapping(source = "tripId", target = "trip.id")
    Location locationDtoToLocation(LocationDto locationDto);
}
