package lux.fontys.tracking.facade;

import lux.fontys.tracking.dto.TrackerDto;
import lux.fontys.tracking.mapper.TrackerMapper;
import lux.fontys.tracking.model.Tracker;
import lux.fontys.tracking.repository.TrackerRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class TrackerFacade implements BaseFacade<TrackerDto, UUID> {

	@Inject
	TrackerRepository trackerRepository;

	@Inject
    TrackerMapper trackerMapper;


	@Override
	public Optional<TrackerDto> findById(UUID id) {
	    Tracker tracker = trackerRepository.findById(id).get();
        TrackerDto trackerDto = trackerMapper.trackerToTrackerDto(tracker);
	    return Optional.of(trackerDto);
	}

	@Override
	public List<TrackerDto>  findAll() {
	    List<TrackerDto> trackerDtos = trackerMapper.trackersToTrackerDtos(trackerRepository.findAll());
		return trackerDtos;
	}

    @Override
    public TrackerDto save(TrackerDto entity) {
	    Tracker tracker = trackerMapper.trackerDtoToTracker(entity);
	    trackerRepository.save(tracker);
        return entity;
    }
}
