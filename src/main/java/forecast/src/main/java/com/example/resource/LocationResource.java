package com.example.resource;


import com.example.domain.Location;
import com.example.dtos.in.CreateLocationDTO;
import com.example.dtos.out.ObjectIdDTO;
import com.example.repository.LocationRepository;
import com.example.usecases.location.CreateLocation;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * The type Location resource.
 */
@Path("api/locations")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LocationResource {

    @Inject
    LocationRepository locationRepo;
    @Inject
    CreateLocation createLocation;

    /**
     * Add new location.
     *
     * @param dto object to create a new location
     * @return the object id dto
     */
    @POST
    public ObjectIdDTO addNewLocation(CreateLocationDTO dto){
        return new ObjectIdDTO(createLocation.command(dto));
    }

    /**
     * Gets location by name.
     *
     * @param locationId the location id
     * @return the location object
     */
    @GET
    @Path("{locationId}")
    public Location getLocationByName(@PathParam("locationId") String locationId){
        Location location = locationRepo.findById(Long.valueOf(locationId));
        if(location == null) {
            throw new IllegalArgumentException(String.format("Location with id: %s doesnt exist", locationId));
        }
        return location;
    }

    /**
     * Get all locations list.
     *
     * @return the list
     */
    @GET
    public List<Location> getAllLocations(){
        return locationRepo.findAll().stream().toList();
    }



}
