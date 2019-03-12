package lux.fontys.tracking.controller;

import lux.fontys.tracking.model.Tracker;
import lux.fontys.tracking.repository.TrackerRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/trackers")
@Produces("application/json")
@Consumes("application/json")
public class TrackerController {

    @Inject
    TrackerRepository trackerRepository;

    @GET
    public Response index() {
        return Response.ok(trackerRepository.findAll()).build();
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
