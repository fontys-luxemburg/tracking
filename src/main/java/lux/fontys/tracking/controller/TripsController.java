package lux.fontys.tracking.controller;

import lux.fontys.tracking.dto.TrackerDto;
import lux.fontys.tracking.dto.TripDto;
import lux.fontys.tracking.facade.LocationFacade;
import lux.fontys.tracking.facade.TrackerFacade;
import lux.fontys.tracking.facade.TripFacade;
import lux.fontys.tracking.model.Tracker;
import lux.fontys.tracking.model.Trip;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Path("/trips")
@Produces("application/json")
@Consumes("application/json")
public class TripsController {

    @Inject
    TripFacade tripFacade;

    @Inject
    LocationFacade locationFacade;
    @Inject
    TrackerFacade trackerFacade;

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
    @Path("finish/{id}")
    public Response endTrip(@PathParam("id") Long id) {
        Optional<Trip> trip = tripFacade.findByIdTrip(id);
        if(trip.isPresent())
        {
            try {
                tripFacade.finishTrip(trip.get());
                return Response.ok().build();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                return Response.status(Response.Status.NOT_FOUND).build();
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
    @GET
    @Path("tracker/{trackerid}")
    @Transactional
    public Response getByTracking(@PathParam("trackerid") UUID trackerId){
        List<TripDto> trips = tripFacade.getAllFor(trackerId);
        return Response.ok(trips).build();
    }
    @GET
    @Path("tracker/{trackerid}/date")
    @Transactional
    public Response getByTracking(@PathParam("trackerid") UUID trackerId,@QueryParam("startDate") Long startDate,@QueryParam("endDate") Long endDate){
        return Response.ok(tripFacade.getAllTripsTrackerTrip((trackerFacade.findbyUuid(trackerId).get()),new Date(startDate),new Date(endDate))).build();
    }
}
