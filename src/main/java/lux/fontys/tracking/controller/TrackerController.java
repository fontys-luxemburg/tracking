package lux.fontys.tracking.controller;

import lux.fontys.tracking.dto.TrackerDto;
import lux.fontys.tracking.facade.TrackerFacade;
import lux.fontys.tracking.facade.TripFacade;
import lux.fontys.tracking.model.Tracker;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.zip.DataFormatException;

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
        @Path("available")
    public Response getAvailableTracker(){
        Optional<List<TrackerDto>> trackerDto = trackerFacade.findAvailableTracker();
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

    //TODO Registration ID meegeven
    @POST
    @Path("{vehicleID}")
    @Transactional
    public Response create(@PathParam("vehicleID") String vehicleID) {
        trackerFacade.setLastTracker(vehicleID);
        UUID uuid = UUID.randomUUID();
        try {
            trackerFacade.save(new TrackerDto(uuid, vehicleID));
            return Response.status(Response.Status.CREATED).entity(uuid).build();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/vehicle/{id}")
    public Response getTrackersByVehicleID(@PathParam("id") String vehicleID) {
        List<TrackerDto> trackers = trackerFacade.findAllByVehicleID(vehicleID);
        if(trackers != null)
        {
            return Response.ok(trackers).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/vehicle")
    public Response getTrackersByVehicleIDBetweenDates(
            @QueryParam("vehicleID") String vehicleID,
            @QueryParam("begin") Long begin,
            @QueryParam("end") Long end) {
        Date beginDate = new Date(begin);
        Date endDate = new Date(end);
        List<TrackerDto> trackers = trackerFacade.findByVehicleIDBetweenDates(vehicleID, beginDate, endDate);
        if(trackers != null)
        {
            trackers = tripFacade.GetTripBetweenDates(trackers, beginDate, endDate);
            return Response.ok(trackers).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
