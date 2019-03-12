package lux.fontys.tracking.facade;

import lux.fontys.tracking.model.Tracker;
import lux.fontys.tracking.repository.TrackerRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TrackerFacade implements BaseFacade<Tracker, Long> {

	@Inject
	TrackerRepository trackerRepository;


	@Override
	public Optional<Tracker> findById(Long id) {
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
