package lux.fontys.tracking.controller;

import lux.fontys.tracking.dto.TripDto;
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

    @POST
    public Response create(TripDto trip) {
        tripFacade.save(trip);
        return Response.status(Response.Status.CREATED).build();
    }
}
