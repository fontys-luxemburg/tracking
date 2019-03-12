package lux.fontys.tracking.facade;

import lux.fontys.tracking.model.Tracker;
import lux.fontys.tracking.repository.TrackerRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class TrackerFacade implements BaseFacade<Tracker, UUID> {

	@Inject
	TrackerRepository trackerRepository;


	@Override
	public Optional<Tracker> findById(UUID id) {
		return trackerRepository.findById(id);
	}

	@Override
	public List<Tracker>  findAll() {
		return trackerRepository.findAll();
	}

	@Override
	public Tracker save(Tracker entity) {
		return trackerRepository.save(entity);
	}
}
