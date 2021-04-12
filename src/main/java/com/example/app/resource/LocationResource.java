package com.example.app.resource;

import com.example.app.domain.Location;
import com.example.app.dtos.EmptyDTO;
import com.example.app.dtos.ObjectIdDTO;
import com.example.app.dtos.WarningMessageDTO;
import com.example.app.service.LocationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * The type Location resource.
 */
@Path("locations")
public class LocationResource {

    private LocationService locationService = new LocationService();

    /**
     * Add new location.
     *
     * @param newLocationName the new location name
     * @param emptyDTO        the empty dto
     * @return the object id dto
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{newLocationName}")
    public ObjectIdDTO addNewLocation(@PathParam("newLocationName") String newLocationName, EmptyDTO emptyDTO){
        return new ObjectIdDTO(locationService.addNewLocation(newLocationName));
    }

    /**
     * Gets location by name.
     *
     * @param locationName the location name
     * @return the location by name
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{locationName}")
    public Location getLocationByName(@PathParam("locationName") String locationName){
        return locationService.findLocation(locationName);
    }

    /**
     * Get all locations list.
     *
     * @return the list
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Location> getAllLocations(){
        return locationService.findAllLocations();
    }



}
