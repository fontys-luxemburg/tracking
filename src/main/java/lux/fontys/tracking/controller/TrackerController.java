package lux.fontys.tracking.controller;

import lux.fontys.tracking.dto.TrackerDto;
import lux.fontys.tracking.facade.TrackerFacade;
import lux.fontys.tracking.facade.TripFacade;
import lux.fontys.tracking.model.Tracker;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Path("/trackers")
@Produces("application/json")
@Consumes("application/json")
public class TrackerController {

    @Inject
    TrackerFacade trackerFacade;

    @Inject
    TripFacade tripFacade;

    @GET
    @Transactional
    public Response index() {
        return Response.ok(trackerFacade.findAll()).build();
    }

    @GET
    @Path("{uuid}")
    public Response show(@PathParam("uuid") UUID uuid) {
        Optional<TrackerDto> trackerDto = trackerFacade.findbyUuid(uuid);
        if (trackerDto.isPresent()){
            return Response.ok(trackerDto.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("{id}/trips")
    public Response showTrips(@PathParam("id") Long id) {
        return Response.ok(tripFacade.getAllFor(id)).build();
    }

    @POST
    public Response create() {
        UUID uuid = UUID.randomUUID();
        trackerFacade.save(new TrackerDto(uuid));

        return Response.status(Response.Status.CREATED).entity(uuid).build();
    }

    @GET
    @Path("/vehicle/{id}")
    public Response getTrackersByVehicleID(@PathParam("id") String vehicle_ID) {
        List<TrackerDto> trackers = trackerFacade.findAllByVehicleID(vehicle_ID);
        if(trackers != null)
        {
            return Response.ok(trackers).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
