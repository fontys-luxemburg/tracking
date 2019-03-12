package lux.fontys.tracking.controller;

import lux.fontys.tracking.dto.TrackerDto;
import lux.fontys.tracking.facade.TrackerFacade;
import lux.fontys.tracking.model.Tracker;

import javax.inject.Inject;
import javax.transaction.Transactional;
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
    @Transactional
    public Response index() {
        return Response.ok(trackerFacade.findAll()).build();
    }

    @GET
    @Path("{id}")
    public Response show(@PathParam("id") UUID id) {
        return Response.ok(trackerFacade.findById(id)).build();
    }

    @POST
    public Response create(TrackerDto tracker) {
        trackerFacade.save(tracker);
        return Response.status(Response.Status.CREATED).build();
    }
}
