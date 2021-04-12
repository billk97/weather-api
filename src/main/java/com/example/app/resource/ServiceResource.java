package com.example.app.resource;

import com.example.app.domain.Service;
import com.example.app.dtos.NewServiceNameDTO;
import com.example.app.dtos.ObjectIdDTO;
import com.example.app.service.ServiceService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * The type Service resource.
 */
@Path("services")
public class ServiceResource {

    private ServiceService serviceService = new ServiceService();

    /**
     * Add new service object id dto.
     *
     * @param serviceName the service name
     * @return the object id dto
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{serviceName}")
    public ObjectIdDTO addNewService(@PathParam("serviceName") String serviceName){
        return new ObjectIdDTO(serviceService.addNewService(serviceName));
    }

    /**
     * Change service name.
     *
     * @param oldServiceName    the old service name
     * @param newServiceNameDTO the new service name dto
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{oldServiceName}")
    public void changeServiceName(@PathParam("oldServiceName")String oldServiceName, NewServiceNameDTO newServiceNameDTO){
        serviceService.updateServiceName(oldServiceName, newServiceNameDTO.getServiceName());
    }

    /**
     * Get all services list.
     *
     * @return the list
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Service> getAllServices(){
        return serviceService.getAllServices();
    }

    /**
     * Gets service by name.
     *
     * @param serviceName the service name
     * @return the service by name
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{serviceName}")
    public Service getServiceByName(@PathParam("serviceName") String serviceName){
        return serviceService.getService(serviceName);
    }


}
