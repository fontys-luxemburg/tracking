package lux.fontys.tracking.facade;

import lux.fontys.tracking.dto.TrackerDto;
import lux.fontys.tracking.mapper.TrackerMapper;
import lux.fontys.tracking.model.Tracker;
import lux.fontys.tracking.repository.TrackerRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class TrackerFacade implements BaseFacade<TrackerDto, Long> {

	@Inject
	TrackerRepository trackerRepository;

	@Inject
    TrackerMapper trackerMapper;


	@Override
	public Optional<TrackerDto> findById(Long id) {
	    Tracker tracker = trackerRepository.findById(id).get();
        TrackerDto trackerDto = trackerMapper.trackerToTrackerDto(tracker);
	    return Optional.of(trackerDto);
	}

	public Optional<TrackerDto> findbyUuid(UUID uuid){
		Tracker tracker = trackerRepository.findByUuid(uuid).get();
		TrackerDto trackerDto = trackerMapper.trackerToTrackerDto(tracker);
		return Optional.of(trackerDto);
	}

	@Override
	public List<TrackerDto>  findAll() {
	    List<TrackerDto> trackerDtos = trackerMapper.trackersToTrackerDtos(trackerRepository.findAll());
		return trackerDtos;
	}

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public TrackerDto save(TrackerDto entity) {
	    Tracker tracker = trackerMapper.trackerDtoToTracker(entity);
	    trackerRepository.save(tracker);
        return entity;
    }

	public List<TrackerDto> findAllByVehicleID(String vehicle_id) {
		return trackerMapper.trackersToTrackerDtos(trackerRepository.findByVehicleID(vehicle_id));
	}
}
