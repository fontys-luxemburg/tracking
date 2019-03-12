package lux.fontys.tracking.controller;

import lux.fontys.tracking.facade.TrackerFacade;
import lux.fontys.tracking.model.Tracker;
import lux.fontys.tracking.repository.TrackerRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/trackers")
@Produces("application/json")
@Consumes("application/json")
public class TrackerController {

    @Inject
    TrackerFacade trackerFacade;

    @GET
    public Response index() {
        return Response.ok(trackerFacade.findAll()).build();
    }

    @GET
    @Path("{id}")
    public Response show(@PathParam("id") UUID id) {
        return Response.ok(trackerFacade.findById(id)).build();
    }

    @POST
    public Response create(Tracker tracker) {
        trackerFacade.save(tracker);
        return Response.status(Response.Status.CREATED).build();
    }
}
