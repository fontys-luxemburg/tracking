package lux.fontys.tracking.controller;

import lux.fontys.tracking.model.Tracker;
import lux.fontys.tracking.model.TrackerRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/trackers")
public class TrackerController {

    @Inject
    TrackerRepository trackerRepository;

    @GET
    public Response index() {
        trackerRepository.create(new Tracker("This is a test!"));
        return Response.ok().build();
    }
}
