package lux.fontys.tracking.controller;

import lux.fontys.tracking.dto.TrackerDto;
import lux.fontys.tracking.mapper.TrackerMapper;
import lux.fontys.tracking.model.Tracker;
import lux.fontys.tracking.repository.TrackerRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/trackers")
@Produces("application/json")
@Consumes("application/json")
public class TrackerController {

    @Inject
    TrackerRepository trackerRepository;

    @GET
    @Transactional
    public Response index() {
        List<Tracker> trackers = trackerRepository.findAll();
        List<TrackerDto> trackerDtos = TrackerMapper.INSTANCE.trackersToTrackerDtos(trackers);
        return Response.ok(trackerDtos).build();
    }

    @GET
    @Path("{id}")
    public Response show(@PathParam("id") Long id) {
        return Response.ok(trackerRepository.findById(id)).build();
    }

    @POST
    public Response create(Tracker tracker) {
        trackerRepository.save(tracker);
        return Response.status(Response.Status.CREATED).build();
    }
}
