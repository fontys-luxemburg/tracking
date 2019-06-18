package lux.fontys.tracking.controller;

import lux.fontys.tracking.dto.TripDto;
import lux.fontys.tracking.facade.LocationFacade;
import lux.fontys.tracking.facade.TripFacade;
import lux.fontys.tracking.model.Trip;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

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
    @Path("finishTrip/{id}")
    public Response endTrip(@PathParam("id") Long id) {
        Trip trip = tripFacade.findByIdTrip(id).get();
        if(trip != null)
        {
            try {
                tripFacade.finishTrip(trip);
                return Response.ok().build();
            }
            catch (Exception e) {
                return Response.ok(e.getMessage()).build();
            }
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
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

    @GET
    @Path("/newid/{uuid}")
    @Transactional
    public Response newID(@PathParam("uuid") UUID uuid) {
        try {
            return Response.ok(tripFacade.getNewID(uuid)).build();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
