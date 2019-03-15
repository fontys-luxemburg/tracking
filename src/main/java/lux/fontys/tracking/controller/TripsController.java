package lux.fontys.tracking.controller;

import lux.fontys.tracking.dto.TripDto;
import lux.fontys.tracking.facade.LocationFacade;
import lux.fontys.tracking.facade.TripFacade;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/trips")
@Produces("application/json")
@Consumes("application/json")
public class TripsController {

    @Inject
    TripFacade tripFacade;

    @Inject
    LocationFacade locationFacade;

    @GET
    public Response index() {
        return Response.ok(tripFacade.findAll()).build();
    }

    @GET
    @Path("{id}")
    public Response show(@PathParam("id") Long id) {
        return Response.ok(tripFacade.findById(id)).build();
    }

    @GET
    @Path("{trip_id}/locations")
    public Response showLocations(@PathParam("trip_id") Long tripId) {
        return Response.ok(locationFacade.getAllFor(tripId)).build();
    }

    @POST
    public Response create(TripDto trip) {
        tripFacade.save(trip);
        return Response.status(Response.Status.CREATED).build();
    }
}
