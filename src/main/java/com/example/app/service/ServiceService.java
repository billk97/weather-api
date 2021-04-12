package com.example.app.service;

import com.example.app.domain.Service;
import com.example.app.repository.ServiceRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * The type Service service.
 */
public class ServiceService {

    private ServiceRepository serviceRepository = new ServiceRepository();

    /**
     * Add new service int.
     *
     * @param serviceName the service name
     * @return the int
     */
    public int addNewService(String serviceName){
        Optional<Service> serviceOptional = serviceRepository.findByName(serviceName);
        if(serviceOptional.isPresent()){
           throw new IllegalArgumentException("service already exists: " +serviceName);
        }
        Service service = new Service();
        service.setName(serviceName);
        serviceRepository.save(service);
        return service.getServiceId();
    }

    /**
     * Update service name.
     *
     * @param oldServiceName the old service name
     * @param newServiceName the new service name
     */
    public void updateServiceName(String oldServiceName, String newServiceName){
        Optional<Service> serviceOptional = serviceRepository.findByName(newServiceName);
        if(serviceOptional.isPresent()){
            throw new IllegalArgumentException("service already exists: " + newServiceName);
        }
        Optional<Service> oldServiceOptional = serviceRepository.findByName(oldServiceName);
        if(!oldServiceOptional.isPresent()){
            throw new IllegalArgumentException("service doesn't exists: " + oldServiceName);
        }
        oldServiceOptional.get().setName(newServiceName);
        serviceRepository.save(oldServiceOptional.get());
    }

    /**
     * Delete service.
     *
     * @param serviceName the service name
     */
    public void deleteService(String serviceName){
        Optional<Service> serviceOptional = serviceRepository.findByName(serviceName);
        if(!serviceOptional.isPresent()){
            throw new IllegalArgumentException("Service doesn't exists: " + serviceName);
        }
        serviceRepository.delete(serviceOptional.get());
    }

    /**
     * Get all services list.
     *
     * @return the list
     */
    public List<Service> getAllServices(){
        return serviceRepository.findAll();
    }

    /**
     * Get service service.
     *
     * @param serviceName the service name
     * @return the service
     */
    public Service getService(String serviceName){
        Optional<Service> serviceOptional = serviceRepository.findByName(serviceName);
        if(!serviceOptional.isPresent()){
            throw new IllegalArgumentException("service with name doesn't exists");
        }
        return serviceOptional.get();
    }
}
